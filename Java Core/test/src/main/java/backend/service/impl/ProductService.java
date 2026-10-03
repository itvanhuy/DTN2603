package backend.service.impl;

import backend.repository.impl.ProductRepository;
import backend.service.IProductService;
import entity.Product;

import java.util.List;

public class ProductService implements IProductService {
    private final ProductRepository repository = new ProductRepository();

    @Override
    public List<Product> getAllProducts() {
        return repository.getAll();
    }

    @Override
    public boolean addProduct(Product product) {
        if (!isValid(product)) {
            return false;
        }
        normalizeProduct(product);
        return !repository.existsByName(product.getName(), 0) && repository.add(product);
    }

    @Override
    public boolean updateProductName(int id, String name) {
        String normalizedName = normalize(name);
        return id > 0 && normalizedName != null && normalizedName.length() <= 50
                && !repository.existsByName(normalizedName, id)
                && repository.updateName(id, normalizedName);
    }

    @Override
    public boolean deleteProduct(int id) {
        return id > 0 && repository.delete(id);
    }

    private boolean isValid(Product product) {
        if (product == null
                || !hasLength(product.getName(), 1, 50)
                || !hasLength(product.getPrice(), 1, 50)
                || !hasLength(product.getInfo(), 1, 200)
                || !hasLength(product.getImageName(), 1, 50)
                || product.getManufacturer() == null || product.getManufacturer().getId() <= 0
                || product.getCategory() == null || product.getCategory().getId() <= 0) {
            return false;
        }
        if (product.getDetail() != null && product.getDetail().length() > 500) {
            return false;
        }
        return product.getRatingStar() == null
                || (product.getRatingStar() >= 0 && product.getRatingStar() <= 5);
    }

    private void normalizeProduct(Product product) {
        product.setName(product.getName().trim());
        product.setPrice(product.getPrice().trim());
        product.setInfo(product.getInfo().trim());
        product.setDetail(normalize(product.getDetail()));
        product.setImageName(product.getImageName().trim());
    }

    private boolean hasLength(String value, int min, int max) {
        String normalized = normalize(value);
        return normalized != null && normalized.length() >= min && normalized.length() <= max;
    }

    private String normalize(String value) {
        if (value == null) {
            return null;
        }
        String normalized = value.trim();
        return normalized.isEmpty() ? null : normalized;
    }
}
