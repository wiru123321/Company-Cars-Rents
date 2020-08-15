package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Parking;
import com.euvic.carrental.responses.ParkingDTO;

import java.util.List;

public interface ParkingServiceInterface {
    Parking mapRestModel(Long id, ParkingDTO parking);

    List<ParkingDTO> getAllDTOsByTownName(String town);

    List<ParkingDTO> getAllDTOs();

    Long addEntityToDB(Parking parking);

    Parking getEntityById(Long id);

    ParkingDTO getDTOById(Long id);

    void updateParkingInDb(Long oldParkingId, ParkingDTO newParkingDTO);
}
