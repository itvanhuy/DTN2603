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
        if (isDuplicateName(department.getName(), -1)) {
            return false;
        }
        return departmentRepository.add(department);
    }

    public boolean updateDepartment(Department department) {
        if (department == null || department.getId() <= 0 || !isValid(department)) {
            return false;
        }
        if (isDuplicateName(department.getName(), department.getId())) {
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
        if (department == null || department.getName() == null) {
            return false;
        }
        String name = department.getName().trim();
        return !name.isEmpty() && name.length() >= 2 && name.length() <= 100;
    }

    private boolean isDuplicateName(String name, int ignoreId) {
        if (name == null) {
            return true;
        }
        String target = name.trim();
        List<Department> departments = departmentRepository.getAll();
        for (Department department : departments) {
            if (department != null
                    && department.getName() != null
                    && department.getName().trim().equalsIgnoreCase(target)
                    && department.getId() != ignoreId) {
                return true;
            }
        }
        return false;
    }
}
