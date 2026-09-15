package backend;

import entity.Account;
import untils.JDButils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLAccount implements IQLAccount {
    private Connection connection;

    public QLAccount() {
        this.connection = JDButils.getConnection();
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                accounts.add(new Account(
                        rs.getInt("account_id"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("fullname"),
                        rs.getInt("department_id"),
                        rs.getInt("position_id"),
                        rs.getDate("create_date")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return accounts;
    }

    @Override
    public Account getAccountById(int id) {
        String sql = "SELECT * FROM account WHERE account_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

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
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean addAccount(Account account) {
        String sql = "INSERT INTO account "
                + "(email, username, fullname, department_id, position_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, account.getEmail());
            stmt.setString(2, account.getUsername());
            stmt.setString(3, account.getFullname());
            stmt.setInt(4, account.getDepartmentId());
            stmt.setInt(5, account.getPositionId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateAccount(Account account) {
        String sql = "UPDATE account SET email=?, username=?, fullname=?, "
                + "department_id=?, position_id=? WHERE account_id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, account.getEmail());
            stmt.setString(2, account.getUsername());
            stmt.setString(3, account.getFullname());
            stmt.setInt(4, account.getDepartmentId());
            stmt.setInt(5, account.getPositionId());
            stmt.setInt(6, account.getAccountId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAccount(int id) {
        String sql = "DELETE FROM account WHERE account_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void displayAccountsAsTable() {
        List<Account> accounts = getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("Khong co du lieu.");
            return;
        }

        System.out.println("\nID\tEmail\tUsername\tFullname\tDepartment\tPosition\tCreate Date");

        for (Account acc : accounts) {
            System.out.println(acc.getAccountId() + "\t"
                    + acc.getEmail() + "\t"
                    + acc.getUsername() + "\t"
                    + acc.getFullname() + "\t"
                    + acc.getDepartmentId() + "\t"
                    + acc.getPositionId() + "\t"
                    + acc.getCreateDate());
        }
    }
}