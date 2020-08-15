package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.RentHistory;
import com.euvic.carrental.responses.CarDTO;
import com.euvic.carrental.responses.RentHistoryDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface RentHistoryServiceInterface {
    Long addEntityToDB(RentHistory rentHistory);

    RentHistory getEntityById(Long id);

    RentHistory getEntityByCarAndDateFrom(Car car, LocalDateTime dateFrom);

    RentHistoryDTO getDTOById(Long id);

    RentHistoryDTO getDTOByCarDTOAndDateFrom(CarDTO carDTO, LocalDateTime dateFrom);

    RentHistory mapRestModel(Long id, RentHistoryDTO rentHistoryDTO, Long parkingHistoryFromId, Long parkingHistoryToId);

    List<RentHistoryDTO> getAllDTOs();

    List<RentHistoryDTO> getUserRentHistoryDTOs();
}
