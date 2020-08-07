package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Colour;
import com.euvic.carrental.responses.ColourDTO;

import java.util.List;

public interface ColourServiceInterface {
    Colour getEntityByName(String name);

    ColourDTO getDTOByName(String name);

    Long addEntityToDB(Colour colour);

    Colour mapRestModel(Long id, ColourDTO colour);

    Long updateColourInDB(String oldColourName, ColourDTO colourDTO);

    List<ColourDTO> getAllDTOs();
}
