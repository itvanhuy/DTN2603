package backend.repository.impl;

import backend.repository.IManufacturerRepository;
import entity.Manufacturer;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManufacturerRepository implements IManufacturerRepository {
    @Override
    public Manufacturer getById(int id) {
        String sql = "SELECT ManufacturerId, ManufacturerName FROM Manufacturer WHERE ManufacturerId = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Manufacturer(
                            resultSet.getInt("ManufacturerId"),
                            resultSet.getString("ManufacturerName")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm nhà sản xuất: " + e.getMessage());
        }
        return null;
    }
}
