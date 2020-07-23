package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Colour;
import com.euvic.carrental.model.GearboxType;
import com.euvic.carrental.responses.ColourDTO;

import java.util.List;

public interface ColourServiceInterface {
    Colour getEntityByName(final String name);
    ColourDTO getDTOByName(final String name);

    Long add(final ColourDTO colour);

    Colour mapRestModel(final ColourDTO colour);

    List<ColourDTO> getAll();
}
