package backend.controller;

import backend.service.IProductService;
import backend.service.impl.ProductService;
import entity.Product;

import java.util.List;

public class ProductController {
    private final IProductService service = new ProductService();

    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    public boolean addProduct(Product product) {
        return service.addProduct(product);
    }

    public boolean updateProductName(int id, String name) {
        return service.updateProductName(id, name);
    }

    public boolean deleteProduct(int id) {
        return service.deleteProduct(id);
    }

    public void displayProducts() {
        List<Product> products = getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Không có dữ liệu sản phẩm.");
            return;
        }

        System.out.println("Danh sách sản phẩm:");
        for (Product product : products) {
            String detail = product.getDetail() == null ? product.getInfo() : product.getDetail();
            System.out.printf("ID: %d | Tên: %s%n", product.getId(), product.getName());
            System.out.printf("Giá: %s | Số sao: %s | Nhà sản xuất: %s%n",
                    product.getPrice(),
                    product.getRatingStar() == null ? "Chưa đánh giá" : product.getRatingStar(),
                    product.getManufacturer() == null ? "" : product.getManufacturer().getName());
            System.out.println("Chi tiết: " + detail);
            System.out.println("-".repeat(80));
        }
    }
}
