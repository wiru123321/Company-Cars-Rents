package com.euvic.carrental.responses;

import com.euvic.carrental.responses.User.UserRentInfo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentHistoryEndPendingDTO {
    private Long id;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private CarDTO carDTO;
    private String reasonForTheLoan;
    private String adminResponseForTheRequest;
    private String faultMessage;
    private UserRentInfo userRentInfo;
    private ParkingHistoryDTO parkingFrom;
    private ParkingHistoryDTO parkingTo;
}
