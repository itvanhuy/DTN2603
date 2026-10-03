package backend.repository;

import entity.Manufacturer;

public interface IManufacturerRepository {
    Manufacturer getById(int id);
}
