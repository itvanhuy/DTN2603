package backend.controller;

import backend.service.IManufacturerService;
import backend.service.impl.ManufacturerService;
import entity.Manufacturer;

public class ManufacturerController {
    private final IManufacturerService service = new ManufacturerService();

    public Manufacturer getManufacturerById(int id) {
        return service.getManufacturerById(id);
    }
}
