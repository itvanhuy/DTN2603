package backend.service;

import entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    boolean addProduct(Product product);
    boolean updateProductName(int id, String name);
    boolean deleteProduct(int id);
}
