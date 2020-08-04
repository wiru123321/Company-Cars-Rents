package com.euvic.carrental.responses;

import com.euvic.carrental.model.Colour;
import lombok.Data;

@Data
public class ColourDTO {

    private String name;

    public ColourDTO() {
    }

    public ColourDTO(final String name) {
        this.name = name;
    }

    public ColourDTO(final Colour entity) {
        this.name = entity.getName();
    }
}
