package com.euvic.carrental.services;

import com.euvic.carrental.model.Mark;
import com.euvic.carrental.repositories.MarkRepository;
import com.euvic.carrental.responses.MarkDTO;
import com.euvic.carrental.services.interfaces.MarkServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarkService implements MarkServiceInterface {

    private final MarkRepository markRepository;

    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    public Mark mapRestModel(MarkDTO model) {
        return new Mark(null, model.getName());
    }

    @Override
    public MarkDTO getDTOByName(String name) {
        final Mark mark = markRepository.findByName(name);
        return new MarkDTO(mark);
    }

    @Override
    public Mark getEntityByName(String name) {
        return markRepository.findByName(name);
    }

    @Override
    public List<MarkDTO> getAll() {
        final ArrayList<Mark> markList = new ArrayList<>();
        markRepository.findAll().forEach(markList::add);

        return markList.stream().map(MarkDTO::new).collect(Collectors.toList());
    }
}
