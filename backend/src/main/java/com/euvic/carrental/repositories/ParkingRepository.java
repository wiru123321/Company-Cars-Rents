package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Parking;
import org.springframework.data.repository.CrudRepository;

public interface ParkingRepository extends CrudRepository<Parking, Long> {
    Parking getByTown(String town);
    Parking findByTown(String town);
}
