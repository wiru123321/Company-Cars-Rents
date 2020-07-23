package com.euvic.carrental.services;

import com.euvic.carrental.model.GearboxType;
import com.euvic.carrental.model.Model;
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
    void tearDown(){
        gearboxTypeRepository.deleteAll();
    }

    @Test
    void whenGearboxTypeDTOGiven_thenReturnGearboxTypeEntity(){
        final GearboxType gearboxType = new GearboxType(null, "Automatic");
        final GearBoxTypeDTO gearBoxTypeDTO = new GearBoxTypeDTO("Automatic");

        assertAll(() -> {
            assertEquals(gearboxTypeService.mapRestModel(gearBoxTypeDTO).getName(), gearboxType.getName());
            assertEquals(gearboxTypeService.mapRestModel(gearBoxTypeDTO).getId(), gearboxType.getId());
        });
    }

    @Test
    void returnDBGearboxTypeEntity() {
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
    void returnDBGearboxTypeDTO(){
        final GearboxType gearboxType = new GearboxType(null, "Automatic");
        assertEquals(0, gearboxTypeRepository.count());
        gearboxTypeRepository.save(gearboxType);
        assertEquals(1, gearboxTypeRepository.count());

        GearBoxTypeDTO serviceGearboxDTO = gearboxTypeService.getDTOByName("Automatic");

        assertEquals(gearboxType.getName(), serviceGearboxDTO.getName());
    }

    @Test
    void returnAllDBGearboxTypesDTO(){
        final GearboxType gearboxType1 = new GearboxType(null, "Automatic");
        final GearboxType gearboxType2 = new GearboxType(null, "Manual");
        assertEquals(0, gearboxTypeRepository.count());
        gearboxTypeRepository.save(gearboxType1);
        gearboxTypeRepository.save(gearboxType2);
        assertEquals(2, gearboxTypeRepository.count());

        List<GearBoxTypeDTO> gearBoxTypeDTOList = gearboxTypeService.getAll();

        assertEquals(gearboxTypeRepository.count(), gearBoxTypeDTOList.size());
    }
}
