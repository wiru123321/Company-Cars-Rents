package com.euvic.carrental.responses;

import com.euvic.carrental.model.Mark;
import lombok.Data;

@Data
public class MarkDTO {

    private String name;

    public MarkDTO(){

    }

    public MarkDTO(final Mark entity){
        this.name = entity.getName();
    }

    public MarkDTO(final String name){
        this.name = name;
    }
}
