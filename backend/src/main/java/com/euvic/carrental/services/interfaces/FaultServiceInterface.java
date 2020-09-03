package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Fault;
import com.euvic.carrental.responses.FaultDTO;

import java.util.List;

public interface FaultServiceInterface {
    Boolean checkIfCarFaultWithDescriptionExists(Car car, String description);

    Long setInactiveCarFaultWithDescription(Car car, String description);

    Long addEntityToDB(Fault fault);

    Fault mapRestModel(Long id, FaultDTO faultDTO);

    Fault getEntityById(Long id);
    
    FaultDTO getDTOById(Long id);

    List<Fault> getAllActiveEntitiesByCar(Car car);

    List<FaultDTO> getAllActiveDTOsByCar(Car car);

    List<FaultDTO> getAllActiveFaultDTOsByCarLicensePlate(String licensePlate);

    List<FaultDTO> getAllActiveFaultDTOs();

    List<FaultDTO> getAllDTOs();

    List<Long> setAllFaultsAsInactiveForCertainCar(String licensePlate);
}
