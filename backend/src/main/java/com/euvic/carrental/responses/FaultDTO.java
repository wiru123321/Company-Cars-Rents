package com.euvic.carrental.responses;

import com.euvic.carrental.model.Fault;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FaultDTO {
    private String description;
    private LocalDateTime faultDate;
    private Boolean setCarInactive;
    private String carLicensePlate;

    public FaultDTO() {

    }

    public FaultDTO(final String carLicensePlate, final String description, final LocalDateTime faultDate, final Boolean setCarInactive) {
        this.description = description;
        this.faultDate = faultDate;
        this.setCarInactive = setCarInactive;
        this.carLicensePlate = carLicensePlate;
    }

    public FaultDTO(final Fault fault, final String carLicensePlate) {
        this.description = fault.getDescription();
        this.faultDate = fault.getFaultDate();
        this.setCarInactive = fault.getSetCarInactive();
        this.carLicensePlate = carLicensePlate;
    }
}
