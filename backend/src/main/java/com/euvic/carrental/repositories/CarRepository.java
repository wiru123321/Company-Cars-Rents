package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Car findByLicensePlateAndIsOnCompany(String licensePlate, Boolean isOnCompany);
    List<Car> findAllByIsOnCompany(Boolean isOnCompany);
    List<Car> findAllByIsOnCompanyAndIsActive(Boolean isOnCompany, Boolean isActive);
    Boolean existsByLicensePlateAndIsOnCompany(String licensePlate, Boolean isOnCompany);
}
