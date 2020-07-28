package com.euvic.carrental.services;

import com.euvic.carrental.model.Colour;
import com.euvic.carrental.repositories.ColourRepository;
import com.euvic.carrental.responses.ColourDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class ColourServiceTest {

    @Autowired
    private ColourService colourService;

    @Autowired
    private ColourRepository colourRepository;

    @AfterEach
    void tearDown() {
        colourRepository.deleteAll();
    }

    @Test
    void whenColourDTOGiven_thenReturnColourEntity() {
        final Colour colour = new Colour(null, "Red");
        final ColourDTO colourDTO = new ColourDTO("Red");
        assertAll(() -> {
            assertEquals(colourService.mapRestModel(colourDTO).getName(), colour.getName());
            assertEquals(colourService.mapRestModel(colourDTO).getId(), colour.getId());
        });
    }

    @Test
    void returnDBColourEntity(){
        final Colour colour = new Colour(null, "Red");
        assertEquals(0, colourRepository.count());
        colourRepository.save(colour);
        assertEquals(1, colourRepository.count());
        Colour serviceColour = colourService.getEntityByName("Red");

        assertAll(()->{
            assertEquals(colour.getName(), serviceColour.getName());
            assertNotEquals(null, serviceColour.getId());
        });
    }

    @Test
    void returnDBColourDTO() {
        final Colour colour = new Colour(null, "Blue");
        assertEquals(0, colourRepository.count());
        colourRepository.save(colour);
        assertEquals(1, colourRepository.count());

        final ColourDTO colourDTO = colourService.getDTOByName("Blue");

        assertEquals(colour.getName(), colourDTO.getName());
    }

    @Test
    void returnAllDBColoursDTO() {
        final Colour colour1 = new Colour(null, "Red");
        final Colour colour2 = new Colour(null, "Blue");
        final Colour colour3 = new Colour(null, "Yellow");
        assertEquals(0, colourRepository.count());
        colourRepository.save(colour1);
        colourRepository.save(colour2);
        colourRepository.save(colour3);
        assertEquals(3, colourRepository.count());

        final List<ColourDTO> colourDTOList = colourService.getAllDTOs();

        assertEquals(colourRepository.count(), colourDTOList.size());
    }

    @Test
    void whenColourEntityGiven_shouldAddColourEntityToDB() {
        final Colour colour = new Colour(null, "Red");

        assertEquals(0, colourRepository.count());
        colourService.addEntityToDB(colour);
        assertEquals(1, colourRepository.count());
    }
}
