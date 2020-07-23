package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.responses.CarDTO;

import java.util.List;

public interface CarServiceInterface {
    Car mapRestModel(final CarDTO carDTO);
    Car getEntityByLicensePlate(String licensePlate);
    CarDTO getDTOByLicensePlate(String licensePlate);
    List<CarDTO> getAll();
    void add(CarDTO car);
}
