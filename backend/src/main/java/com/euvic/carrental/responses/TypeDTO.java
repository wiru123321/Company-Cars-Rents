package com.euvic.carrental.responses;

import com.euvic.carrental.model.Type;
import lombok.Data;

@Data
public class TypeDTO {

    private String name;

    public TypeDTO(){

    }

    public TypeDTO(final Type entity){
        this.name = entity.getName();
    }

    public TypeDTO(final String name){
        this.name = name;
    }
}
