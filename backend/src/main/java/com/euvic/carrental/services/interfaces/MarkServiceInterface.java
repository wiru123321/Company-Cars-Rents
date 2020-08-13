package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Mark;
import com.euvic.carrental.responses.MarkDTO;

import java.util.List;

public interface MarkServiceInterface {
    Mark mapRestModel(Long id, MarkDTO model);

    Mark getEntityByName(String name);

    MarkDTO getDTOByName(String name);

    Long addEntityToDB(Mark mark);

    Long updateMarkInDB(String oldMarkName, MarkDTO markDTO);

    List<MarkDTO> getAllDTOs();
}
