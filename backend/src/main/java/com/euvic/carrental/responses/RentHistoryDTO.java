package com.euvic.carrental.responses;

import com.euvic.carrental.model.RentHistory;
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
    private Boolean isAccepted;
    private String comment;
    private String response;

    public RentHistoryDTO() {

    }

    public RentHistoryDTO(final UserDTO userDTO, final CarDTO carDTO, final LocalDateTime dateFrom, final LocalDateTime dateTo, final ParkingHistoryDTO parkingHistoryDTOFrom, final ParkingHistoryDTO parkingHistoryDTOTo, final Boolean isActive, final Boolean isAccepted, final String comment, final String response) {
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.parkingHistoryDTOFrom = parkingHistoryDTOFrom;
        this.parkingHistoryDTOTo = parkingHistoryDTOTo;
        this.isActive = isActive;
        this.isAccepted = isAccepted;
        this.comment = comment;
        this.response = response;
    }

    public RentHistoryDTO(final RentHistory rentHistory, final UserDTO userDTO, final CarDTO carDTO, final ParkingHistoryDTO parkingHistoryDTOFrom, final ParkingHistoryDTO parkingHistoryDTOTo) {
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.dateFrom = rentHistory.getDateFrom();
        this.dateTo = rentHistory.getDateTo();
        this.parkingHistoryDTOFrom = parkingHistoryDTOFrom;
        this.parkingHistoryDTOTo = parkingHistoryDTOTo;
        this.isActive = rentHistory.getIsActive();
        this.isAccepted = rentHistory.getIsAccepted();
        this.comment = rentHistory.getComment();
        this.response = rentHistory.getResponse();
    }
}