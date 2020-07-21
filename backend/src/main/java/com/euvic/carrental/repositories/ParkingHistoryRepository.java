package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Parking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingHistoryRepository extends CrudRepository<Parking, Long> {
    Parking getByTown(String town);
}
