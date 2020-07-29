package com.euvic.carrental.services;

import com.euvic.carrental.model.Parking;
import com.euvic.carrental.repositories.ParkingHistoryRepository;
import com.euvic.carrental.responses.ParkingDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class ParkingHistoryServiceTest {
    @Autowired
    private ParkingHistoryService parkingHistoryService;

    @Autowired
    private ParkingHistoryRepository parkingHistoryRepository;

    @AfterEach
    void tearDown() {
        parkingHistoryRepository.deleteAll();
    }

    @Test
    void whenParkingDTOGiven_thenReturnParkingEntity() {
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final ParkingDTO parkingDTO = new ParkingDTO("Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        assertAll(() -> {
            assertEquals(parkingHistoryService.mapRestModel(null, parkingDTO).getId(), parking.getId());
            assertEquals(parkingHistoryService.mapRestModel(null, parkingDTO).getTown(), parking.getTown());
            assertEquals(parkingHistoryService.mapRestModel(null, parkingDTO).getComment(), parking.getComment());
            assertEquals(parkingHistoryService.mapRestModel(null, parkingDTO).getPostalCode(), parking.getPostalCode());
            assertEquals(parkingHistoryService.mapRestModel(null, parkingDTO).getStreetName(), parking.getStreetName());
            assertEquals(parkingHistoryService.mapRestModel(null, parkingDTO).getIsActive(), parking.getIsActive());
            assertEquals(parkingHistoryService.mapRestModel(null, parkingDTO).getNumber(), parking.getNumber());

        });
    }

    @Test
    void shouldReturnDBParkingDTO() {
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        assertEquals(0, parkingHistoryRepository.count());
        final Long parkingId = parkingHistoryService.addEntityToDB(parking);
        assertEquals(1, parkingHistoryRepository.count());

        final ParkingDTO parkingDTO1 = parkingHistoryService.getAllDTOsByTownName("Katowice").get(0);
        final ParkingDTO parkingDTO2 = parkingHistoryService.getDTOById(parkingId);

        assertAll(() -> {
            assertEquals(parking.getTown(), parkingDTO1.getTown());
            assertEquals(parking.getStreetName(), parkingDTO1.getStreetName());
            assertEquals(parking.getPostalCode(), parkingDTO1.getPostalCode());
            assertEquals(parking.getNumber(), parkingDTO1.getNumber());
            assertEquals(parking.getComment(), parkingDTO1.getComment());
            assertEquals(parking.getIsActive(), parkingDTO1.getIsActive());


            assertEquals(parking.getTown(), parkingDTO2.getTown());
            assertEquals(parking.getStreetName(), parkingDTO2.getStreetName());
            assertEquals(parking.getPostalCode(), parkingDTO2.getPostalCode());
            assertEquals(parking.getNumber(), parkingDTO2.getNumber());
            assertEquals(parking.getComment(), parkingDTO2.getComment());
            assertEquals(parking.getIsActive(), parkingDTO2.getIsActive());
        });
    }

    @Test
    void shouldReturnDBParkingEntity() {
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        assertEquals(0, parkingHistoryRepository.count());
        final Long parkingId = parkingHistoryService.addEntityToDB(parking);
        assertEquals(1, parkingHistoryRepository.count());

        final Parking serviceParking = parkingHistoryService.getEntityById(parkingId);
        assertAll(() -> {
            assertEquals(parking.getComment(), serviceParking.getComment());
            assertEquals(parking.getIsActive(), serviceParking.getIsActive());
            assertEquals(parking.getNumber(), serviceParking.getNumber());
            assertEquals(parking.getPostalCode(), serviceParking.getPostalCode());
            assertEquals(parking.getStreetName(), serviceParking.getStreetName());
            assertEquals(parking.getTown(), serviceParking.getTown());
            assertNotEquals(null, serviceParking.getId());
        });
    }

    @Test
    void whenParkingEntityGiven_shouldAddParkingEntityToDB() {
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);

        assertEquals(0, parkingHistoryRepository.count());
        parkingHistoryService.addEntityToDB(parking);
        assertEquals(1, parkingHistoryRepository.count());
    }
}
