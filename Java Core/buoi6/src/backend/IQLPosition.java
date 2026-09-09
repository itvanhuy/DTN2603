package backend;

import entity.Position;
import java.util.List;

public interface IQLPosition {
    List<Position> getAllPositions();
    Position getPositionById(int id);
    boolean addPosition(Position position);
    boolean updatePosition(Position position);
    boolean deletePosition(int id);
    void displayPositionsAsTable();
}