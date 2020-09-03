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
    private String reasonForTheLoan;
    private String adminResponseForTheRequest;
    private String faultMessage;
    private UserDTO userDTO;
    private CarDTO carDTO;
    private ParkingDTO parkingDTOFrom;
    private ParkingDTO parkingDTOTo;

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
        this.reasonForTheLoan = rentHistory.getReasonForTheLoan();
        this.adminResponseForTheRequest = rentHistory.getAdminResponseForTheRequest();
        this.faultMessage = rentHistory.getFaultMessage();
    }

    public RentDTO(final LocalDateTime dateFrom, final LocalDateTime dateTo, final UserDTO userDTO, final CarDTO carDTO, final ParkingDTO parkingDTOFrom, final ParkingDTO parkingDTOTo, final String reasonForTheLoan, final String adminResponseForTheRequest, final String faultMessage) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.parkingDTOFrom = parkingDTOFrom;
        this.parkingDTOTo = parkingDTOTo;
        this.reasonForTheLoan = reasonForTheLoan;
        this.adminResponseForTheRequest = adminResponseForTheRequest;
        this.faultMessage = faultMessage;
    }

    public RentDTO(final Rent rent, final UserDTO userDTO, final CarDTO carDTO, final ParkingDTO parkingDTOFrom, final ParkingDTO parkingDTOTo) {
        this.dateFrom = rent.getDateFrom();
        this.dateTo = rent.getDateTo();
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.parkingDTOFrom = parkingDTOFrom;
        this.parkingDTOTo = parkingDTOTo;
        this.reasonForTheLoan = rent.getReasonForTheLoan();
        this.adminResponseForTheRequest = rent.getAdminResponseForTheRequest();
        this.faultMessage = rent.getFaultMessage();
    }
}
