package backend.repository.impl;

import backend.repository.IProductRepository;
import entity.Category;
import entity.Manufacturer;
import entity.Product;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.ProductId, p.ProductName, p.ProductPrice, p.ProductInfo, p.ProductDetail, " +
                "p.RatingStar, p.ProductImageName, p.ManufacturerId, m.ManufacturerName, " +
                "p.CategoryId, c.CategoryName " +
                "FROM Product p JOIN Manufacturer m ON p.ManufacturerId = m.ManufacturerId " +
                "JOIN Category c ON p.CategoryId = c.CategoryId ORDER BY p.ProductId";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                products.add(toProduct(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
        }
        return products;
    }

    @Override
    public boolean existsByName(String name, int ignoredId) {
        String sql = "SELECT 1 FROM Product WHERE ProductName = ? AND ProductId <> ? LIMIT 1";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, ignoredId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra tên sản phẩm: " + e.getMessage());
            return true;
        }
    }

    @Override
    public boolean add(Product product) {
        String sql = "INSERT INTO Product (ProductName, ProductPrice, ProductInfo, ProductDetail, " +
                "RatingStar, ProductImageName, ManufacturerId, CategoryId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getPrice());
            statement.setString(3, product.getInfo());
            statement.setString(4, product.getDetail());
            if (product.getRatingStar() == null) {
                statement.setNull(5, java.sql.Types.TINYINT);
            } else {
                statement.setInt(5, product.getRatingStar());
            }
            statement.setString(6, product.getImageName());
            statement.setInt(7, product.getManufacturer().getId());
            statement.setInt(8, product.getCategory().getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateName(int id, String name) {
        String sql = "UPDATE Product SET ProductName = ? WHERE ProductId = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
            return productExists(id);
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Product WHERE ProductId = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa sản phẩm: " + e.getMessage());
            return false;
        }
    }

    private boolean productExists(int id) throws SQLException {
        String sql = "SELECT 1 FROM Product WHERE ProductId = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private Product toProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("ProductId"));
        product.setName(resultSet.getString("ProductName"));
        product.setPrice(resultSet.getString("ProductPrice"));
        product.setInfo(resultSet.getString("ProductInfo"));
        product.setDetail(resultSet.getString("ProductDetail"));
        int rating = resultSet.getInt("RatingStar");
        product.setRatingStar(resultSet.wasNull() ? null : rating);
        product.setImageName(resultSet.getString("ProductImageName"));
        product.setManufacturer(new Manufacturer(
                resultSet.getInt("ManufacturerId"),
                resultSet.getString("ManufacturerName")
        ));
        product.setCategory(new Category(
                resultSet.getInt("CategoryId"),
                resultSet.getString("CategoryName")
        ));
        return product;
    }
}
