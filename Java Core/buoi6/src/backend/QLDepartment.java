package backend;

import entity.Department;
import untils.JDButils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLDepartment implements IQLDepartment {
    private Connection connection;

    public QLDepartment() {
        this.connection = JDButils.getConnection();
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM department";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                departments.add(new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return departments;
    }

    @Override
    public Department getDepartmentById(int id) {
        String sql = "SELECT * FROM department WHERE department_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name")
                );
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean addDepartment(Department department) {
        String sql = "INSERT INTO department (department_name) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, department.getDepartmentName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateDepartment(Department department) {
        String sql = "UPDATE department SET department_name=? WHERE department_id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, department.getDepartmentName());
            stmt.setInt(2, department.getDepartmentId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteDepartment(int id) {
        String sql = "DELETE FROM department WHERE department_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void displayDepartmentsAsTable() {
        List<Department> departments = getAllDepartments();
        if (departments.isEmpty()) {
            System.out.println("Khong co du lieu.");
            return;
        }

        System.out.println("\nID\tDepartment Name");

        for (Department dept : departments) {
            System.out.println(dept.getDepartmentId() + "\t" + dept.getDepartmentName());
        }
    }
}