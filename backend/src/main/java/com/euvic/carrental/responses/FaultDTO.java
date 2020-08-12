package com.euvic.carrental.responses;

import com.euvic.carrental.model.Fault;
import lombok.Data;

@Data
public class FaultDTO {
    private String describe;
    private Boolean setCarInactive;
    private Boolean isActive;
    private CarDTO carDTO;

    public FaultDTO() {

    }

    public FaultDTO(final CarDTO carDTO, final String describe, final Boolean setCarInactive, final Boolean isActive) {
        this.carDTO = carDTO;
        this.describe = describe;
        this.setCarInactive = setCarInactive;
        this.isActive = isActive;
    }

    public FaultDTO(final Fault fault, final CarDTO carDTO) {
        this.carDTO = carDTO;
        this.describe = fault.getDescribe();
        this.setCarInactive = fault.getSetCarInactive();
        this.isActive = fault.getIsActive();
    }
}
