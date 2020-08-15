package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Fault;
import com.euvic.carrental.responses.FaultDTO;

import java.util.List;

public interface FaultServiceInterface {
    Fault mapRestModel(Long id, FaultDTO faultDTO);

    List<Fault> getAllActiveEntitiesByCar(Car car);

    List<FaultDTO> getAllActiveDTOsByCar(Car car);

    List<FaultDTO> getAllDTOs();

    Long addEntityToDB(Fault fault);

    Fault getEntityById(Long id);

    FaultDTO getDTOById(Long id);

    List<FaultDTO> getAllActiveFaultDTOs();

    Boolean checkIfCarFaultWithDescriptionExists(Car car, String description);

    Long setInactiveCarFaultWithDescription(Car car, String description);

    List<FaultDTO> getAllActiveFaultDTOsByCarLicensePlate(String licensePlate);

    List<Long> setAllFaultsAsInactiveForCertainCar(String licensePlate);
}
