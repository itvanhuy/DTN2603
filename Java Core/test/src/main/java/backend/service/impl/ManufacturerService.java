package backend.service.impl;

import backend.repository.impl.ManufacturerRepository;
import backend.service.IManufacturerService;
import entity.Manufacturer;

public class ManufacturerService implements IManufacturerService {
    private final ManufacturerRepository repository = new ManufacturerRepository();

    @Override
    public Manufacturer getManufacturerById(int id) {
        return id > 0 ? repository.getById(id) : null;
    }
}
