package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Type;
import com.euvic.carrental.responses.TypeDTO;

import java.util.List;

public interface TypeServiceInterface {
    Long addEntityToDB(Type type);

    Long updateTypeInDB(String oldTypeName, TypeDTO typeDTO);

    Type mapRestModel(Long id, TypeDTO model);

    Type getEntityByName(String name);

    TypeDTO getDTOByName(String name);

    List<TypeDTO> getAllDTOs();
}
