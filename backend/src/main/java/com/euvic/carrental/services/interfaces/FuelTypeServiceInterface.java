package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.FuelType;
import com.euvic.carrental.responses.FuelTypeDTO;

import java.util.List;

public interface FuelTypeServiceInterface {
    FuelType mapRestModel(final FuelTypeDTO model);
    FuelType getEntityByName(final String name);
    FuelTypeDTO getDTOByName(final String name);
    List<FuelTypeDTO> getAll();
}
