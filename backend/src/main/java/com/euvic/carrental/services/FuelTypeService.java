package com.euvic.carrental.services;

import com.euvic.carrental.model.FuelType;
import com.euvic.carrental.repositories.FuelTypeRepository;
import com.euvic.carrental.responses.FuelTypeDTO;
import com.euvic.carrental.services.interfaces.FuelTypeServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelTypeService implements FuelTypeServiceInterface {

    private final FuelTypeRepository fuelTypeRepository;

    public FuelTypeService(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    public FuelType getEntityByName(String name) {
        return fuelTypeRepository.findByName(name);
    }

    @Override
    public FuelTypeDTO getDTOByName(final String name) {
        final FuelType fuelType = fuelTypeRepository.findByName(name);
        return new FuelTypeDTO(fuelType);
    }

    @Override
    public FuelType mapRestModel(final FuelTypeDTO model) {
        return new FuelType(null, model.getName());
    }

    @Override
    public List<FuelTypeDTO> getAllDTOs() {
        final ArrayList<FuelType> fuelTypeList = new ArrayList<>();
        fuelTypeRepository.findAll().forEach(fuelTypeList::add);

        return fuelTypeList.stream().map(FuelTypeDTO::new).collect(Collectors.toList());
    }
}
