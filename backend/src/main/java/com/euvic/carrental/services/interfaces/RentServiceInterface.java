package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Rent;
import com.euvic.carrental.responses.CarDTO;
import com.euvic.carrental.responses.DateFromDateTo;
import com.euvic.carrental.responses.RentDTO;
import com.euvic.carrental.responses.RentPendingDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface RentServiceInterface {
    Long addEntityToDB(Rent rent);

    Rent getEntityById(Long id);

    Rent getEntityByCarAndDateFrom(Car car, LocalDateTime dateFrom);

    RentDTO getDTOById(Long id);

    RentDTO getDTOByCarDTOAndDateFrom(CarDTO carDTO, LocalDateTime dateFrom);

    Rent mapRestModel(Long id, RentDTO rentDTO, Long parkingFromId, Long parkingToId);

    List<CarDTO> getActiveCarsBetweenDates(DateFromDateTo dateFromDateTo);

    List<RentPendingDTO> getAllPendingRents();

    List<RentDTO> getAllDTOs();

    List<RentDTO> getUserInactiveRentDTOs();

    List<RentDTO> getUserActiveRentDTOs();

    void deleteRent(Rent rent);

    boolean checkMyRentsBeforeAddNewRent(RentDTO rentDTO);
}
