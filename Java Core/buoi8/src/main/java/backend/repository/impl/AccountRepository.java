package backend.repository.impl;

import backend.repository.IAccountRepository;
import entity.Account;
import entity.Department;
import entity.Position;
import entity.PositionName;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements IAccountRepository {

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT a.account_id, a.username, a.fullname, a.email, a.department_id, a.position_id, a.create_date, " +
                "d.department_name, p.position_name " +
                "FROM account a " +
                "JOIN department d ON a.department_id = d.department_id " +
                "JOIN position p ON a.position_id = p.position_id " +
                "ORDER BY a.account_id";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return accounts;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Department department = new Department(
                            resultSet.getInt("department_id"),
                            resultSet.getString("department_name")
                    );
                    Position position = new Position(
                            resultSet.getInt("position_id"),
                            PositionName.valueOf(resultSet.getString("position_name"))
                    );

                    accounts.add(new Account(
                            resultSet.getInt("account_id"),
                            resultSet.getString("username"),
                            resultSet.getString("fullname"),
                            resultSet.getString("email"),
                            department,
                            position,
                            resultSet.getDate("create_date").toLocalDate()
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách account: " + e.getMessage());
        }

        return accounts;
    }

    @Override
    public Account getById(int id) {
        String sql = "SELECT a.account_id, a.username, a.fullname, a.email, a.department_id, a.position_id, a.create_date, " +
                "d.department_name, p.position_name " +
                "FROM account a " +
                "JOIN department d ON a.department_id = d.department_id " +
                "JOIN position p ON a.position_id = p.position_id " +
                "WHERE a.account_id = ?";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return null;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Department department = new Department(
                                resultSet.getInt("department_id"),
                                resultSet.getString("department_name")
                        );
                        Position position = new Position(
                                resultSet.getInt("position_id"),
                                PositionName.valueOf(resultSet.getString("position_name"))
                        );

                        return new Account(
                                resultSet.getInt("account_id"),
                                resultSet.getString("username"),
                                resultSet.getString("fullname"),
                                resultSet.getString("email"),
                                department,
                                position,
                                resultSet.getDate("create_date").toLocalDate()
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm account theo ID: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean add(Account account) {
        String sql = "INSERT INTO account (email, username, fullname, department_id, position_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return false;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, account.getEmail());
                statement.setString(2, account.getUsername());
                statement.setString(3, account.getFullName());
                statement.setInt(4, account.getDepartment().getId());
                statement.setInt(5, account.getPosition().getId());
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm account: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Account account) {
        String sql = "UPDATE account SET email = ?, username = ?, fullname = ?, department_id = ?, position_id = ? WHERE account_id = ?";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return false;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, account.getEmail());
                statement.setString(2, account.getUsername());
                statement.setString(3, account.getFullName());
                statement.setInt(4, account.getDepartment().getId());
                statement.setInt(5, account.getPosition().getId());
                statement.setInt(6, account.getId());
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật account: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM account WHERE account_id = ?";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return false;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa account: " + e.getMessage());
            return false;
        }
    }
}
