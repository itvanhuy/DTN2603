package backend.repository.impl;

import backend.repository.IDepartmentRepository;
import entity.Department;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository implements IDepartmentRepository {
    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT department_id, department_name FROM department ORDER BY department_id";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return departments;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    departments.add(new Department(
                            resultSet.getInt("department_id"),
                            resultSet.getString("department_name")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách department: " + e.getMessage());
        }
        return departments;
    }

    @Override
    public Department getById(int id) {
        String sql = "SELECT department_id, department_name FROM department WHERE department_id = ?";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return null;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Department(
                                resultSet.getInt("department_id"),
                                resultSet.getString("department_name")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm department theo ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean add(Department department) {
        String sql = "INSERT INTO department (department_name) VALUES (?)";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return false;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, department.getName());
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm department: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Department department) {
        String sql = "UPDATE department SET department_name = ? WHERE department_id = ?";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return false;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, department.getName());
                statement.setInt(2, department.getId());
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật department: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM department WHERE department_id = ?";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return false;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa department: " + e.getMessage());
            return false;
        }
    }
}
