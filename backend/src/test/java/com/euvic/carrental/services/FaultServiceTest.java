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

import java.util.Date;
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

        parkingRepository.save(parking1);
        parkingRepository.save(parking2);
        parkingRepository.save(parking3);

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


        final Car car = new Car(null, "photoNr2", "SBE33212", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                new Date(2000, 3, 25), 2000, true, true, 120000, modelService.getEntityByName("Astra"),
                parkingService.getEntityByTown("Radom").get(0), colourService.getEntityByName("Blue"), typeService.getEntityByName("Coupe"));

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
        final Car car = carService.getEntityByLicensePlate("SBE33212");
        final CarDTO carDTO = carService.getDTOByLicensePlate("SBE33212");

        final Fault fault = new Fault(car, "nothing", true);

        final FaultDTO faultDTO = new FaultDTO(carDTO, "nothing", true);

        final Fault restModelToEntityModel = faultService.mapRestModel(faultDTO);
        assertAll(() -> {
            assertEquals(restModelToEntityModel.getCar(), fault.getCar());
            assertEquals(restModelToEntityModel.getId(), fault.getId());
            assertEquals(restModelToEntityModel.getDescribe(), fault.getDescribe());
            assertEquals(restModelToEntityModel.getIsActive(), fault.getIsActive());
        });
    }

    @Test
    void returnDBFaultEntity() {
        final Car car = carService.getEntityByLicensePlate("SBE33212");

        final Fault fault = new Fault(car, "nothing", true);

        assertEquals(0, faultRepository.count());
        faultRepository.save(fault);
        assertEquals(1, faultRepository.count());

        final Fault serviceFault = faultService.getEntitiesByCar(car).get(0);

        assertAll(() -> {
            assertNotEquals(null, serviceFault.getId());
            assertEquals(fault.getCar(), serviceFault.getCar());
            assertEquals(fault.getDescribe(), serviceFault.getDescribe());
            assertEquals(fault.getIsActive(), serviceFault.getIsActive());
        });
    }

    @Test
    void returnDBFaultDTO() {
        final Car car = carService.getEntityByLicensePlate("SBE33212");

        final Fault fault = new Fault(car, "nothing", true);
        assertEquals(0, faultRepository.count());
        faultRepository.save(fault);
        assertEquals(1, faultRepository.count());

        final FaultDTO faultDTO = faultService.getDTOByCar(car).get(0);

        assertAll(() -> {
            assertEquals(fault.getIsActive(), faultDTO.getIsActive());
            assertEquals(fault.getDescribe(), faultDTO.getDescribe());
            assertEquals(fault.getCar().getLicensePlate(), faultDTO.getCarDTO().getLicensePlate());
        });
    }

    @Test
    void returnAllDBFaultsDTO() {
        final Car car = carService.getEntityByLicensePlate("SBE33212");

        final Fault fault1 = new Fault(car, "sd", true);
        final Fault fault2 = new Fault(car, "dd", true);
        final Fault fault3 = new Fault(car, "we", true);

        assertEquals(0, faultRepository.count());
        faultRepository.save(fault1);
        faultRepository.save(fault2);
        faultRepository.save(fault3);
        assertEquals(3, faultRepository.count());

        final List<FaultDTO> faultDTOList = faultService.getAll();

        assertEquals(faultRepository.count(), faultDTOList.size());
    }

    @Test
    void whenCarDTOGiven_shouldAddEntityCarToDB() {
        final CarDTO carDTO = carService.getDTOByLicensePlate("SBE33212");

        final FaultDTO faultDTO = new FaultDTO(carDTO, "nothing", true);

        assertEquals(0, faultRepository.count());
        faultService.add(faultDTO);
        assertEquals(1, faultRepository.count());
    }
}
