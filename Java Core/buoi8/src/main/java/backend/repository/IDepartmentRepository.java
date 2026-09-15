package backend.repository;

import entity.Department;
import java.util.List;

public interface IDepartmentRepository {
    List<Department> getAll();
    Department getById(int id);
    boolean add(Department department);
    boolean update(Department department);
    boolean delete(int id);
}
