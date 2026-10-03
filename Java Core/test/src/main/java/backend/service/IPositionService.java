package backend.service;

import entity.Position;

import java.util.List;

public interface IPositionService {
    List<Position> getAllPositions();
    Position getPositionById(int id);
    boolean addPosition(Position position);
    boolean updatePosition(Position position);
    boolean deletePosition(int id);
}
