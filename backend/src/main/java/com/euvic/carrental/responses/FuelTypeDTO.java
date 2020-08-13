package com.euvic.carrental.responses;

import com.euvic.carrental.model.FuelType;
import lombok.Data;

@Data
public class FuelTypeDTO {

    private String name;

    public FuelTypeDTO(){

    }

    public FuelTypeDTO(final FuelType entity){
        this.name = entity.getName();
    }

    public FuelTypeDTO(final String name){
        this.name = name;
    }
}
