package com.euvic.carrental.services;

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
        final ParkingDTO parkingDTO = new ParkingDTO("Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea");
        assertAll(() -> {
            assertEquals(parkingService.mapRestModel(null, parkingDTO).getId(), parking.getId());
            assertEquals(parkingService.mapRestModel(null, parkingDTO).getTown(), parking.getTown());
            assertEquals(parkingService.mapRestModel(null, parkingDTO).getComment(), parking.getComment());
            assertEquals(parkingService.mapRestModel(null, parkingDTO).getPostalCode(), parking.getPostalCode());
            assertEquals(parkingService.mapRestModel(null, parkingDTO).getStreetName(), parking.getStreetName());
            assertEquals(parkingService.mapRestModel(null, parkingDTO).getIsActive(), parking.getIsActive());
            assertEquals(parkingService.mapRestModel(null, parkingDTO).getNumber(), parking.getNumber());

        });
    }

    @Test
    void shouldReturnDBParkingEntity() {
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        assertEquals(0, parkingRepository.count());
        final Long parkingId = parkingService.addEntityToDB(parking);
        assertEquals(1, parkingRepository.count());

        final Parking serviceParking = parkingService.getEntityById(parkingId);
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
    void shouldReturnDBParkingDTO() {
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        assertEquals(0, parkingRepository.count());
        final Long parkingId = parkingService.addEntityToDB(parking);
        assertEquals(1, parkingRepository.count());

        final ParkingDTO parkingDTO1 = parkingService.getAllDTOsByTownName("Katowice").get(0);
        final ParkingDTO parkingDTO2 = parkingService.getDTOById(parkingId);

        assertAll(() -> {
            assertEquals(parking.getTown(), parkingDTO1.getTown());
            assertEquals(parking.getStreetName(), parkingDTO1.getStreetName());
            assertEquals(parking.getPostalCode(), parkingDTO1.getPostalCode());
            assertEquals(parking.getNumber(), parkingDTO1.getNumber());
            assertEquals(parking.getComment(), parkingDTO1.getComment());
            assertEquals(parking.getIsActive(), true);


            assertEquals(parking.getTown(), parkingDTO2.getTown());
            assertEquals(parking.getStreetName(), parkingDTO2.getStreetName());
            assertEquals(parking.getPostalCode(), parkingDTO2.getPostalCode());
            assertEquals(parking.getNumber(), parkingDTO2.getNumber());
            assertEquals(parking.getComment(), parkingDTO2.getComment());
            assertEquals(parking.getIsActive(), true);
        });
    }

    @Test
    void whenParkingEntityGiven_shouldAddParkingEntityToDB() {
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);

        assertEquals(0, parkingRepository.count());
        parkingService.addEntityToDB(parking);
        assertEquals(1, parkingRepository.count());
    }

    @Test
    void whenParkingDTOGiven_shouldUpdateExistingDBParking() {
        final Parking parking = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);

        final ParkingDTO parkingDTO = new ParkingDTO("Chorzow", "40-321", "Jakas 23", "E-1", "Parking przy Tesco");
        assertEquals(0, parkingRepository.count());
        final Long parkingId = parkingService.addEntityToDB(parking);
        assertEquals(1, parkingRepository.count());
        parkingService.updateParkingInDb(parkingId, parkingDTO);
        final Parking updatedParking = parkingService.getEntityById(parkingId);
        assertAll(() -> {
            assertEquals(1, parkingRepository.count());
            assertEquals("Chorzow", updatedParking.getTown());
            assertEquals("40-321", updatedParking.getPostalCode());
            assertEquals("Jakas 23", updatedParking.getStreetName());
            assertEquals("E-1", updatedParking.getNumber());
            assertEquals("Parking przy Tesco", updatedParking.getComment());
            assertTrue(updatedParking.getIsActive());
        });
    }

    @Test
    void whenTwoParkingsGiven_ifFirstParkingIsNotNull_addToDBAndReturnParkingId() {
        final ParkingDTO parkingDTONotNull1 = new ParkingDTO("Chorzow", "40-321", "Jakas 23", "E-1", "Parking przy Tesco");
        final ParkingDTO parkingDTONotNull2 = new ParkingDTO("KAtowice", "40-311", "Mariacka 22", "A2", "Parking za mariacką");

        final Long parkingFirstId = parkingService.choosesNotNullParkingAndAddToDB(parkingDTONotNull1, parkingDTONotNull2);
        final ParkingDTO result1 = parkingService.getDTOById(parkingFirstId);

        assertAll(() -> {
            assertEquals(parkingDTONotNull2.getTown(), result1.getTown());
            assertEquals(parkingDTONotNull2.getPostalCode(), result1.getPostalCode());
        });
        final Long parkingSecondId = parkingService.choosesNotNullParkingAndAddToDB(parkingDTONotNull1, null);
        final ParkingDTO result2 = parkingService.getDTOById(parkingSecondId);
        assertAll(() -> {
            assertEquals(parkingDTONotNull1.getTown(), result2.getTown());
            assertEquals(parkingDTONotNull1.getPostalCode(), result2.getPostalCode());
        });
    }
}
