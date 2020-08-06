package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.GearboxType;
import com.euvic.carrental.responses.GearBoxTypeDTO;

import java.util.List;

public interface GearboxTypeServiceInterface {
    GearboxType getEntityByName(String name);

    GearBoxTypeDTO getDTOByName(String name);

    GearboxType mapRestModel(Long id, GearBoxTypeDTO model);

    Long addEntityToDB(GearboxType gearboxType);

    List<GearBoxTypeDTO> getAllDTOs();
}
