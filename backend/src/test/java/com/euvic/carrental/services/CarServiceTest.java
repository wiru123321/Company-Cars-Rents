package com.euvic.carrental.services;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.*;
import com.euvic.carrental.responses.CarDTO;
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
public class CarServiceTest {

    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepository;
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
    }

    @AfterEach
    void tearDown() {
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
    void whenCarDTOGiven_thenReturnCarEntity() {
        final Car car = new Car(null, "photoNr1", "WN101", 100, 4, 5, 5,
                gearboxTypeService.getEntityByName("Automatic"), fuelTypeService.getEntityByName("Gasoline"),
                new Date(2000, 03, 25), 1990, true, true, 200000, modelService.getEntityByName("C350"),
                parkingService.getEntityByTown("Katowice").get(0), colourService.getEntityByName("Red"), typeService.getEntityByName("Sedan"));

        final CarDTO carDTO = new CarDTO("photoNr1", "WN101", 100, 4, 5, 5,
                gearboxTypeService.getDTOByName("Automatic"), fuelTypeService.getDTOByName("Gasoline"),
                new Date(2000, 03, 25), 1990, true, true, 200000, modelService.getDTOByName("C350"),
                parkingService.getDTOByTown("Katowice").get(0), colourService.getDTOByName("Red"), typeService.getDTOByName("Sedan"));
        final Car restModelToEntityModel = carService.mapRestModel(carDTO);
        assertAll(() -> {
            assertEquals(restModelToEntityModel.getId(), car.getId());
            assertEquals(restModelToEntityModel.getPhotoInFolderName(), car.getPhotoInFolderName());
            assertEquals(restModelToEntityModel.getLicensePlate(), car.getLicensePlate());
            assertEquals(restModelToEntityModel.getEnginePower(), car.getEnginePower());
            assertEquals(restModelToEntityModel.getCapacityOfTrunkScale(), car.getCapacityOfTrunkScale());
            assertEquals(restModelToEntityModel.getCapacityOfPeople(), car.getCapacityOfPeople());
            assertEquals(restModelToEntityModel.getDoorsNumber(), car.getDoorsNumber());
            assertEquals(restModelToEntityModel.getGearboxType(), car.getGearboxType());
            assertEquals(restModelToEntityModel.getFuelType(), car.getFuelType());
            assertEquals(restModelToEntityModel.getLastInspection(), car.getLastInspection());
            assertEquals(restModelToEntityModel.getProductionYear(), car.getProductionYear());
            assertEquals(restModelToEntityModel.getIsActive(), car.getIsActive());
            assertEquals(restModelToEntityModel.getIsOnCompany(), car.getIsOnCompany());
            assertEquals(restModelToEntityModel.getMileage(), car.getMileage());
            assertEquals(restModelToEntityModel.getModel(), car.getModel());
            assertEquals(restModelToEntityModel.getParking(), car.getParking());
            assertEquals(restModelToEntityModel.getColour(), car.getColour());
            assertEquals(restModelToEntityModel.getFuelType(), car.getFuelType());
        });
    }

    @Test
    void returnDBCarEntity() {
        final Car car = new Car(null, "photoNr1", "WN101", 100, 4, 5, 5,
                gearboxTypeService.getEntityByName("Automatic"), fuelTypeService.getEntityByName("Gasoline"),
                new Date(2000, 03, 25), 1990, true, true, 200000, modelService.getEntityByName("C350"),
                parkingService.getEntityByTown("Katowice").get(0), colourService.getEntityByName("Red"), typeService.getEntityByName("Sedan"));

        assertEquals(0, carRepository.count());
        carRepository.save(car);
        assertEquals(1, carRepository.count());
        final Car serviceCar = carService.getEntityByLicensePlate("WN101");

        assertAll(() -> {
            assertNotEquals(null, serviceCar.getId());
            assertEquals(car.getPhotoInFolderName(), serviceCar.getPhotoInFolderName());
            assertEquals(car.getLicensePlate(), serviceCar.getLicensePlate());
            assertEquals(car.getEnginePower(), serviceCar.getEnginePower());
            assertEquals(car.getCapacityOfTrunkScale(), serviceCar.getCapacityOfTrunkScale());
            assertEquals(car.getCapacityOfPeople(), serviceCar.getCapacityOfPeople());
            assertEquals(car.getDoorsNumber(), serviceCar.getDoorsNumber());
            assertEquals(car.getGearboxType(), serviceCar.getGearboxType());
            assertEquals(car.getFuelType(), serviceCar.getFuelType());
            assertEquals(car.getLastInspection(), serviceCar.getLastInspection());
            assertEquals(car.getProductionYear(), serviceCar.getProductionYear());
            assertEquals(car.getIsActive(), serviceCar.getIsActive());
            assertEquals(car.getIsOnCompany(), serviceCar.getIsOnCompany());
            assertEquals(car.getMileage(), serviceCar.getMileage());
            assertEquals(car.getModel(), serviceCar.getModel());
            assertEquals(car.getParking(), serviceCar.getParking());
            assertEquals(car.getColour(), serviceCar.getColour());
            assertEquals(car.getFuelType(), serviceCar.getFuelType());
        });
    }

    @Test
    void returnDBCarDTO() {
        final Car car = new Car(null, "photoNr1", "WN101", 100, 4, 5, 5,
                gearboxTypeService.getEntityByName("Automatic"), fuelTypeService.getEntityByName("Gasoline"),
                new Date(2000, 03, 25), 1990, true, true, 200000, modelService.getEntityByName("C350"),
                parkingService.getEntityByTown("Katowice").get(0), colourService.getEntityByName("Red"), typeService.getEntityByName("Sedan"));
        assertEquals(0, carRepository.count());
        carRepository.save(car);
        assertEquals(1, carRepository.count());

        final CarDTO serviceCarDTO = carService.getDTOByLicensePlate("WN101");

        assertAll(() -> {
            assertEquals(car.getPhotoInFolderName(), serviceCarDTO.getPhotoInFolderName());
            assertEquals(car.getLicensePlate(), serviceCarDTO.getLicensePlate());
            assertEquals(car.getEnginePower(), serviceCarDTO.getEnginePower());
            assertEquals(car.getCapacityOfTrunkScale(), serviceCarDTO.getCapacityOfTrunkScale());
            assertEquals(car.getCapacityOfPeople(), serviceCarDTO.getCapacityOfPeople());
            assertEquals(car.getDoorsNumber(), serviceCarDTO.getDoorsNumber());
            assertEquals(car.getGearboxType().getName(), serviceCarDTO.getGearBoxTypeDTO().getName());
            assertEquals(car.getFuelType().getName(), serviceCarDTO.getFuelTypeDTO().getName());
            assertEquals(car.getLastInspection(), serviceCarDTO.getLastInspection());
            assertEquals(car.getProductionYear(), serviceCarDTO.getProductionYear());
            assertEquals(car.getIsActive(), serviceCarDTO.getIsActive());
            assertEquals(car.getIsOnCompany(), serviceCarDTO.getIsOnCompany());
            assertEquals(car.getMileage(), serviceCarDTO.getMileage());
            assertEquals(car.getModel().getName(), serviceCarDTO.getModelDTO().getName());
            assertEquals(car.getParking().getTown(), serviceCarDTO.getParkingDTO().getTown());
            assertEquals(car.getColour().getName(), serviceCarDTO.getColourDTO().getName());
            assertEquals(car.getFuelType().getName(), serviceCarDTO.getFuelTypeDTO().getName());
        });
    }

    @Test
    void returnAllDBCarsDTO() {
        final Car car1 = new Car(null, "photoNr1", "WN101", 100, 4, 5, 5,
                gearboxTypeService.getEntityByName("Automatic"), fuelTypeService.getEntityByName("Gasoline"),
                new Date(2000, 3, 25), 1990, true, true, 200000, modelService.getEntityByName("C350"),
                parkingService.getEntityByTown("Katowice").get(0), colourService.getEntityByName("Red"), typeService.getEntityByName("Sedan"));

        final Car car2 = new Car(null, "photoNr2", "SBE33212", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                new Date(2000, 3, 25), 2000, true, true, 120000, modelService.getEntityByName("Astra"),
                parkingService.getEntityByTown("Radom").get(0), colourService.getEntityByName("Blue"), typeService.getEntityByName("Coupe"));

        final Car car3 = new Car(null, "photoNr3", "SBE11212", 250, 4, 5, 5,
                gearboxTypeService.getEntityByName("Automatic"), fuelTypeService.getEntityByName("Diesel"),
                new Date(2015, 9, 11), 2014, true, true, 100000, modelService.getEntityByName("M5"),
                parkingService.getEntityByTown("Kielce").get(0), colourService.getEntityByName("Green"), typeService.getEntityByName("Sedan"));

        assertEquals(0, carRepository.count());
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        assertEquals(3, carRepository.count());

        final List<CarDTO> carDTOList = carService.getAll();

        assertEquals(carRepository.count(), carDTOList.size());
    }

    @Test
    void whenCarDTOGiven_shouldAddEntityCarToDB() {

        final CarDTO carDTO = new CarDTO("photoNr1", "WN101", 100, 4, 5, 5,
                gearboxTypeService.getDTOByName("Automatic"), fuelTypeService.getDTOByName("Gasoline"),
                new Date(2000, 3, 25), 1990, true, true, 200000, modelService.getDTOByName("C350"),
                parkingService.getDTOByTown("Katowice").get(0), colourService.getDTOByName("Red"), typeService.getDTOByName("Sedan"));

        assertEquals(0, carRepository.count());
        carService.add(carDTO);
        assertEquals(1, carRepository.count());
    }
}