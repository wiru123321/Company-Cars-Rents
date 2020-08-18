package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.ParkingHistory;
import com.euvic.carrental.responses.ParkingHistoryDTO;

import java.util.List;

public interface ParkingHistoryServiceInterface {
    Long addEntityToDB(ParkingHistory parkingHistory);

    ParkingHistory getEntityById(Long id);

    ParkingHistory mapRestModel(Long id, ParkingHistoryDTO parkingHistoryDTO);

    ParkingHistoryDTO getDTOById(Long id);

    List<ParkingHistoryDTO> getAllDTOsByTownName(String town);

    List<ParkingHistoryDTO> getAllDTOs();
}
