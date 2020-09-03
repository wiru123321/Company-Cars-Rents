package com.euvic.carrental.services;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.*;
import com.euvic.carrental.responses.CarDTO;
import com.euvic.carrental.responses.FaultDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class FaultServiceTest {

    @Autowired
    private FaultRepository faultRepository;

    @Autowired
    private FaultService faultService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private GearboxTypeService gearboxTypeService;

    @Autowired
    private GearboxTypeRepository gearboxTypeRepository;

    @Autowired
    private FuelTypeService fuelTypeService;

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Autowired
    private MarkService markService;

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private ModelService modelService;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ColourService colourService;

    @Autowired
    private ColourRepository colourRepository;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TypeRepository typeRepository;

    // TODO optymalizacja @BeforeEach
    @BeforeEach
    void setUp() {
        final GearboxType gearboxType1 = new GearboxType(null, "Manual");
        final GearboxType gearboxType2 = new GearboxType(null, "Automatic");
        final GearboxType gearboxType3 = new GearboxType(null, "Other");

        gearboxTypeRepository.save(gearboxType1);
        gearboxTypeRepository.save(gearboxType2);
        gearboxTypeRepository.save(gearboxType3);


        final FuelType fuelType1 = new FuelType(null, "Gasoline");
        final FuelType fuelType2 = new FuelType(null, "Petrol");
        final FuelType fuelType3 = new FuelType(null, "Diesel");

        fuelTypeRepository.save(fuelType1);
        fuelTypeRepository.save(fuelType2);
        fuelTypeRepository.save(fuelType3);

        final Mark mark1 = new Mark(null, "Audi");
        final Mark mark2 = new Mark(null, "Opel");
        final Mark mark3 = new Mark(null, "BMW");

        markRepository.save(mark1);
        markRepository.save(mark2);
        markRepository.save(mark3);

        final Model model1 = new Model(null, "C350", markService.getEntityByName("Audi"));
        final Model model2 = new Model(null, "Astra", markService.getEntityByName("Opel"));
        final Model model3 = new Model(null, "M5", markService.getEntityByName("BMW"));

        modelRepository.save(model1);
        modelRepository.save(model2);
        modelRepository.save(model3);

        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);

        final Colour colour1 = new Colour(null, "Red");
        final Colour colour2 = new Colour(null, "Blue");
        final Colour colour3 = new Colour(null, "Green");

        colourRepository.save(colour1);
        colourRepository.save(colour2);
        colourRepository.save(colour3);

        final Type type1 = new Type(null, "Sedan");
        final Type type2 = new Type(null, "Coupe");
        final Type type3 = new Type(null, "Van");

        typeRepository.save(type1);
        typeRepository.save(type2);
        typeRepository.save(type3);


        final Car car = new Car(null, "SBE33212", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2000, true, 120000, modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId1), colourService.getEntityByName("Blue"), typeService.getEntityByName("Coupe"));

        carRepository.save(car);
    }

    @AfterEach
    void tearDown() {
        faultRepository.deleteAll();
        carRepository.deleteAll();
        gearboxTypeRepository.deleteAll();
        fuelTypeRepository.deleteAll();
        modelRepository.deleteAll();
        markRepository.deleteAll();
        parkingRepository.deleteAll();
        colourRepository.deleteAll();
        typeRepository.deleteAll();

    }

    @Test
    void whenFaultDTOGiven_thenReturnFaultEntity() {
        final Car car = carService.getOnCompanyEntityByLicensePlate("SBE33212");
        final CarDTO carDTO = carService.getDTOByLicensePlate("SBE33212");

        final Fault fault = new Fault(null, car, "nothing", LocalDateTime.of(2020, 3, 25, 0, 0), false, true);

        final FaultDTO faultDTO = new FaultDTO(carDTO.getLicensePlate(), "nothing", LocalDateTime.of(2020, 3, 25, 0, 0), false);

        final Fault restModelToEntityModel = faultService.mapRestModel(null, faultDTO);
        assertAll(() -> {
            assertEquals(restModelToEntityModel.getCar(), fault.getCar());
            assertEquals(restModelToEntityModel.getId(), fault.getId());
            assertEquals(restModelToEntityModel.getDescription(), fault.getDescription());
            assertEquals(restModelToEntityModel.getFaultDate(), fault.getFaultDate());
            assertEquals(restModelToEntityModel.getSetCarInactive(), fault.getSetCarInactive());
            assertEquals(restModelToEntityModel.getIsActive(), fault.getIsActive());
        });
    }

    @Test
    void shouldReturnDBFaultEntity() {
        final Car car = carService.getOnCompanyEntityByLicensePlate("SBE33212");

        final Fault fault = new Fault(null, car, "nothing", LocalDateTime.of(2020, 3, 25, 0, 0), false, true);

        assertEquals(0, faultRepository.count());
        final Long faultId = faultService.addEntityToDB(fault);
        assertEquals(1, faultRepository.count());

        final Fault serviceFault1 = faultService.getAllActiveEntitiesByCar(car).get(0);
        final Fault serviceFault2 = faultService.getEntityById(faultId);

        assertAll(() -> {
            assertNotEquals(null, serviceFault1.getId());
            assertEquals(fault.getCar(), serviceFault1.getCar());
            assertEquals(fault.getDescription(), serviceFault1.getDescription());
            assertEquals(fault.getFaultDate(), serviceFault1.getFaultDate());
            assertEquals(fault.getSetCarInactive(), serviceFault1.getSetCarInactive());
            assertEquals(fault.getIsActive(), serviceFault1.getIsActive());


            assertNotEquals(null, serviceFault2.getId());
            assertEquals(fault.getCar(), serviceFault2.getCar());
            assertEquals(fault.getDescription(), serviceFault2.getDescription());
            assertEquals(fault.getFaultDate(), serviceFault2.getFaultDate());
            assertEquals(fault.getSetCarInactive(), serviceFault2.getSetCarInactive());
            assertEquals(fault.getIsActive(), serviceFault2.getIsActive());
        });
    }

    @Test
    void shouldReturnDBFaultDTO() {
        final Car car = carService.getOnCompanyEntityByLicensePlate("SBE33212");

        final Fault fault = new Fault(null, car, "nothing", LocalDateTime.of(2020, 3, 25, 0, 0), false, true);
        assertEquals(0, faultRepository.count());
        final Long faultId = faultService.addEntityToDB(fault);
        assertEquals(1, faultRepository.count());

        final FaultDTO faultDTO1 = faultService.getAllActiveDTOsByCar(car).get(0);
        final FaultDTO faultDTO2 = faultService.getDTOById(faultId);

        assertAll(() -> {
            assertEquals(fault.getDescription(), faultDTO1.getDescription());
            assertEquals(fault.getFaultDate(), faultDTO1.getFaultDate());
            assertEquals(fault.getSetCarInactive(), faultDTO1.getSetCarInactive());
            assertEquals(fault.getCar().getLicensePlate(), faultDTO1.getCarLicensePlate());


            assertEquals(fault.getDescription(), faultDTO2.getDescription());
            assertEquals(fault.getFaultDate(), faultDTO2.getFaultDate());
            assertEquals(fault.getSetCarInactive(), faultDTO2.getSetCarInactive());
            assertEquals(fault.getCar().getLicensePlate(), faultDTO2.getCarLicensePlate());
        });
    }

    @Test
    void shouldReturnAllActiveCarDBFaultsDTO() {
        final Car car = carService.getOnCompanyEntityByLicensePlate("SBE33212");

        final Fault fault1 = new Fault(null, car, "sd", LocalDateTime.of(2020, 3, 24, 0, 0), false, false);
        final Fault fault2 = new Fault(null, car, "dd", LocalDateTime.of(2020, 3, 25, 0, 0), false, true);
        final Fault fault3 = new Fault(null, car, "we", LocalDateTime.of(2020, 3, 26, 0, 0), false, true);

        assertEquals(0, faultRepository.count());
        faultRepository.save(fault1);
        faultRepository.save(fault2);
        faultRepository.save(fault3);
        assertEquals(3, faultRepository.count());

        final List<FaultDTO> faultDTOList = faultService.getAllActiveDTOsByCar(car);

        assertEquals(2, faultDTOList.size());
    }

    @Test
    void shouldReturnAllDBFaultsDTO(){
        final Car car = carService.getOnCompanyEntityByLicensePlate("SBE33212");

        final Fault fault1 = new Fault(null, car, "sd", LocalDateTime.of(2020, 3, 24, 0, 0), false, false);
        final Fault fault2 = new Fault(null, car, "dd", LocalDateTime.of(2020, 3, 25, 0, 0), false, true);
        final Fault fault3 = new Fault(null, car, "we", LocalDateTime.of(2020, 3, 26, 0, 0), false, true);

        assertEquals(0, faultRepository.count());
        faultRepository.save(fault1);
        faultRepository.save(fault2);
        faultRepository.save(fault3);
        assertEquals(3, faultRepository.count());

        final List<FaultDTO> faultDTOList = faultService.getAllDTOs();

        assertEquals(faultRepository.count(), faultDTOList.size());
    }

    @Test
    void shouldReturnAllActiveCarFaultsDTO(){
        final Car car = carService.getOnCompanyEntityByLicensePlate("SBE33212");

        final Fault fault1 = new Fault(null, car, "sd", LocalDateTime.of(2020, 3, 24, 0, 0), false, false);
        final Fault fault2 = new Fault(null, car, "dd", LocalDateTime.of(2020, 3, 25, 0, 0), false, true);
        final Fault fault3 = new Fault(null, car, "we", LocalDateTime.of(2020, 3, 26, 0, 0), false, true);

        assertEquals(0, faultRepository.count());
        faultRepository.save(fault1);
        faultRepository.save(fault2);
        faultRepository.save(fault3);
        assertEquals(3, faultRepository.count());

        final List<FaultDTO> faultDTOList = faultService.getAllActiveFaultDTOs();

        assertEquals(2, faultDTOList.size());
    }

    @Test
    void shouldReturnAllActiveFaultDTOsByCarLicensePlate(){
        final Car car = carService.getOnCompanyEntityByLicensePlate("SBE33212");

        final Fault fault1 = new Fault(null, car, "sd", LocalDateTime.of(2020, 3, 24, 0, 0), false, false);
        final Fault fault2 = new Fault(null, car, "dd", LocalDateTime.of(2020, 3, 25, 0, 0), false, true);
        final Fault fault3 = new Fault(null, car, "we", LocalDateTime.of(2020, 3, 26, 0, 0), false, true);

        assertEquals(0, faultRepository.count());
        faultRepository.save(fault1);
        faultRepository.save(fault2);
        faultRepository.save(fault3);
        assertEquals(3, faultRepository.count());

        final List<FaultDTO> faultDTOList = faultService.getAllActiveFaultDTOsByCarLicensePlate("SBE33212");

        assertEquals(2, faultDTOList.size());
    }

    @Test
    void whenFaultEntityGiven_shouldAddFaultEntityToDB() {
        final Car car = carService.getOnCompanyEntityByLicensePlate("SBE33212");

        final Fault fault = new Fault(null, car, "sd", LocalDateTime.of(2020, 3, 24, 0, 0), false, false);

        assertEquals(0, faultRepository.count());
        faultService.addEntityToDB(fault);
        assertEquals(1, faultRepository.count());
    }

    @Test
    void shouldCheckIfCarFaultWithDescriptionExists(){
        final Car car = carService.getOnCompanyEntityByLicensePlate("SBE33212");
        final Fault fault = new Fault(null, car, "sd", LocalDateTime.of(2020, 3, 24, 0, 0), false, true);
        assertEquals(0, faultRepository.count());
        Long faultId = faultService.addEntityToDB(fault);
        assertEquals(1, faultRepository.count());

        assertTrue(faultService.checkIfCarFaultWithDescriptionExists(car, fault.getDescription()));
    }

    @Test
    void whenGivenFaultCarAndFaultDescription_shouldSetThisFaultInactive(){
        final Car car = carService.getOnCompanyEntityByLicensePlate("SBE33212");
        final Fault fault1 = new Fault(null, car, "dd", LocalDateTime.of(2020, 3, 25, 0, 0), false, true);
        final Fault fault2 = new Fault(null, car, "sd", LocalDateTime.of(2020, 3, 24, 0, 0), false, false);
        final Fault fault3 = new Fault(null, car, "we", LocalDateTime.of(2020, 3, 26, 0, 0), false, true);
        assertEquals(0, faultRepository.count());
        Long faultId1 = faultService.addEntityToDB(fault1);
        Long faultId2 = faultService.addEntityToDB(fault2);
        Long faultId3 = faultService.addEntityToDB(fault3);
        assertEquals(3, faultRepository.count());

        faultService.setInactiveCarFaultWithDescription(car, fault1.getDescription());
        Fault deletedFault1 = faultService.getEntityById(faultId1);
        assertAll(()->{
            assertFalse(deletedFault1.getIsActive());
            assertFalse(fault2.getIsActive());
            assertTrue(fault3.getIsActive());
        });
    }

    @Test
    void whenGivenCarDescription_shouldSetAllItsFaultsInactive(){
        final Car car = carService.getOnCompanyEntityByLicensePlate("SBE33212");
        final Fault fault1 = new Fault(null, car, "sd", LocalDateTime.of(2020, 3, 24, 0, 0), false, false);
        final Fault fault2 = new Fault(null, car, "dd", LocalDateTime.of(2020, 3, 25, 0, 0), false, true);
        final Fault fault3 = new Fault(null, car, "we", LocalDateTime.of(2020, 3, 26, 0, 0), false, true);
        assertEquals(0, faultRepository.count());
        Long faultId1 = faultService.addEntityToDB(fault1);
        Long faultId2 = faultService.addEntityToDB(fault2);
        Long faultId3 = faultService.addEntityToDB(fault3);
        assertEquals(3, faultRepository.count());

        faultService.setAllFaultsAsInactiveForCertainCar("SBE33212");
        Fault deletedFault1 = faultService.getEntityById(faultId1);
        Fault deletedFault2 = faultService.getEntityById(faultId2);
        Fault deletedFault3 = faultService.getEntityById(faultId3);
        assertAll(()->{
            assertFalse(deletedFault1.getIsActive());
            assertFalse(deletedFault2.getIsActive());
            assertFalse(deletedFault3.getIsActive());
        });
    }
}
