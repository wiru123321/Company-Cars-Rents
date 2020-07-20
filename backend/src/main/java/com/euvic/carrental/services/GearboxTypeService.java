package com.euvic.carrental.services;

import com.euvic.carrental.model.GearboxType;
import com.euvic.carrental.repositories.GearboxTypeRepository;
import com.euvic.carrental.responses.GearBoxTypeDTO;
import com.euvic.carrental.services.interfaces.GearboxTypeServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GearboxTypeService implements GearboxTypeServiceInterface {

    private final GearboxTypeRepository gearboxTypeRepository;

    public GearboxTypeService(GearboxTypeRepository gearboxTypeRepository) {
        this.gearboxTypeRepository = gearboxTypeRepository;
    }

    @Override
    public GearBoxTypeDTO getByName(final String name) {
        final GearboxType gearboxType = gearboxTypeRepository.findByName(name);
        return new GearBoxTypeDTO(gearboxType);
    }

    @Override
    public GearboxType mapRestModel(final GearBoxTypeDTO model) {
        return new GearboxType(null, model.getName());
    }

    @Override
    public List<GearBoxTypeDTO> getAll() {
        ArrayList<GearboxType> gearboxTypes = new ArrayList<>();
        gearboxTypeRepository.findAll().forEach(gearboxTypes::add);

        return gearboxTypes.stream().map(GearBoxTypeDTO::new).collect(Collectors.toList());
    }
}
