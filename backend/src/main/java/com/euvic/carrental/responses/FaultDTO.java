package com.euvic.carrental.responses;

import com.euvic.carrental.model.Fault;
import lombok.Data;

@Data
public class FaultDTO {
    private String description;
    private Boolean setCarInactive;
    private Boolean isActive;
    private String carLicensePlate;

    public FaultDTO() {

    }

    public FaultDTO(final String carLicensePlate, final String description, final Boolean setCarInactive, final Boolean isActive) {
        this.description = description;
        this.setCarInactive = setCarInactive;
        this.isActive = isActive;
        this.carLicensePlate = carLicensePlate;
    }

    public FaultDTO(final Fault fault, final String carLicensePlate) {
        this.description = fault.getDescription();
        this.setCarInactive = fault.getSetCarInactive();
        this.isActive = fault.getIsActive();
        this.carLicensePlate = carLicensePlate;
    }
}
