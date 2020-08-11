package com.euvic.carrental.responses;

import com.euvic.carrental.responses.User.UserRentInfo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentPermitDTO {
    private Long id;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private CarDTO carDTO;
    private String comment;
    private UserRentInfo userRentInfo;
    private ParkingDTO parkingFrom;
    private ParkingDTO parkingTo;
}
