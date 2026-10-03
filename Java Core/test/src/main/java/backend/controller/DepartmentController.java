package backend.controller;

import backend.service.IDepartmentService;
import backend.service.impl.DepartmentService;
import entity.Department;

import java.util.List;

public class DepartmentController {
    private final IDepartmentService departmentService;

    public DepartmentController() {
        this.departmentService = new DepartmentService();
    }

    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    public Department getDepartmentById(int id) {
        return departmentService.getDepartmentById(id);
    }

    public boolean addDepartment(Department department) {
        return departmentService.addDepartment(department);
    }

    public boolean updateDepartment(Department department) {
        return departmentService.updateDepartment(department);
    }

    public boolean deleteDepartment(int id) {
        return departmentService.deleteDepartment(id);
    }

    public void displayDepartmentsAsTable() {
        List<Department> departments = getAllDepartments();

        if (departments.isEmpty()) {
            System.out.println("Không có dữ liệu Department.");
            return;
        }

        System.out.println("\n" + "=".repeat(40));
        System.out.printf("| %-4s | %-20s |%n", "ID", "Department Name");
        System.out.println("=".repeat(40));

        for (Department department : departments) {
            System.out.println(department);
        }

        System.out.println("=".repeat(40));
    }
}
