package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Parking;
import com.euvic.carrental.model.ParkingHistory;
import com.euvic.carrental.responses.ParkingDTO;
import com.euvic.carrental.responses.ParkingHistoryDTO;

import java.util.List;

public interface ParkingHistoryServiceInterface {
    ParkingHistory mapRestModel(Long id, ParkingHistoryDTO parkingHistoryDTO);

    List<ParkingHistoryDTO> getAllDTOsByTownName(String town);

    List<ParkingHistoryDTO> getAllDTOs();

    Long addEntityToDB(ParkingHistory parkingHistory);

    ParkingHistory getEntityById(Long id);

    ParkingHistoryDTO getDTOById(Long id);
}
