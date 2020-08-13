package com.euvic.carrental.responses;

import com.euvic.carrental.model.GearboxType;
import lombok.Data;

@Data
public class GearBoxTypeDTO {

    private String name;

    public GearBoxTypeDTO(){

    }

    public GearBoxTypeDTO(final GearboxType entity){
        this.name = entity.getName();
    }

    public GearBoxTypeDTO(final String name) {
        this.name = name;
    }

}
