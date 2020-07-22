package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.GearboxType;
import com.euvic.carrental.responses.GearBoxTypeDTO;

import java.util.List;

public interface GearboxTypeServiceInterface {
    GearBoxTypeDTO getDTOByName(final String name);
    GearboxType mapRestModel(final GearBoxTypeDTO model);
    List<GearBoxTypeDTO> getAll();
}
