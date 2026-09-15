package backend.service;

import entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> getAllDepartments();
    Department getDepartmentById(int id);
    boolean addDepartment(Department department);
    boolean updateDepartment(Department department);
    boolean deleteDepartment(int id);
}
