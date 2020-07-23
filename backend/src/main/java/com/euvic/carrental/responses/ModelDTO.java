package com.euvic.carrental.responses;

import com.euvic.carrental.model.Model;
import lombok.Data;

@Data
public class ModelDTO {

    private String name;
    private MarkDTO markDTO;

    public ModelDTO(){

    }

    public ModelDTO(final Model entity){
        this.name = entity.getName();
        this.markDTO = new MarkDTO(entity.getMark());
    }

    public ModelDTO(final String name, final MarkDTO markDTO){
        this.name = name;
        this.markDTO = markDTO;
    }
}
