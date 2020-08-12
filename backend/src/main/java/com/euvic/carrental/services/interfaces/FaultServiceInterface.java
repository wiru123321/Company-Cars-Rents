package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Fault;
import com.euvic.carrental.responses.FaultDTO;

import java.util.List;

public interface FaultServiceInterface {
    Fault mapRestModel(Long id, FaultDTO faultDTO);

    List<Fault> getAllEntitiesByCar(Car car);

    List<FaultDTO> getAllDTOsByCar(Car car);

    List<FaultDTO> getAllDTOs();

    Long addEntityToDB(Fault fault);

    Fault getEntityById(Long id);

    FaultDTO getDTOById(Long id);

    List<FaultDTO> getAllActiveFaultDTOs();

    Boolean checkIfCarFaultWithDescriptionExists(Car car, String description);

    Long setInactiveCarFaultWithDescription(Car car, String description);
}
