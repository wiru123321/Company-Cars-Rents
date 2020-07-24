package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Parking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingHistoryRepository extends CrudRepository<Parking, Long> {
    List<Parking> findByTown(String town);
}
