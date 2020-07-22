package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Model;
import com.euvic.carrental.responses.ModelDTO;

import java.util.List;

public interface ModelServiceInterface {
    Model mapRestModel(final ModelDTO modelDTO);

    Model getEntityByName(String name);

    ModelDTO getDTOByName(String name);

    void add(ModelDTO modelDTO);

    List<ModelDTO> getAll();
}
