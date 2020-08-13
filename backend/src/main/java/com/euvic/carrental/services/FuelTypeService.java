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

    public FuelTypeService(final FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    public Long addEntityToDB(final FuelType fuelType) {
        return fuelTypeRepository.save(fuelType).getId();
    }

    @Override
    public FuelType getEntityByName(final String name) {
        return fuelTypeRepository.findByName(name);
    }

    @Override
    public FuelTypeDTO getDTOByName(final String name) {
        final FuelType fuelType = fuelTypeRepository.findByName(name);
        return new FuelTypeDTO(fuelType);
    }

    @Override
    public Long updateFuelTypeInDB(final String oldFuelTypeName, final FuelTypeDTO fuelTypeDTO) {
        final FuelType oldFuelType = this.getEntityByName(oldFuelTypeName);
        oldFuelType.setName(fuelTypeDTO.getName());
        return fuelTypeRepository.save(oldFuelType).getId();
    }

    @Override
    public FuelType mapRestModel(final Long id, final FuelTypeDTO model) {
        return new FuelType(id, model.getName());
    }

    @Override
    public List<FuelTypeDTO> getAllDTOs() {
        final ArrayList<FuelType> fuelTypeList = new ArrayList<>();
        fuelTypeRepository.findAll().forEach(fuelTypeList::add);

        return fuelTypeList.stream().map(FuelTypeDTO::new).collect(Collectors.toList());
    }

}
