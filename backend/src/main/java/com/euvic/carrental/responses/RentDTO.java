package com.euvic.carrental.responses;

import com.euvic.carrental.model.Rent;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentDTO {
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private UserDTO userDTO;
    private CarDTO carDTO;
    private ParkingDTO parkingDTOFrom;
    private ParkingDTO parkingDTOTo;
    private Boolean isActive;

    public RentDTO() {

    }

    public RentDTO(final LocalDateTime dateFrom, final LocalDateTime dateTo, final UserDTO userDTO, final CarDTO carDTO, final ParkingDTO parkingDTOFrom, final ParkingDTO parkingDTOTo, final Boolean isActive) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.parkingDTOFrom = parkingDTOFrom;
        this.parkingDTOTo = parkingDTOTo;
        this.isActive = isActive;
    }

    public RentDTO(final Rent rent, final UserDTO userDTO, final CarDTO carDTO, final ParkingDTO parkingDTOFrom, final ParkingDTO parkingDTOTo) {
        this.dateFrom = rent.getDateFrom();
        this.dateTo = rent.getDateTo();
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.parkingDTOFrom = parkingDTOFrom;
        this.parkingDTOTo = parkingDTOTo;
        this.isActive = rent.getIsActive();
    }
}
