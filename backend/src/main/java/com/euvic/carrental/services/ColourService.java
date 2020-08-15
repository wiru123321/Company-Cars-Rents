package com.euvic.carrental.services;

import com.euvic.carrental.model.Colour;
import com.euvic.carrental.repositories.ColourRepository;
import com.euvic.carrental.responses.ColourDTO;
import com.euvic.carrental.services.interfaces.ColourServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColourService implements ColourServiceInterface {

    private final ColourRepository colourRepository;

    public ColourService(final ColourRepository colourRepository) {
        this.colourRepository = colourRepository;
    }

    @Override
    public Colour getEntityByName(final String name) {
        return colourRepository.getByName(name);
    }

    @Override
    public ColourDTO getDTOByName(final String name) {
        return new ColourDTO(colourRepository.getByName(name));
    }

    @Override
    public Long updateColourInDB(final String oldColourName, final ColourDTO colourDTO) {
        final Colour oldColour = this.getEntityByName(oldColourName);
        oldColour.setName(colourDTO.getName());
        return colourRepository.save(oldColour).getId();
    }

    @Override
    public Long addEntityToDB(final Colour colour) {
        return colourRepository.save(colour).getId();
    }

    @Override
    public Colour mapRestModel(final Long id, final ColourDTO colour) {
        return new Colour(id, colour.getName());
    }

    @Override
    public List<ColourDTO> getAllDTOs() {
        final ArrayList<Colour> colourList = new ArrayList<>();
        colourRepository.findAll().forEach(colourList::add);

        return colourList.stream()
                .map(ColourDTO::new)
                .collect(Collectors.toList());
    }
}
