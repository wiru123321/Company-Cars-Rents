package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
    Car findByLicensePlate(String licensePlate);
}
