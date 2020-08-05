package com.euvic.carrental.responses;

import com.euvic.carrental.model.*;
import com.euvic.carrental.responses.User.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentHistoryDTO {
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private UserDTO userDTO;
    private CarDTO carDTO;
    private ParkingHistoryDTO parkingHistoryDTOFrom;
    private ParkingHistoryDTO parkingHistoryDTOTo;
    private Boolean isActive;

    public RentHistoryDTO() {

    }

    public RentHistoryDTO(final UserDTO userDTO, final CarDTO carDTO, final LocalDateTime dateFrom, final LocalDateTime dateTo, final ParkingHistoryDTO parkingHistoryDTOFrom, final ParkingHistoryDTO parkingHistoryDTOTo, final Boolean isActive) {
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.parkingHistoryDTOFrom = parkingHistoryDTOFrom;
        this.parkingHistoryDTOTo = parkingHistoryDTOTo;
        this.isActive = isActive;
    }

    public RentHistoryDTO(final RentHistory rentHistory, final UserDTO userDTO, final CarDTO carDTO, final ParkingHistoryDTO parkingHistoryDTOFrom, final ParkingHistoryDTO parkingHistoryDTOTo) {
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.dateFrom = rentHistory.getDateFrom();
        this.dateTo = rentHistory.getDateTo();
        this.parkingHistoryDTOFrom = parkingHistoryDTOFrom;
        this.parkingHistoryDTOTo = parkingHistoryDTOTo;
        this.isActive = rentHistory.getIsActive();
    }
}