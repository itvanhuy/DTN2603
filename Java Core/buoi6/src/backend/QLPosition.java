package backend;

import entity.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLPosition implements IQLPosition {
    private Connection connection;

    public QLPosition() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        String sql = "SELECT * FROM `position`";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                positions.add(new Position(
                        rs.getInt("position_id"),
                        rs.getString("position_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return positions;
    }

    @Override
    public Position getPositionById(int id) {
        String sql = "SELECT * FROM `position` WHERE position_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Position(
                        rs.getInt("position_id"),
                        rs.getString("position_name")
                );
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean addPosition(Position position) {
        String sql = "INSERT INTO `position` (position_name) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, position.getPositionName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updatePosition(Position position) {
        String sql = "UPDATE `position` SET position_name = ? WHERE position_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, position.getPositionName());
            stmt.setInt(2, position.getPositionId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePosition(int id) {
        String sql = "DELETE FROM `position` WHERE position_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void displayPositionsAsTable() {
        List<Position> positions = getAllPositions();
        if (positions.isEmpty()) {
            System.out.println("Khong co du lieu.");
            return;
        }

        System.out.println("\nID\tPosition Name");

        for (Position pos : positions) {
            System.out.println(pos.getPositionId() + "\t" + pos.getPositionName());
        }
    }
}