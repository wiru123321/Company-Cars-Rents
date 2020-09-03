package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Fault;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaultRepository extends CrudRepository<Fault, Long> {
    Boolean existsByIsActiveAndCarAndDescription(Boolean isActive, Car car, String description);

    Fault findByIsActiveAndCarAndDescription(Boolean isActive, Car car, String description);

    List<Fault> findAllByIsActiveAndCar(Boolean isActive, Car car);

    List<Fault> findAllByIsActive(Boolean isActive);

    List<Fault> findAllByIsActiveAndCarLicensePlate(Boolean isActive, String carLicensePlate);
}
