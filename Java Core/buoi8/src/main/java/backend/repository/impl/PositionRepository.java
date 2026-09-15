package backend.repository.impl;

import backend.repository.IPositionRepository;
import entity.Position;
import entity.PositionName;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionRepository implements IPositionRepository {
    @Override
    public List<Position> getAll() {
        List<Position> positions = new ArrayList<>();
        String sql = "SELECT position_id, position_name FROM position ORDER BY position_id";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return positions;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    positions.add(new Position(
                            resultSet.getInt("position_id"),
                            PositionName.valueOf(resultSet.getString("position_name"))
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách position: " + e.getMessage());
        }
        return positions;
    }

    @Override
    public Position getById(int id) {
        String sql = "SELECT position_id, position_name FROM position WHERE position_id = ?";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return null;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Position(
                                resultSet.getInt("position_id"),
                                PositionName.valueOf(resultSet.getString("position_name"))
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm position theo ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean add(Position position) {
        String sql = "INSERT INTO position (position_name) VALUES (?)";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return false;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, position.getName().name());
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm position: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Position position) {
        String sql = "UPDATE position SET position_name = ? WHERE position_id = ?";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return false;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, position.getName().name());
                statement.setInt(2, position.getId());
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật position: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM position WHERE position_id = ?";

        try (Connection connection = JDBCUtils.getConnection()) {
            if (connection == null) {
                return false;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa position: " + e.getMessage());
            return false;
        }
    }
}
