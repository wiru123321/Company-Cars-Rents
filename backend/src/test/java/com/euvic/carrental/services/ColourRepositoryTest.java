package com.euvic.carrental.services;

import com.euvic.carrental.model.Colour;
import com.euvic.carrental.repositories.ColourRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("h2")
public class ColourRepositoryTest {

    @Autowired
    private ColourRepository colourRepository;

    @AfterEach
    void tearDown(){
        colourRepository.deleteAll();
    }

    @Test
    void addColour(){
        final Colour colour = new Colour("whatever");
        assertEquals(0, colourRepository.count());
        colourRepository.save(colour);

        assertEquals(1, colourRepository.count());
    }

}
