package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Rent;
import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.*;

import java.time.LocalDateTime;
import java.util.List;

public interface RentServiceInterface {
    Long addEntityToDB(Rent rent);

    void deleteRent(Rent rent);

    void updateNextRent(Rent rent);

    void deleteRentsByUser(User user);

    void deleteAndUpdateRentAndParkings(Rent rent, ParkingDTO parkingDTO);

    boolean checkMyRentsBeforeAddNewRent(RentDTO rentDTO);

    boolean checkIfRentIsAllowedToBeRequested(Rent rent);

    Rent getEntityById(Long id);

    Rent getEntityByCarAndDateFrom(Car car, LocalDateTime dateFrom);

    Rent mapRestModel(Long id, RentDTO rentDTO, Long parkingFromId, Long parkingToId);

    RentPendingDTO getRentPendingDTOById(Long id);

    RentDTO getDTOById(Long id);

    RentDTO getDTOByCarDTOAndDateFrom(CarDTO carDTO, LocalDateTime dateFrom);

    List<Rent> getActiveRentsByLicensePlate(String licensePlate);

    List<CarDTO> getActiveCarsBetweenDates(DateFromDateTo dateFromDateTo);

    List<RentDTO> getAllDTOs();

    List<RentPendingDTO> getAllPendingRents();

    List<RentPendingDTO> getAllActiveRents();

    List<RentPendingDTO> getUserInactiveRentDTOs();

    List<RentPendingDTO> getUserActiveRentDTOs();
}
