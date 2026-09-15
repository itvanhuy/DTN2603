package backend.service.impl;

import backend.service.IDepartmentService;
import backend.repository.impl.DepartmentRepository;
import entity.Department;

import java.util.List;

public class DepartmentService implements IDepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService() {
        this.departmentRepository = new DepartmentRepository();
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.getAll();
    }

    public Department getDepartmentById(int id) {
        if (id <= 0) {
            return null;
        }
        return departmentRepository.getById(id);
    }

    public boolean addDepartment(Department department) {
        if (!isValid(department)) {
            return false;
        }
        return departmentRepository.add(department);
    }

    public boolean updateDepartment(Department department) {
        if (department == null || department.getId() <= 0 || !isValid(department)) {
            return false;
        }
        return departmentRepository.update(department);
    }

    public boolean deleteDepartment(int id) {
        if (id <= 0) {
            return false;
        }
        return departmentRepository.delete(id);
    }

    private boolean isValid(Department department) {
        return department != null && department.getName() != null && !department.getName().trim().isEmpty();
    }
}
