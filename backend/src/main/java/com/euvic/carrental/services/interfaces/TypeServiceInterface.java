package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Type;
import com.euvic.carrental.responses.TypeDTO;

import java.util.List;

public interface TypeServiceInterface {
    Type mapRestModel(Long id, TypeDTO model);

    Type getEntityByName(String name);

    TypeDTO getDTOByName(String name);

    Long addEntityToDB(Type type);

    List<TypeDTO> getAllDTOs();
}
