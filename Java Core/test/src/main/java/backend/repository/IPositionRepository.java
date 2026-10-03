package backend.repository;

import entity.Position;
import java.util.List;

public interface IPositionRepository {
    List<Position> getAll();
    Position getById(int id);
    boolean add(Position position);
    boolean update(Position position);
    boolean delete(int id);
}
