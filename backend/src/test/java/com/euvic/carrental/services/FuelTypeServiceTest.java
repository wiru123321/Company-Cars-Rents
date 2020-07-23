package com.euvic.carrental.services;

import com.euvic.carrental.model.FuelType;
import com.euvic.carrental.model.GearboxType;
import com.euvic.carrental.repositories.FuelTypeRepository;
import com.euvic.carrental.responses.FuelTypeDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class FuelTypeServiceTest {

    @Autowired
    FuelTypeService fuelTypeService;

    @Autowired
    FuelTypeRepository fuelTypeRepository;

    @AfterEach
    void tearDown(){
        fuelTypeRepository.deleteAll();
    }

    @Test
    void whenFuelTypeDTOGiven_thenReturnFuelTypeEntity(){
        final FuelType fuelType = new FuelType(null,"Gasoline");
        final FuelTypeDTO fuelTypeDTO = new FuelTypeDTO("Gasoline");
        assertAll(() -> {
            assertEquals(fuelTypeService.mapRestModel(fuelTypeDTO).getName(), fuelType.getName());
            assertEquals(fuelTypeService.mapRestModel(fuelTypeDTO).getId(), fuelType.getId());
        });
    }

    @Test
    void returnDBFuelTypeEntity() {
        final FuelType fuelType = new FuelType(null, "Gasoline");
        assertEquals(0, fuelTypeRepository.count());
        fuelTypeRepository.save(fuelType);
        assertEquals(1, fuelTypeRepository.count());
        final FuelType serviceFuelType = fuelTypeService.getEntityByName("Gasoline");

        assertAll(() -> {
            assertEquals(fuelType.getName(), serviceFuelType.getName());
            assertNotEquals(null, serviceFuelType.getId());
        });
    }

    @Test
    void returnDBFuelTypeDTO(){
        final FuelType fuelType = new FuelType(null, "Gasoline");
        assertEquals(0, fuelTypeRepository.count());
        fuelTypeRepository.save(fuelType);
        assertEquals(1, fuelTypeRepository.count());

        FuelTypeDTO serviceFuelType = fuelTypeService.getDTOByName("Gasoline");

        assertEquals(fuelType.getName(), serviceFuelType.getName());
    }

    @Test
    void returnAllDBFuelTypesDTO(){
        final FuelType fuelType1 = new FuelType(null, "Gasoline");
        final FuelType fuelType2 = new FuelType(null, "Diesel");
        final FuelType fuelType3 = new FuelType(null, "Petrol");
        assertEquals(0, fuelTypeRepository.count());
        fuelTypeRepository.save(fuelType1);
        fuelTypeRepository.save(fuelType2);
        fuelTypeRepository.save(fuelType3);
        assertEquals(3, fuelTypeRepository.count());

        List<FuelTypeDTO> fuelTypeDTOList = fuelTypeService.getAll();

        assertEquals(fuelTypeRepository.count(), fuelTypeDTOList.size());
    }
}
