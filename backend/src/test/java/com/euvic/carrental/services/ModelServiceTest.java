package com.euvic.carrental.services;

import com.euvic.carrental.model.Mark;
import com.euvic.carrental.model.Model;
import com.euvic.carrental.repositories.MarkRepository;
import com.euvic.carrental.repositories.ModelRepository;
import com.euvic.carrental.responses.MarkDTO;
import com.euvic.carrental.responses.ModelDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class ModelServiceTest {

    @Autowired
    private ModelService modelService;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private MarkService markService;

    @Autowired
    private MarkRepository markRepository;

    @BeforeEach
    void setUp() {
        final Mark mark1 = new Mark(null, "Audi");
        final Mark mark2 = new Mark(null, "Opel");
        final Mark mark3 = new Mark(null, "BMW");

        markRepository.save(mark1);
        markRepository.save(mark2);
        markRepository.save(mark3);
    }

    @AfterEach
    void tearDown() {
        modelRepository.deleteAll();
        markRepository.deleteAll();
    }

    @Test
    void whenModelDTOGiven_thenReturnModelEntity() {
        final Model model = new Model(null, "C350", markService.getEntityByName("Audi"));
        final ModelDTO modelDTO = new ModelDTO("C350", new MarkDTO("Audi"));
        final Model restModelToEntityModel = modelService.mapRestModel(modelDTO);
        assertAll(() -> {
            assertEquals(restModelToEntityModel.getId(), model.getId());
            assertEquals(restModelToEntityModel.getName(), model.getName());
            assertEquals(restModelToEntityModel.getMark(), model.getMark());
        });
    }

    @Test
    void shouldReturnDBModelEntity() {
        final Model model = new Model(null, "C350", markService.getEntityByName("Audi"));
        assertEquals(0, modelRepository.count());
        modelRepository.save(model);
        assertEquals(1, modelRepository.count());
        final Model serviceModel = modelService.getEntityByName("C350");

        assertAll(() -> {
            assertEquals(model.getName(), serviceModel.getName());
            assertEquals(model.getMark(), serviceModel.getMark());
            assertNotEquals(null, serviceModel.getId());
        });
    }

    @Test
    void shouldReturnDBModelDTO() {
        final Model model = new Model(null, "C350", markService.getEntityByName("Audi"));
        assertEquals(0, modelRepository.count());
        modelRepository.save(model);
        assertEquals(1, modelRepository.count());

        final ModelDTO serviceModelDTO = modelService.getDTOByName("C350");

        assertAll(() -> {
            assertEquals(model.getName(), serviceModelDTO.getName());
            assertEquals(model.getMark().getName(), serviceModelDTO.getMarkDTO().getName());
        });
    }

    @Test
    void shouldReturnAllDBModelsDTO() {
        final Model model1 = new Model(null, "C350", markService.getEntityByName("Audi"));
        final Model model2 = new Model(null, "Astra", markService.getEntityByName("Opel"));
        final Model model3 = new Model(null, "M5", markService.getEntityByName("BMW"));


        assertEquals(0, modelRepository.count());
        modelRepository.save(model1);
        modelRepository.save(model2);
        modelRepository.save(model3);
        assertEquals(3, modelRepository.count());

        final List<ModelDTO> modelDTOList = modelService.getAllDTOs();

        assertEquals(modelRepository.count(), modelDTOList.size());
    }

    @Test
    void whenModelEntityGiven_shouldAddModelEntityToDB() {
        final Model model = new Model(null,"C350", markService.getEntityByName("Audi"));
        assertEquals(0, modelRepository.count());
        modelService.addEntityToDB(model);
        assertEquals(1, modelRepository.count());
    }
}
