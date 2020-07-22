package com.euvic.carrental.services;

import com.euvic.carrental.model.Parking;
import com.euvic.carrental.repositories.ParkingHistoryRepository;
import com.euvic.carrental.responses.ParkingDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
            assertEquals(parkingHistoryService.mapRestModel(parkingDTO).getId(), parking.getId());
            assertEquals(parkingHistoryService.mapRestModel(parkingDTO).getTown(), parking.getTown());
            assertEquals(parkingHistoryService.mapRestModel(parkingDTO).getComment(), parking.getComment());
            assertEquals(parkingHistoryService.mapRestModel(parkingDTO).getPostalCode(), parking.getPostalCode());
            assertEquals(parkingHistoryService.mapRestModel(parkingDTO).getStreetName(), parking.getStreetName());
            assertEquals(parkingHistoryService.mapRestModel(parkingDTO).getIsActive(), parking.getIsActive());
            assertEquals(parkingHistoryService.mapRestModel(parkingDTO).getNumber(), parking.getNumber());

        });
    }

    @Test
    void returnDBParkingDTO() {
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        assertEquals(0, parkingHistoryRepository.count());
        parkingHistoryRepository.save(parking);
        assertEquals(1, parkingHistoryRepository.count());

        final ParkingDTO parkingDTO = parkingHistoryService.getDTOByTown("Katowice");

        assertEquals(parking.getTown(), parkingDTO.getTown());
    }

    @Test
    void addParking() {
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final ParkingDTO parkingDTO = new ParkingDTO(parking);

        assertEquals(0, parkingHistoryRepository.count());
        parkingHistoryService.add(parkingDTO);
        assertEquals(1, parkingHistoryRepository.count());
    }
}
