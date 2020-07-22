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
    public ColourDTO getDTOByName(final String name) {
        return new ColourDTO(colourRepository.getByName(name));
    }

    @Override
    public Long add(final ColourDTO colour) {
        return colourRepository.save(this.mapRestModel(colour)).getId();
    }

    @Override
    public Colour mapRestModel(final ColourDTO colour) {
        return new Colour(null, colour.getName());
    }

    @Override
    public List<ColourDTO> getAll() {
        final ArrayList<Colour> colourList = new ArrayList<>();
        colourRepository.findAll().forEach(colourList::add);

        return colourList.stream()
                .map(ColourDTO::new)
                .collect(Collectors.toList());
    }
}
