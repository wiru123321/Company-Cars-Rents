package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Parking;
import com.euvic.carrental.responses.ParkingDTO;

import java.util.List;

public interface ParkingHistoryServiceInterface {
    Parking mapRestModel(final ParkingDTO parking);

    ParkingDTO getDTOByTown(String town);

    List<ParkingDTO> getAll();

    Long add(final ParkingDTO parking);
}
