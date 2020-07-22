package com.euvic.carrental.responses;

import lombok.Data;

@Data
public class ModelDTO {

    private String name;
    private MarkDTO markDTO;

    public ModelDTO(){

    }

    public ModelDTO(final ModelDTO entity){
        this.name = entity.getName();
        this.markDTO = entity.getMarkDTO();
    }

    public ModelDTO(final String name, final MarkDTO markDTO){
        this.name = name;
        this.markDTO = markDTO;
    }
}
