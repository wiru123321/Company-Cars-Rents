package com.euvic.carrental.services;

import com.euvic.carrental.model.Mark;
import com.euvic.carrental.model.Model;
import com.euvic.carrental.repositories.ModelRepository;
import com.euvic.carrental.responses.MarkDTO;
import com.euvic.carrental.responses.ModelDTO;
import com.euvic.carrental.services.interfaces.ModelServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelService implements ModelServiceInterface {

    private final ModelRepository modelRepository;
    private final MarkService markService;

    public ModelService(final ModelRepository modelRepository, final MarkService markService) {
        this.modelRepository = modelRepository;
        this.markService = markService;
    }

    @Override
    public void updateModelInDb(final Long oldModelId, final ModelDTO newModelDTO) {
        final Model updatedModel = this.mapRestModel(oldModelId, newModelDTO);
        modelRepository.save(updatedModel);
    }

    @Override
    public Long addEntityToDB(final Model model) {
        final List<Model> modelList = modelRepository.findAllByName(model.getName());

        for (final Model m : modelList) {
            if (model.getMark().getName().equals(m.getMark().getName())) {
                return m.getId();
            }
        }
        return modelRepository.save(model).getId();
    }

    @Override
    public Long updateModelInDbFromFront(final String oldModelName, final ModelDTO newModelDTO) {
        final Model oldModel = this.getEntityByName(oldModelName);
        final Mark mark = markService.getEntityByName(newModelDTO.getMarkDTO().getName());
        final Long id;
        if (mark != null) {
            oldModel.setMark(mark);
            oldModel.setName(newModelDTO.getName());
            id = modelRepository.save(oldModel).getId();
        } else {
            throw new NullPointerException();
        }
        return id;
    }

    @Override
    public Model mapRestModel(final Long id, final ModelDTO modelDTO) {
        return new Model(id, modelDTO.getName(), markService.getEntityByName(modelDTO.getMarkDTO().getName()));
    }

    @Override
    public Model getEntityByName(final String name) {
        return modelRepository.findByName(name);
    }

    @Override
    public Model getEntityById(final Long id) {
        return modelRepository.findById(id).get();
    }

    @Override
    public ModelDTO getDTOByName(final String name) {
        final Model model = modelRepository.findByName(name);
        final Mark modelMark = model.getMark();
        return new ModelDTO(model.getName(), markService.getDTOByName(modelMark.getName()));
    }

    @Override
    public List<ModelDTO> getAllDTOs() {
        final ArrayList<Model> modelList = new ArrayList<>();
        modelRepository.findAll().forEach(modelList::add);

        final ArrayList<ModelDTO> modelDTOList = new ArrayList<>();
        modelList.forEach((model) -> {
            final ModelDTO modelDTO = new ModelDTO(model.getName(), new MarkDTO(model.getMark().getName()));
            modelDTOList.add(modelDTO);
        });

        return modelDTOList;
    }
}
