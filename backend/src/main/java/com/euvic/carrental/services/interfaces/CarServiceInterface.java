package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.responses.CarDTO;

import java.util.List;

public interface CarServiceInterface {
    Car mapRestModel(Long id, CarDTO carDTO, Long parkingId);

    Car getEntityByLicensePlate(String licensePlate);

    CarDTO getDTOByLicensePlate(String licensePlate);

    List<CarDTO> getAllDTOs();

    Long addEntityToDB(Car car);
}
