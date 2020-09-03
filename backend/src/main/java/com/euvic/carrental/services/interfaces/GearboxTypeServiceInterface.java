package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.GearboxType;
import com.euvic.carrental.responses.GearBoxTypeDTO;

import java.util.List;

public interface GearboxTypeServiceInterface {
    GearboxType getEntityByName(String name);

    GearboxType mapRestModel(Long id, GearBoxTypeDTO model);

    GearBoxTypeDTO getDTOByName(String name);
    
    Long addEntityToDB(GearboxType gearboxType);

    Long updateGearboxTypeInDB(String oldGearboxTypeName, GearBoxTypeDTO gearBoxTypeDTO);

    List<GearBoxTypeDTO> getAllDTOs();
}
