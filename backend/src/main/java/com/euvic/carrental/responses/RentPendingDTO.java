package com.euvic.carrental.responses;

import com.euvic.carrental.responses.User.UserRentInfo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentPendingDTO {
    private Long id;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private CarDTO carDTO;
    private String reasonForTheLoan;
    private String adminResponseForTheRequest;
    private UserRentInfo userRentInfo;
    private ParkingDTO parkingFrom;
    private ParkingDTO parkingTo;

    public RentPendingDTO() {
    }

    public RentPendingDTO(final Long id, final String reasonForTheLoan, final CarDTO carDTO, final LocalDateTime dateFrom, final LocalDateTime dateTo, final ParkingDTO parkingFrom
            , final ParkingDTO parkingTo, final UserRentInfo userRentInfo, final String adminResponseForTheRequest) {

        this.id = id;
        this.reasonForTheLoan = reasonForTheLoan;
        this.carDTO = carDTO;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.parkingFrom = parkingFrom;
        this.parkingTo = parkingTo;
        this.userRentInfo = userRentInfo;
        this.adminResponseForTheRequest = adminResponseForTheRequest;
    }
}
