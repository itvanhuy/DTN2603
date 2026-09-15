package backend.controller;

import backend.service.IPositionService;
import backend.service.impl.PositionService;
import entity.Position;

import java.util.List;

public class PositionController {
    private final IPositionService positionService;

    public PositionController() {
        this.positionService = new PositionService();
    }

    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    public Position getPositionById(int id) {
        return positionService.getPositionById(id);
    }

    public boolean addPosition(Position position) {
        return positionService.addPosition(position);
    }

    public boolean updatePosition(Position position) {
        return positionService.updatePosition(position);
    }

    public boolean deletePosition(int id) {
        return positionService.deletePosition(id);
    }

    public void displayPositionsAsTable() {
        List<Position> positions = getAllPositions();

        if (positions.isEmpty()) {
            System.out.println("Không có dữ liệu Position.");
            return;
        }

        System.out.println("\n" + "=".repeat(40));
        System.out.printf("| %-4s | %-15s |%n", "ID", "Position Name");
        System.out.println("=".repeat(40));

        for (Position position : positions) {
            System.out.println(position);
        }

        System.out.println("=".repeat(40));
    }
}
