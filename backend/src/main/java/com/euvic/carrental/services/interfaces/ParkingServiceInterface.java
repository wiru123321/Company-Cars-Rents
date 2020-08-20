package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Parking;
import com.euvic.carrental.responses.ParkingDTO;

import java.util.List;

public interface ParkingServiceInterface {
    Long addEntityToDB(Parking parking);

    Long choosesNotNullParkingAndAddToDB(ParkingDTO parkingDTOFromCar, ParkingDTO parkingDTOFromUser);

    boolean checkIfParkingExist(Parking parking);

    void updateParkingInDb(Long oldParkingId, ParkingDTO newParkingDTO);

    void deleteParkingById(Long id);

    Parking mapRestModel(Long id, ParkingDTO parking);

    Parking getEntityById(Long id);

    ParkingDTO getDTOById(Long id);

    List<ParkingDTO> getAllDTOsByTownName(String town);

    List<ParkingDTO> getAllDTOs();
}
