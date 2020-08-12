package com.euvic.carrental.responses;

import lombok.Data;

@Data
public class RentPermitRejectDTO {
    private String licensePlate;
    private String response;
}
