package com.euvic.carrental.services;

import com.euvic.carrental.model.ParkingHistory;
import com.euvic.carrental.repositories.ParkingHistoryRepository;
import com.euvic.carrental.responses.ParkingHistoryDTO;
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
    void whenParkingHistoryDTOGiven_thenReturnParkingHistoryEntity() {
        final ParkingHistory parkingHistory = new ParkingHistory(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final ParkingHistoryDTO parkingHistoryDTO = new ParkingHistoryDTO("Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        assertAll(() -> {
            assertEquals(parkingHistoryService.mapRestModel(null, parkingHistoryDTO).getId(), parkingHistory.getId());
            assertEquals(parkingHistoryService.mapRestModel(null, parkingHistoryDTO).getTown(), parkingHistory.getTown());
            assertEquals(parkingHistoryService.mapRestModel(null, parkingHistoryDTO).getComment(), parkingHistory.getComment());
            assertEquals(parkingHistoryService.mapRestModel(null, parkingHistoryDTO).getPostalCode(), parkingHistory.getPostalCode());
            assertEquals(parkingHistoryService.mapRestModel(null, parkingHistoryDTO).getStreetName(), parkingHistory.getStreetName());
            assertEquals(parkingHistoryService.mapRestModel(null, parkingHistoryDTO).getIsActive(), parkingHistory.getIsActive());
            assertEquals(parkingHistoryService.mapRestModel(null, parkingHistoryDTO).getNumber(), parkingHistory.getNumber());

        });
    }

    @Test
    void shouldReturnDBParkingHistoryDTO() {
        final ParkingHistory parkingHistory = new ParkingHistory(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        assertEquals(0, parkingHistoryRepository.count());
        final Long parkingHistoryId = parkingHistoryService.addEntityToDB(parkingHistory);
        assertEquals(1, parkingHistoryRepository.count());

        final ParkingHistoryDTO parkingHistoryDTO1 = parkingHistoryService.getAllDTOsByTownName("Katowice").get(0);
        final ParkingHistoryDTO parkingHistoryDTO2 = parkingHistoryService.getDTOById(parkingHistoryId);

        assertAll(() -> {
            assertEquals(parkingHistory.getTown(), parkingHistoryDTO1.getTown());
            assertEquals(parkingHistory.getStreetName(), parkingHistoryDTO1.getStreetName());
            assertEquals(parkingHistory.getPostalCode(), parkingHistoryDTO1.getPostalCode());
            assertEquals(parkingHistory.getNumber(), parkingHistoryDTO1.getNumber());
            assertEquals(parkingHistory.getComment(), parkingHistoryDTO1.getComment());
            assertEquals(parkingHistory.getIsActive(), parkingHistoryDTO1.getIsActive());


            assertEquals(parkingHistory.getTown(), parkingHistoryDTO2.getTown());
            assertEquals(parkingHistory.getStreetName(), parkingHistoryDTO2.getStreetName());
            assertEquals(parkingHistory.getPostalCode(), parkingHistoryDTO2.getPostalCode());
            assertEquals(parkingHistory.getNumber(), parkingHistoryDTO2.getNumber());
            assertEquals(parkingHistory.getComment(), parkingHistoryDTO2.getComment());
            assertEquals(parkingHistory.getIsActive(), parkingHistoryDTO2.getIsActive());
        });
    }

    @Test
    void shouldReturnDBParkingHistoryEntity() {
        final ParkingHistory parkingHistory = new ParkingHistory(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        assertEquals(0, parkingHistoryRepository.count());
        final Long parkingHistoryId = parkingHistoryService.addEntityToDB(parkingHistory);
        assertEquals(1, parkingHistoryRepository.count());

        final ParkingHistory serviceParkingHistory = parkingHistoryService.getEntityById(parkingHistoryId);
        assertAll(() -> {
            assertEquals(parkingHistory.getComment(), serviceParkingHistory.getComment());
            assertEquals(parkingHistory.getIsActive(), serviceParkingHistory.getIsActive());
            assertEquals(parkingHistory.getNumber(), serviceParkingHistory.getNumber());
            assertEquals(parkingHistory.getPostalCode(), serviceParkingHistory.getPostalCode());
            assertEquals(parkingHistory.getStreetName(), serviceParkingHistory.getStreetName());
            assertEquals(parkingHistory.getTown(), serviceParkingHistory.getTown());
            assertNotEquals(null, serviceParkingHistory.getId());
        });
    }

    @Test
    void whenParkingHistoryEntityGiven_shouldAddParkingHistoryEntityToDB() {
        final ParkingHistory parkingHistory = new ParkingHistory(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);

        assertEquals(0, parkingHistoryRepository.count());
        parkingHistoryService.addEntityToDB(parkingHistory);
        assertEquals(1, parkingHistoryRepository.count());
    }
}
