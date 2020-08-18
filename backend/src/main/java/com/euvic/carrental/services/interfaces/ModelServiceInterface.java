package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Model;
import com.euvic.carrental.responses.ModelDTO;

import java.util.List;

public interface ModelServiceInterface {
    void updateModelInDb(Long oldModelId, ModelDTO newModelDTO);

    Long addEntityToDB(Model model);

    Long updateModelInDbFromFront(String oldModelName, ModelDTO newModelDTO);

    Model mapRestModel(Long id, ModelDTO modelDTO);

    Model getEntityByName(String name);

    Model getEntityById(Long id);

    ModelDTO getDTOByName(String name);

    List<ModelDTO> getAllDTOs();
}
