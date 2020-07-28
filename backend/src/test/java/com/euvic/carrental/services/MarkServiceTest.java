package com.euvic.carrental.services;

import com.euvic.carrental.model.Mark;
import com.euvic.carrental.repositories.MarkRepository;
import com.euvic.carrental.responses.MarkDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class MarkServiceTest {

    @Autowired
    private MarkService markService;

    @Autowired
    private MarkRepository markRepository;

    @AfterEach
    void tearDown(){
        markRepository.deleteAll();
    }

    @Test
    void whenMarkDTOGiven_thenReturnMarkEntity(){
        final Mark mark = new Mark(null, "Audi");
        assertEquals(0, markRepository.count());
        markRepository.save(mark);
        assertEquals(1, markRepository.count());
        final MarkDTO markDTO = markService.getDTOByName("Audi");

        assertEquals(mark.getName(), markDTO.getName());
    }

    @Test
    void shouldReturnDBMarkEntity(){
        final Mark mark = new Mark(null, "Audi");
        assertEquals(0, markRepository.count());
        markRepository.save(mark);
        assertEquals(1, markRepository.count());
        Mark serviceMark = markService.getEntityByName("Audi");

        assertAll(()->{
            assertEquals(mark.getName(), serviceMark.getName());
            assertNotEquals(null, serviceMark.getId());
        });
    }

    @Test
    void shouldReturnDBMarkDTO(){
        final Mark mark = new Mark(null, "Audi");

        assertEquals(0, markRepository.count());
        markRepository.save(mark);
        assertEquals(1, markRepository.count());
        MarkDTO serviceMarkDTO = markService.getDTOByName("Audi");

        assertEquals(mark.getName(), serviceMarkDTO.getName());
    }

    @Test
    void shouldReturnAllDBMarksDTO(){
        final Mark mark1 = new Mark(null, "Audi");
        final Mark mark2 = new Mark(null, "Mercedes");
        final Mark mark3 = new Mark(null, "Opel");
        assertEquals(0, markRepository.count());
        markRepository.save(mark1);
        markRepository.save(mark2);
        markRepository.save(mark3);
        assertEquals(3, markRepository.count());

        final List<MarkDTO> markDTOList = markService.getAllDTOs();

        assertEquals(markRepository.count(), markDTOList.size());
    }
}
