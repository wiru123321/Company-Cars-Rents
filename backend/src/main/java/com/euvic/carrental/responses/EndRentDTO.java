package com.euvic.carrental.responses;

import lombok.Data;

@Data
public class EndRentDTO {
    private ParkingDTO parkingDTO;
    private String faultMessage;

    public EndRentDTO() {
    }

    public EndRentDTO(final String faultMessage, final ParkingDTO parkingDTO) {
        this.parkingDTO = parkingDTO;
        this.faultMessage = faultMessage;
    }
}
