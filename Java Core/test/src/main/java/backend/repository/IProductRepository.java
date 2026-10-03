package backend.repository;

import entity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> getAll();
    boolean existsByName(String name, int ignoredId);
    boolean add(Product product);
    boolean updateName(int id, String name);
    boolean delete(int id);
}
