package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Fault;
import com.euvic.carrental.responses.FaultDTO;

import java.util.List;

public interface FaultServiceInterface {
    Fault mapRestModel(final FaultDTO faultDTO);

    List<Fault> getEntitiesByCar(final Car car);

    List<FaultDTO> getDTOByCar(final Car car);

    void add(final FaultDTO faultDTO);

    List<FaultDTO> getAll();
}
