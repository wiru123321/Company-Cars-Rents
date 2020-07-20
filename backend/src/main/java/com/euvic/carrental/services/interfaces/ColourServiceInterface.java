package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Colour;
import com.euvic.carrental.responses.ColourDTO;

import java.util.List;

public interface ColourServiceInterface {
    ColourDTO getByName(final String name);

    ColourDTO getById(final Long id);

    Long add(final ColourDTO colour);

    Colour mapRestModel(final ColourDTO colour);

    List<ColourDTO> getAll();
}
