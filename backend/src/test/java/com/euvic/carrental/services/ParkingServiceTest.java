package com.euvic.carrental.services;

import com.euvic.carrental.model.Mark;
import com.euvic.carrental.model.Model;
import com.euvic.carrental.model.Parking;
import com.euvic.carrental.repositories.ParkingRepository;
import com.euvic.carrental.responses.ParkingDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class ParkingServiceTest {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ParkingRepository parkingRepository;

    @AfterEach
    void tearDown() {
        parkingRepository.deleteAll();
    }

    @Test
    void whenParkingDTOGiven_thenReturnParkingEntity() {
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final ParkingDTO parkingDTO = new ParkingDTO("Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        assertAll(() -> {
            assertEquals(parkingService.mapRestModel(parkingDTO).getId(), parking.getId());
            assertEquals(parkingService.mapRestModel(parkingDTO).getTown(), parking.getTown());
            assertEquals(parkingService.mapRestModel(parkingDTO).getComment(), parking.getComment());
            assertEquals(parkingService.mapRestModel(parkingDTO).getPostalCode(), parking.getPostalCode());
            assertEquals(parkingService.mapRestModel(parkingDTO).getStreetName(), parking.getStreetName());
            assertEquals(parkingService.mapRestModel(parkingDTO).getIsActive(), parking.getIsActive());
            assertEquals(parkingService.mapRestModel(parkingDTO).getNumber(), parking.getNumber());

        });
    }

    @Test
    void returnDBParkingEntity(){
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        assertEquals(0, parkingRepository.count());
        parkingRepository.save(parking);
        assertEquals(1, parkingRepository.count());
        Parking serviceParking = parkingService.getEntityByTown("Katowice");

        assertAll(()->{
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
    void returnDBParkingDTO() {
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        assertEquals(0, parkingRepository.count());
        parkingRepository.save(parking);
        assertEquals(1, parkingRepository.count());

        final ParkingDTO parkingDTO = parkingService.getDTOByTown("Katowice");

        assertEquals(parking.getTown(), parkingDTO.getTown());
    }

    @Test
    void addParking() {
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final ParkingDTO parkingDTO = new ParkingDTO(parking);

        assertEquals(0, parkingRepository.count());
        parkingService.add(parkingDTO);
        assertEquals(1, parkingRepository.count());
    }
}
