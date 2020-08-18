package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Colour;
import com.euvic.carrental.responses.ColourDTO;

import java.util.List;

public interface ColourServiceInterface {
    Colour getEntityByName(String name);

    Colour mapRestModel(Long id, ColourDTO colour);

    Long addEntityToDB(Colour colour);

    Long updateColourInDB(String oldColourName, ColourDTO colourDTO);

    ColourDTO getDTOByName(String name);

    List<ColourDTO> getAllDTOs();
}
