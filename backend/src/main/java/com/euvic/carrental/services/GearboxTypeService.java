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

    public GearboxTypeService(final GearboxTypeRepository gearboxTypeRepository) {
        this.gearboxTypeRepository = gearboxTypeRepository;
    }

    @Override
    public GearboxType mapRestModel(final Long id, final GearBoxTypeDTO model) {
        return new GearboxType(id, model.getName());
    }

    @Override
    public Long addEntityToDB(final GearboxType gearboxType) {
        return gearboxTypeRepository.save(gearboxType).getId();
    }

    @Override
    public GearboxType getEntityByName(final String name) {
        return gearboxTypeRepository.findByName(name);
    }

    @Override
    public GearBoxTypeDTO getDTOByName(final String name) {
        final GearboxType gearboxType = gearboxTypeRepository.findByName(name);
        return new GearBoxTypeDTO(gearboxType);
    }

    @Override
    public List<GearBoxTypeDTO> getAllDTOs() {
        final ArrayList<GearboxType> gearboxTypes = new ArrayList<>();
        gearboxTypeRepository.findAll().forEach(gearboxTypes::add);

        return gearboxTypes.stream().map(GearBoxTypeDTO::new).collect(Collectors.toList());
    }
}
