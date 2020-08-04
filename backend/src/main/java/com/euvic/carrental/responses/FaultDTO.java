package com.euvic.carrental.responses;

import com.euvic.carrental.model.Fault;
import lombok.Data;

@Data
public class FaultDTO {
    private String describe;
    private Boolean isActive;
    private CarDTO carDTO;

    public FaultDTO() {

    }

    public FaultDTO(final CarDTO carDTO, final String describe, final Boolean isActive) {
        this.carDTO = carDTO;
        this.describe = describe;
        this.isActive = isActive;
    }
}
