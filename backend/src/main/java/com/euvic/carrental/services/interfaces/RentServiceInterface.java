package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Rent;
import com.euvic.carrental.responses.CarDTO;
import com.euvic.carrental.responses.RentDTO;

import java.util.Date;
import java.util.List;

public interface RentServiceInterface {
    Long addEntityToDB(Rent rent);

    Rent getEntityById(Long id);

    Rent getEntityByCarAndDateFrom(Car car, Date dateFrom);

    RentDTO getDTOById(Long id);

    RentDTO getDTOByCarDTOAndDateFrom(CarDTO carDTO, Date dateFrom);

    Rent mapRestModel(Long id, RentDTO rentDTO, Long parkingFromId, Long parkingToId);

    List<RentDTO> getAllDTOs();
}
