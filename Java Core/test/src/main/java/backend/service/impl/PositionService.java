package backend.service.impl;

import backend.service.IPositionService;
import backend.repository.impl.PositionRepository;
import entity.Position;
import entity.PositionName;

import java.util.List;

public class PositionService implements IPositionService {
    private final PositionRepository positionRepository;

    public PositionService() {
        this.positionRepository = new PositionRepository();
    }

    public List<Position> getAllPositions() {
        return positionRepository.getAll();
    }

    public Position getPositionById(int id) {
        if (id <= 0) {
            return null;
        }
        return positionRepository.getById(id);
    }

    public boolean addPosition(Position position) {
        if (!isValid(position)) {
            return false;
        }
        if (isDuplicateName(position.getName(), -1)) {
            return false;
        }
        return positionRepository.add(position);
    }

    public boolean updatePosition(Position position) {
        if (position == null || position.getId() <= 0 || !isValid(position)) {
            return false;
        }
        if (isDuplicateName(position.getName(), position.getId())) {
            return false;
        }
        return positionRepository.update(position);
    }

    public boolean deletePosition(int id) {
        if (id <= 0) {
            return false;
        }
        return positionRepository.delete(id);
    }

    private boolean isValid(Position position) {
        return position != null && position.getName() != null;
    }

    private boolean isDuplicateName(PositionName name, int ignoreId) {
        if (name == null) {
            return true;
        }
        List<Position> positions = positionRepository.getAll();
        for (Position position : positions) {
            if (position != null
                    && position.getName() != null
                    && position.getName() == name
                    && position.getId() != ignoreId) {
                return true;
            }
        }
        return false;
    }
}
