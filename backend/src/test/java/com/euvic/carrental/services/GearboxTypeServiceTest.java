package com.euvic.carrental.services;

import com.euvic.carrental.model.GearboxType;
import com.euvic.carrental.repositories.GearboxTypeRepository;
import com.euvic.carrental.responses.GearBoxTypeDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class GearboxTypeServiceTest {

    @Autowired
    private GearboxTypeService gearboxTypeService;

    @Autowired
    private GearboxTypeRepository gearboxTypeRepository;

    @AfterEach
    void tearDown() {
        gearboxTypeRepository.deleteAll();
    }

    @Test
    void whenGearboxTypeDTOGiven_thenReturnGearboxTypeEntity() {
        final GearboxType gearboxType = new GearboxType(null, "Automatic");
        final GearBoxTypeDTO gearBoxTypeDTO = new GearBoxTypeDTO("Automatic");

        assertAll(() -> {
            assertEquals(gearboxTypeService.mapRestModel(null, gearBoxTypeDTO).getName(), gearboxType.getName());
            assertEquals(gearboxTypeService.mapRestModel(null, gearBoxTypeDTO).getId(), gearboxType.getId());
        });
    }

    @Test
    void shouldReturnDBGearboxTypeEntity() {
        final GearboxType gearboxType = new GearboxType(null, "Automatic");
        assertEquals(0, gearboxTypeRepository.count());
        gearboxTypeRepository.save(gearboxType);
        assertEquals(1, gearboxTypeRepository.count());
        final GearboxType serviceGearboxType = gearboxTypeService.getEntityByName("Automatic");

        assertAll(() -> {
            assertEquals(gearboxType.getName(), serviceGearboxType.getName());
            assertNotEquals(null, serviceGearboxType.getId());
        });
    }

    @Test
    void shouldReturnDBGearboxTypeDTO() {
        final GearboxType gearboxType = new GearboxType(null, "Automatic");
        assertEquals(0, gearboxTypeRepository.count());
        gearboxTypeRepository.save(gearboxType);
        assertEquals(1, gearboxTypeRepository.count());

        final GearBoxTypeDTO serviceGearboxDTO = gearboxTypeService.getDTOByName("Automatic");

        assertEquals(gearboxType.getName(), serviceGearboxDTO.getName());
    }

    @Test
    void shouldReturnAllDBGearboxTypesDTO() {
        final GearboxType gearboxType1 = new GearboxType(null, "Automatic");
        final GearboxType gearboxType2 = new GearboxType(null, "Manual");
        assertEquals(0, gearboxTypeRepository.count());
        gearboxTypeRepository.save(gearboxType1);
        gearboxTypeRepository.save(gearboxType2);
        assertEquals(2, gearboxTypeRepository.count());

        final List<GearBoxTypeDTO> gearBoxTypeDTOList = gearboxTypeService.getAllDTOs();

        assertEquals(gearboxTypeRepository.count(), gearBoxTypeDTOList.size());
    }
}
