package com.euvic.carrental.services;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.*;
import com.euvic.carrental.responses.CarDTO;
import com.euvic.carrental.responses.RentDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class RentServiceTest {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private RentService rentService;

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

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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
        rentRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
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
    void whenRentEntityGiven_shouldAddRentEntityToDB() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);

        final Car car = new Car(null, "photoNr2", "SBE33212", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2000
                , true, 120000, modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId1), colourService.getEntityByName("Blue"), typeService.getEntityByName("Coupe"));

        carRepository.save(car);

        final Role role1 = new Role(null, "Admin");
        final Role role2 = new Role(null, "User");

        roleRepository.save(role1);
        roleRepository.save(role2);

        final User user = new User(null, "login", "password", "email@email.com", "name", "surname"
                , "123789456", roleService.getEntityByRoleName("User"));

        userRepository.save(user);

        final Rent rent = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlate("SBE33212")
                , LocalDateTime.of(2000, 3, 25, 10, 0), LocalDateTime.of(2000, 3, 25, 18, 0)
                , parkingService.getEntityById(parkingId1), null, true);

        assertEquals(0, rentRepository.count());
        rentService.addEntityToDB(rent);
        assertEquals(1, rentRepository.count());
    }

    @Test
    void returnDBRentEntity() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);

        final Car car = new Car(null, "photoNr2", "SBE33212", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2000, true, 120000
                , modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId1), colourService.getEntityByName("Blue"), typeService.getEntityByName("Coupe"));

        carRepository.save(car);

        final Role role1 = new Role(null, "Admin");
        final Role role2 = new Role(null, "User");

        roleRepository.save(role1);
        roleRepository.save(role2);

        final User user = new User(null, "login", "password", "email@email.com", "name", "surname", "123789456"
                , roleService.getEntityByRoleName("User"));

        userRepository.save(user);

        final Rent rent = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlate("SBE33212")
                , LocalDateTime.of(2000, 3, 25, 10, 0), LocalDateTime.of(2000, 3, 25, 18, 0)
                , parkingService.getEntityById(parkingId1), null, true);

        assertEquals(0, rentRepository.count());
        final Long rentId = rentService.addEntityToDB(rent);
        assertEquals(1, rentRepository.count());
        final Rent serviceRent1 = rentService.getEntityById(rentId);
        final Rent serviceRent2 = rentService.getEntityByCarAndDateFrom(car, LocalDateTime.of(2000, 3, 25, 10, 0));

        assertAll(() -> {
            assertNotEquals(null, serviceRent1.getId());
            assertEquals(rent.getUser(), serviceRent1.getUser());
            assertEquals(rent.getCar(), serviceRent1.getCar());
            assertEquals(rent.getDateFrom(), serviceRent1.getDateFrom());
            assertEquals(rent.getDateTo(), serviceRent1.getDateTo());
            assertEquals(rent.getParkingFrom(), serviceRent1.getParkingFrom());
            assertEquals(rent.getParkingTo(), serviceRent1.getParkingTo());
            assertEquals(rent.getIsActive(), serviceRent1.getIsActive());

            assertNotEquals(null, serviceRent2.getId());
            assertEquals(rent.getUser(), serviceRent2.getUser());
            assertEquals(rent.getCar(), serviceRent2.getCar());
            assertEquals(rent.getDateFrom(), serviceRent2.getDateFrom());
            assertEquals(rent.getDateTo(), serviceRent2.getDateTo());
            assertEquals(rent.getParkingFrom(), serviceRent2.getParkingFrom());
            assertEquals(rent.getParkingTo(), serviceRent2.getParkingTo());
            assertEquals(rent.getIsActive(), serviceRent2.getIsActive());
        });
    }

    @Test
    void whenRentDTOGiven_thenReturnRentEntity() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);

        final Car car = new Car(null, "photoNr2", "SBE33212", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2000, true, 120000
                , modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId1), colourService.getEntityByName("Blue"), typeService.getEntityByName("Coupe"));

        carRepository.save(car);

        final Role role1 = new Role(null, "Admin");
        final Role role2 = new Role(null, "User");

        roleRepository.save(role1);
        roleRepository.save(role2);

        final User user = new User(null, "login", "password", "email@email.com", "name", "surname", "123789456"
                , roleService.getEntityByRoleName("User"));

        userRepository.save(user);

        final LocalDate dateFFrom = LocalDate.of(2000, Month.MARCH, 25);
        final LocalDate dateTTo = LocalDate.of(2000, Month.MARCH, 30);
        final LocalDateTime dateFrom = LocalDateTime.of(2000, 3, 25, 0, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2000, 3, 30, 0, 0);
        final Rent rent = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlate("SBE33212"), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), null, true);
        final Long rentId = rentService.addEntityToDB(rent);

        final RentDTO rentDTO = rentService.getDTOById(rentId);

        final Rent restModelToEntityModel = rentService.mapRestModel(null, rentDTO, parkingId1, null);
        assertAll(() -> {
            assertNotEquals(restModelToEntityModel.getId(), rent.getId());
            assertEquals(restModelToEntityModel.getUser(), rent.getUser());
            assertEquals(restModelToEntityModel.getCar(), rent.getCar());
            assertEquals(restModelToEntityModel.getDateFrom(), rent.getDateFrom());
            assertEquals(restModelToEntityModel.getDateTo(), rent.getDateTo());
            assertEquals(restModelToEntityModel.getParkingFrom(), rent.getParkingFrom());
            assertEquals(restModelToEntityModel.getParkingTo(), rent.getParkingTo());
            assertEquals(restModelToEntityModel.getIsActive(), rent.getIsActive());
        });
    }

    @Test
    void shouldReturnDBRentDTO() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);

        final Car car = new Car(null, "photoNr2", "SBE33212", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2000, true, 120000
                , modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId1), colourService.getEntityByName("Blue"), typeService.getEntityByName("Coupe"));

        carRepository.save(car);
        final CarDTO carDTO = carService.getDTOByLicensePlate("SBE33212");

        final Role role1 = new Role(null, "Admin");
        final Role role2 = new Role(null, "User");

        roleRepository.save(role1);
        roleRepository.save(role2);

        final User user = new User(null, "login", "password", "email@email.com", "name", "surname", "123789456"
                , roleService.getEntityByRoleName("User"));

        userRepository.save(user);

        final LocalDateTime dateFrom = LocalDateTime.of(2000, 3, 25, 0, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2000, 3, 30, 0, 0);
        final Rent rent = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlate("SBE33212"), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), null, true);
        final Long rentId = rentService.addEntityToDB(rent);

        final RentDTO rentDTO1 = rentService.getDTOByCarDTOAndDateFrom(carDTO, dateFrom);

        assertAll(() -> {
            assertEquals(rent.getUser().getLogin(), rentDTO1.getUserDTO().getLogin());
            assertEquals(rent.getCar().getLicensePlate(), rentDTO1.getCarDTO().getLicensePlate());
            assertEquals(rent.getDateFrom(), rentDTO1.getDateFrom());
            assertEquals(rent.getDateTo(), rentDTO1.getDateTo());
            assertEquals(rent.getParkingFrom().getTown(), rentDTO1.getParkingDTOFrom().getTown());
            if (rent.getParkingTo() != null)
                assertEquals(rent.getParkingTo().getTown(), rentDTO1.getParkingDTOTo().getTown());
            assertEquals(rent.getIsActive(), rentDTO1.getIsActive());
        });
    }

    @Test
    void shouldReturnAllDBRentsDTO() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);

        final Car car = new Car(null, "photoNr2", "SBE33212", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2000, true, 120000
                , modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId1), colourService.getEntityByName("Blue"), typeService.getEntityByName("Coupe"));

        carRepository.save(car);

        final Role role1 = new Role(null, "Admin");
        final Role role2 = new Role(null, "User");

        roleRepository.save(role1);
        roleRepository.save(role2);

        final User user = new User(null, "login", "password", "email@email.com", "name", "surname", "123789456", roleService.getEntityByRoleName("User"));

        userRepository.save(user);

        final LocalDateTime dateFrom = LocalDateTime.of(2000, 3, 25, 0, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2000, 3, 30, 0, 0);
        final Rent rent1 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlate("SBE33212"), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), null, true);
        final Rent rent2 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlate("SBE33212"), dateFrom, dateTo
                , parkingService.getEntityById(parkingId2), null, true);
        final Rent rent3 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlate("SBE33212"), dateFrom, dateTo
                , parkingService.getEntityById(parkingId3), null, true);

        assertEquals(0, rentRepository.count());
        final Long rentId1 = rentService.addEntityToDB(rent1);
        final Long rentId2 = rentService.addEntityToDB(rent2);
        final Long rentId3 = rentService.addEntityToDB(rent3);
        assertEquals(3, rentRepository.count());

        final List<RentDTO> rentDTOList = rentService.getAllDTOs();

        assertEquals(rentRepository.count(), rentDTOList.size());
    }
}
