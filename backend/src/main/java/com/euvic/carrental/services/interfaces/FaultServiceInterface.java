package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Fault;
import com.euvic.carrental.model.Parking;
import com.euvic.carrental.responses.CarDTO;
import com.euvic.carrental.responses.FaultDTO;
import com.euvic.carrental.responses.ParkingDTO;

import java.util.List;

public interface FaultServiceInterface {
    Fault mapRestModel(final FaultDTO faultDTO);

    List<Fault> getAllEntitiesByCar(final Car car);

    List<FaultDTO> getAllDTOsByCar(final Car car);

    List<FaultDTO> getAllDTOs();

    Long addEntityToDB(final Fault fault);

    Fault getEntityById(Long id);

    FaultDTO getDTOById(Long id);
}
