package backend;

import backend.DatabaseConnection;
import backend.IQLDepartment;
import entity.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLDepartment implements IQLDepartment {
    private Connection connection;

    public QLDepartment() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM department ORDER BY department_id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Department dept = new Department();
                dept.setDepartmentId(rs.getInt("department_id"));
                dept.setDepartmentName(rs.getString("department_name"));
                departments.add(dept);
            }
        } catch (SQLException e) {
            System.err.println("Loi lay danh sach department: " + e.getMessage());
        }
        return departments;
    }

    @Override
    public Department getDepartmentById(int id) {
        String query = "SELECT * FROM department WHERE department_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name")
                );
            }
        } catch (SQLException e) {
            System.err.println("Loi lay department theo id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addDepartment(Department department) {
        String query = "INSERT INTO department (department_name) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, department.getDepartmentName());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Loi them department: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateDepartment(Department department) {
        String query = "UPDATE department SET department_name=? WHERE department_id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, department.getDepartmentName());
            pstmt.setInt(2, department.getDepartmentId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Loi cap nhat department: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteDepartment(int id) {
        String query = "DELETE FROM department WHERE department_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Loi xoa department: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void displayDepartmentsAsTable() {
        List<Department> departments = getAllDepartments();
        if (departments.isEmpty()) {
            System.out.println("Khong co department nao.");
            return;
        }

        System.out.println("\n" + "=".repeat(40));
        System.out.printf("| %-4s | %-30s |%n", "ID", "Department Name");
        System.out.println("=".repeat(40));

        for (Department dept : departments) {
            System.out.println(dept);
        }
        System.out.println("=".repeat(40));
        System.out.println("Tong so department: " + departments.size());
    }
}