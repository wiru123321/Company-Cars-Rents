package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Car findByLicensePlate(String licensePlate);
}
