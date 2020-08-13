package com.euvic.carrental.repositories;

import com.euvic.carrental.model.Parking;
import com.euvic.carrental.model.ParkingHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingHistoryRepository extends CrudRepository<ParkingHistory, Long> {
    List<ParkingHistory> findByTown(String town);
}
