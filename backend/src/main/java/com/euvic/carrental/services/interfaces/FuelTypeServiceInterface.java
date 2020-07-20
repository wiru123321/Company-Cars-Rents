package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.FuelType;
import com.euvic.carrental.responses.FuelTypeDTO;

import java.util.List;

public interface FuelTypeServiceInterface {
    FuelType mapRestModel(final FuelTypeDTO model);
    FuelTypeDTO getByName(String name);
    List<FuelTypeDTO> getAll();
}
