package backend;

import backend.DatabaseConnection;
import backend.IQLAccount;
import entity.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLAccount implements IQLAccount {
    private Connection connection;

    public QLAccount() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM account";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt("account_id"));
                account.setEmail(rs.getString("email"));
                account.setUsername(rs.getString("username"));
                account.setFullname(rs.getString("fullname"));
                account.setDepartmentId(rs.getInt("department_id"));
                account.setPositionId(rs.getInt("position_id"));
                account.setCreateDate(rs.getDate("create_date"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.err.println("Loi lay danh sach account: " + e.getMessage());
        }
        return accounts;
    }

    @Override
    public Account getAccountById(int id) {
        String query = "SELECT * FROM account WHERE account_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Account(
                        rs.getInt("account_id"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("fullname"),
                        rs.getInt("department_id"),
                        rs.getInt("position_id"),
                        rs.getDate("create_date")
                );
            }
        } catch (SQLException e) {
            System.err.println("Loi lay account theo id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addAccount(Account account) {
        String query = "INSERT INTO account (email, username, fullname, department_id, position_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, account.getEmail());
            pstmt.setString(2, account.getUsername());
            pstmt.setString(3, account.getFullname());
            pstmt.setInt(4, account.getDepartmentId());
            pstmt.setInt(5, account.getPositionId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Loi them account: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateAccount(Account account) {
        String query = "UPDATE account SET email=?, username=?, fullname=?, department_id=?, position_id=? WHERE account_id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, account.getEmail());
            pstmt.setString(2, account.getUsername());
            pstmt.setString(3, account.getFullname());
            pstmt.setInt(4, account.getDepartmentId());
            pstmt.setInt(5, account.getPositionId());
            pstmt.setInt(6, account.getAccountId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Loi cap nhat account: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAccount(int id) {
        String query = "DELETE FROM account WHERE account_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Loi xoa account: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void displayAccountsAsTable() {
        List<Account> accounts = getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("Khong co account nao.");
            return;
        }

        System.out.println("\n" + "=".repeat(120));
        System.out.printf("| %-4s | %-25s | %-20s | %-25s | %-8s | %-8s | %-12s |%n",
                "ID", "Email", "Username", "Fullname", "Dept ID", "Pos ID", "Create Date");
        System.out.println("=".repeat(120));

        for (Account acc : accounts) {
            System.out.println(acc);
        }
        System.out.println("=".repeat(120));
        System.out.println("Tong so account: " + accounts.size());
    }
}