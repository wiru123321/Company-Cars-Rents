package com.euvic.carrental.responses;

import com.euvic.carrental.model.Rent;
import lombok.Data;

import java.util.Date;

@Data
public class RentDTO {
    private Date dateFrom;
    private Date dateTo;
    private UserDTO userDTO;
    private CarDTO carDTO;
    private ParkingDTO parkingDTOFrom;
    private ParkingDTO parkingDTOTo;
    private Boolean isActive;

    public RentDTO(){

    }

    public RentDTO(Date dateFrom, Date dateTo, UserDTO userDTO, CarDTO carDTO, ParkingDTO parkingDTOFrom, ParkingDTO parkingDTOTo, Boolean isActive){
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.parkingDTOFrom = parkingDTOFrom;
        this.parkingDTOTo = parkingDTOTo;
        this.isActive = isActive;
    }

    public RentDTO(Rent rent, UserDTO userDTO, CarDTO carDTO, ParkingDTO parkingDTOFrom, ParkingDTO parkingDTOTo){
        this.dateFrom = rent.getDateFrom();
        this.dateTo = rent.getDateTo();
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.parkingDTOFrom = parkingDTOFrom;
        this.parkingDTOTo = parkingDTOTo;
        this.isActive = rent.getIsActive();
    }
}
