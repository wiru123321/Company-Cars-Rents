package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Parking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParkingRepository extends CrudRepository<Parking, Long> {
    List<Parking> findByTown(String town);
}
