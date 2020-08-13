package com.euvic.carrental.responses;

import com.euvic.carrental.model.Rent;
import com.euvic.carrental.model.RentHistory;
import com.euvic.carrental.responses.User.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentDTO {
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String comment;
    private String response;
    private UserDTO userDTO;
    private CarDTO carDTO;
    private ParkingDTO parkingDTOFrom;
    private ParkingDTO parkingDTOTo;
    private Boolean isActive;

    public RentDTO() {
    }

    public RentDTO(final LocalDateTime dateFrom, final LocalDateTime dateTo, final ParkingDTO parkingDTO) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.parkingDTOTo = parkingDTO;
    }

    public RentDTO(final RentHistory rentHistory, final UserDTO userDTO, final CarDTO carDTO, final ParkingDTO parkingDTOFrom, final ParkingDTO parkingDTOTo) {
        this.dateFrom = rentHistory.getDateFrom();
        this.dateTo = rentHistory.getDateTo();
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.parkingDTOFrom = parkingDTOFrom;
        this.parkingDTOTo = parkingDTOTo;
        this.isActive = rentHistory.getIsActive();
        this.comment = rentHistory.getComment();
        this.response = rentHistory.getResponse();
    }

    public RentDTO(final LocalDateTime dateFrom, final LocalDateTime dateTo, final UserDTO userDTO, final CarDTO carDTO, final ParkingDTO parkingDTOFrom, final ParkingDTO parkingDTOTo, final Boolean isActive, final String comment, final String response) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.parkingDTOFrom = parkingDTOFrom;
        this.parkingDTOTo = parkingDTOTo;
        this.isActive = isActive;
        this.comment = comment;
        this.response = response;
    }

    public RentDTO(final Rent rent, final UserDTO userDTO, final CarDTO carDTO, final ParkingDTO parkingDTOFrom, final ParkingDTO parkingDTOTo) {
        this.dateFrom = rent.getDateFrom();
        this.dateTo = rent.getDateTo();
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.parkingDTOFrom = parkingDTOFrom;
        this.parkingDTOTo = parkingDTOTo;
        this.isActive = rent.getIsActive();
        this.comment = rent.getComment();
        this.response = rent.getResponse();
    }
}
