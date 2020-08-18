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
    private String reasonForTheLoan;
    private String adminResponseForTheRequest;
    private String faultMessage;

    public RentHistoryDTO() {

    }

    public RentHistoryDTO(final RentHistory rentHistory, final UserDTO userDTO, final CarDTO carDTO, final ParkingHistoryDTO parkingHistoryDTOFrom, final ParkingHistoryDTO parkingHistoryDTOTo) {
        this.userDTO = userDTO;
        this.carDTO = carDTO;
        this.dateFrom = rentHistory.getDateFrom();
        this.dateTo = rentHistory.getDateTo();
        this.parkingHistoryDTOFrom = parkingHistoryDTOFrom;
        this.parkingHistoryDTOTo = parkingHistoryDTOTo;
        this.reasonForTheLoan = rentHistory.getReasonForTheLoan();
        this.adminResponseForTheRequest = rentHistory.getAdminResponseForTheRequest();
        this.faultMessage = rentHistory.getFaultMessage();
    }
}