package backend;

import backend.DatabaseConnection;
import backend.IQLPosition;
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
        String query = "SELECT * FROM position ORDER BY position_id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Position pos = new Position();
                pos.setPositionId(rs.getInt("position_id"));
                pos.setPositionName(rs.getString("position_name"));
                positions.add(pos);
            }
        } catch (SQLException e) {
            System.err.println("Loi lay danh sach position: " + e.getMessage());
        }
        return positions;
    }

    @Override
    public Position getPositionById(int id) {
        String query = "SELECT * FROM position WHERE position_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Position(
                        rs.getInt("position_id"),
                        rs.getString("position_name")
                );
            }
        } catch (SQLException e) {
            System.err.println("Loi lay position theo id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addPosition(Position position) {
        String query = "INSERT INTO position (position_name) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, position.getPositionName());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Loi them position: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updatePosition(Position position) {
        String query = "UPDATE position SET position_name=? WHERE position_id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, position.getPositionName());
            pstmt.setInt(2, position.getPositionId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Loi cap nhat position: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePosition(int id) {
        String query = "DELETE FROM position WHERE position_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Loi xoa position: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void displayPositionsAsTable() {
        List<Position> positions = getAllPositions();
        if (positions.isEmpty()) {
            System.out.println("Khong co position nao.");
            return;
        }

        System.out.println("\n" + "=".repeat(25));
        System.out.printf("| %-4s | %-15s |%n", "ID", "Position Name");
        System.out.println("=".repeat(25));

        for (Position pos : positions) {
            System.out.println(pos);
        }
        System.out.println("=".repeat(25));
        System.out.println("Tong so position: " + positions.size());
    }
}