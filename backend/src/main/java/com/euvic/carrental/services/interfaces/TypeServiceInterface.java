package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Type;
import com.euvic.carrental.responses.TypeDTO;

import java.util.List;

public interface TypeServiceInterface {
    Type mapRestModel(final TypeDTO model);
    Type getEntityByName(final String name);
    TypeDTO getDTOByName(String name);
    List<TypeDTO> getAllDTOs();
}
