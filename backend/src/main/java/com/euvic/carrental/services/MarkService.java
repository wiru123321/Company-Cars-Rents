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

    public MarkService(final MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    public Mark mapRestModel(final Long id, final MarkDTO model) {
        return new Mark(id, model.getName());
    }

    @Override
    public MarkDTO getDTOByName(final String name) {
        final Mark mark = markRepository.findByName(name);
        return new MarkDTO(mark);
    }

    @Override
    public Mark getEntityByName(final String name) {
        return markRepository.findByName(name);
    }

    @Override
    public Long addEntityToDB(final Mark mark) {
        return markRepository.save(mark).getId();
    }

    @Override
    public Long updateMarkInDB(final String oldMarkName, final MarkDTO markDTO) {
        final Mark oldMark = this.getEntityByName(oldMarkName);
        oldMark.setName(markDTO.getName());
        return markRepository.save(oldMark).getId();
    }

    @Override
    public List<MarkDTO> getAllDTOs() {
        final ArrayList<Mark> markList = new ArrayList<>();
        markRepository.findAll().forEach(markList::add);

        return markList.stream().map(MarkDTO::new).collect(Collectors.toList());
    }
}
