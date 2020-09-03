package com.euvic.carrental.services;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.*;
import com.euvic.carrental.responses.*;
import com.euvic.carrental.responses.User.UserRentInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
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

        final Car car = new Car(null, "SBE33212", 120, 1, 4, 3,
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

        final Rent rent = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true)
                , LocalDateTime.of(2000, 3, 25, 10, 0), LocalDateTime.of(2000, 3, 25, 18, 0)
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId2), true, "comment", "Response", "");

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

        final Car car = new Car(null, "SBE33212", 120, 1, 4, 3,
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

        final Rent rent = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true)
                , LocalDateTime.of(2000, 3, 25, 10, 0), LocalDateTime.of(2000, 3, 25, 18, 0)
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId1), true, "comment", "Response", "");

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

        final Car car = new Car(null, "SBE33212", 120, 1, 4, 3,
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
        final Rent rent = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId2), true, "comment", "Response", "");
        final Long rentId = rentService.addEntityToDB(rent);

        final RentDTO rentDTO = rentService.getDTOById(rentId);

        final Rent restModelToEntityModel = rentService.mapRestModel(null, rentDTO, parkingId1, parkingId2);
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

        final Car car = new Car(null, "SBE33212", 120, 1, 4, 3,
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
        final Rent rent = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId1), true, "comment", "Response", "");
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
            assertEquals(rent.getIsActive(), true);
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

        final Car car = new Car(null, "SBE33212", 120, 1, 4, 3,
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
        final Rent rent1 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId2), true, "comment", "Response", "");
        final Rent rent2 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId2), parkingService.getEntityById(parkingId3), true, "comment", "Response", "");
        final Rent rent3 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId3), parkingService.getEntityById(parkingId1), true, "comment", "Response", "");

        assertEquals(0, rentRepository.count());
        final Long rentId1 = rentService.addEntityToDB(rent1);
        final Long rentId2 = rentService.addEntityToDB(rent2);
        final Long rentId3 = rentService.addEntityToDB(rent3);
        assertEquals(3, rentRepository.count());

        final List<RentDTO> rentDTOList = rentService.getAllDTOs();

        assertEquals(rentRepository.count(), rentDTOList.size());
    }

    @Test
    void givenRent_shouldDeleteItFromRepository() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);

        final Car car = new Car(null, "SBE33212", 120, 1, 4, 3,
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
        final Rent rent1 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId2), true, "comment", "Response", "");
        final Rent rent2 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId2), parkingService.getEntityById(parkingId3), true, "comment", "Response", "");
        final Rent rent3 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId3), parkingService.getEntityById(parkingId1), true, "comment", "Response", "");
        final Long rentId1 = rentService.addEntityToDB(rent1);
        final Long rentId2 = rentService.addEntityToDB(rent2);
        final Long rentId3 = rentService.addEntityToDB(rent3);

        final Rent rentToDelete = rentService.getEntityById(rentId1);

        assertEquals(3, rentRepository.count());
        rentService.deleteRent(rentToDelete);
        assertEquals(2, rentRepository.count());
    }

    @Test
    void shouldReturnTrueWhenRentInTimeRangeNotExist() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);

        final Car car = new Car(null, "SBE33212", 120, 1, 4, 3,
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
        final Rent rent1 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId2), true, "comment", "Response", "");

        final Long rentId1 = rentService.addEntityToDB(rent1);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));

        final RentDTO rentDTO = new RentDTO();
        rentDTO.setDateFrom(LocalDateTime.of(2000, 3, 25, 0, 0));
        rentDTO.setDateTo(LocalDateTime.of(2000, 3, 30, 0, 0));

        assertFalse(rentService.checkMyRentsBeforeAddNewRent(rentDTO));

        rentDTO.setDateFrom(LocalDateTime.of(2000, 5, 25, 0, 0));
        rentDTO.setDateTo(LocalDateTime.of(2000, 5, 28, 0, 0));

        assertTrue(rentService.checkMyRentsBeforeAddNewRent(rentDTO));
    }


    @Test
    void whenRentIsNotActive_addItToList_ReturnRentPendingDTOList() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking4 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking5 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking6 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking7 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking8 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);
        final Long parkingId4 = parkingService.addEntityToDB(parking4);
        final Long parkingId5 = parkingService.addEntityToDB(parking5);
        final Long parkingId6 = parkingService.addEntityToDB(parking6);
        final Long parkingId7 = parkingService.addEntityToDB(parking7);
        final Long parkingId8 = parkingService.addEntityToDB(parking8);

        final Car car = new Car(null, "SBE33212", 120, 1, 4, 3,
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
        final Rent rent1 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId2), false, "comment", "Response", "");
        final Rent rent2 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId3), parkingService.getEntityById(parkingId4), false, "comment", "Response", "");
        final Rent rent3 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId5), parkingService.getEntityById(parkingId6), false, "comment", "Response", "");
        final Rent rent4 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId7), parkingService.getEntityById(parkingId8), false, "comment", "Response", "");


        final List<RentPendingDTO> list = new ArrayList<>();

        assertEquals(list, rentService.getAllPendingRents());
        final Long rentId1 = rentService.addEntityToDB(rent1);
        final Long rentId2 = rentService.addEntityToDB(rent2);
        final Long rentId3 = rentService.addEntityToDB(rent3);
        final Long rentId4 = rentService.addEntityToDB(rent4);

        list.add(new RentPendingDTO(rent1.getId(), rent1.getReasonForTheLoan(), carService.mapToCarDTO(rent1.getCar()), rent1.getDateFrom()
                , rent1.getDateTo(), new ParkingDTO(rent1.getParkingFrom()), new ParkingDTO(rent1.getParkingTo())
                , new UserRentInfo(rent1.getUser().getName(), rent1.getUser().getSurname(), rent1.getUser().getPhoneNumber(), rent1.getUser().getEmail()), rent1.getAdminResponseForTheRequest()));

        list.add(new RentPendingDTO(rent2.getId(), rent2.getReasonForTheLoan(), carService.mapToCarDTO(rent2.getCar()), rent2.getDateFrom()
                , rent2.getDateTo(), new ParkingDTO(rent2.getParkingFrom()), new ParkingDTO(rent2.getParkingTo())
                , new UserRentInfo(rent2.getUser().getName(), rent2.getUser().getSurname(), rent2.getUser().getPhoneNumber(), rent2.getUser().getEmail()), rent2.getAdminResponseForTheRequest()));

        list.add(new RentPendingDTO(rent3.getId(), rent3.getReasonForTheLoan(), carService.mapToCarDTO(rent3.getCar()), rent3.getDateFrom()
                , rent3.getDateTo(), new ParkingDTO(rent3.getParkingFrom()), new ParkingDTO(rent3.getParkingTo())
                , new UserRentInfo(rent3.getUser().getName(), rent3.getUser().getSurname(), rent3.getUser().getPhoneNumber(), rent3.getUser().getEmail()), rent3.getAdminResponseForTheRequest()));

        list.add(new RentPendingDTO(rent4.getId(), rent4.getReasonForTheLoan(), carService.mapToCarDTO(rent4.getCar()), rent4.getDateFrom()
                , rent4.getDateTo(), new ParkingDTO(rent4.getParkingFrom()), new ParkingDTO(rent4.getParkingTo())
                , new UserRentInfo(rent4.getUser().getName(), rent4.getUser().getSurname(), rent4.getUser().getPhoneNumber(), rent4.getUser().getEmail()), rent4.getAdminResponseForTheRequest()));

        assertEquals(list, rentService.getAllPendingRents());
    }

    @Test
    void getDTOListOfActiveUserRents() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking4 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking5 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking6 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking7 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking8 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);
        final Long parkingId4 = parkingService.addEntityToDB(parking4);
        final Long parkingId5 = parkingService.addEntityToDB(parking5);
        final Long parkingId6 = parkingService.addEntityToDB(parking6);
        final Long parkingId7 = parkingService.addEntityToDB(parking7);
        final Long parkingId8 = parkingService.addEntityToDB(parking8);

        final Car car = new Car(null, "SBE33212", 120, 1, 4, 3,
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
        final User user1 = new User(null, "login1", "password", "email@email.com", "www", "eee", "333333333", roleService.getEntityByRoleName("Admin"));

        userRepository.save(user);
        userRepository.save(user1);

        final LocalDateTime dateFrom = LocalDateTime.of(2000, 3, 25, 0, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2000, 3, 30, 0, 0);
        final Rent rent1 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId2), false, "comment", "Response", "");
        final Rent rent2 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId3), parkingService.getEntityById(parkingId4), true, "comment", "Response", "");
        final Rent rent3 = new Rent(null, userService.getEntityByLogin("login1"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId5), parkingService.getEntityById(parkingId6), true, "comment", "Response", "");
        final Rent rent4 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId7), parkingService.getEntityById(parkingId8), true, "comment", "Response", "");


        List<RentPendingDTO> list = new ArrayList<>(rentService.getUserActiveRentDTOs());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));

        assertEquals(0, list.size());

        final Long rentId1 = rentService.addEntityToDB(rent1);
        final Long rentId2 = rentService.addEntityToDB(rent2);
        final Long rentId3 = rentService.addEntityToDB(rent3);
        final Long rentId4 = rentService.addEntityToDB(rent4);

        list = rentService.getUserActiveRentDTOs();
        assertEquals(2, list.size());
    }

    @Test
    void getDTOListOfInactiveUserRents() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking4 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking5 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking6 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking7 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking8 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);
        final Long parkingId4 = parkingService.addEntityToDB(parking4);
        final Long parkingId5 = parkingService.addEntityToDB(parking5);
        final Long parkingId6 = parkingService.addEntityToDB(parking6);
        final Long parkingId7 = parkingService.addEntityToDB(parking7);
        final Long parkingId8 = parkingService.addEntityToDB(parking8);

        final Car car = new Car(null, "SBE33212", 120, 1, 4, 3,
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
        final User user1 = new User(null, "login1", "password", "email@email.com", "www", "eee", "333333333", roleService.getEntityByRoleName("Admin"));

        userRepository.save(user);
        userRepository.save(user1);

        final LocalDateTime dateFrom = LocalDateTime.of(2000, 3, 25, 0, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2000, 3, 30, 0, 0);
        final Rent rent1 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId2), false, "comment", "Response", "");
        final Rent rent2 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId3), parkingService.getEntityById(parkingId4), true, "comment", "Response", "");
        final Rent rent3 = new Rent(null, userService.getEntityByLogin("login1"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId5), parkingService.getEntityById(parkingId6), true, "comment", "Response", "");
        final Rent rent4 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId7), parkingService.getEntityById(parkingId8), true, "comment", "Response", "");


        List<RentPendingDTO> list = new ArrayList<>(rentService.getUserInactiveRentDTOs());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));

        assertEquals(0, list.size());

        final Long rentId1 = rentService.addEntityToDB(rent1);
        final Long rentId2 = rentService.addEntityToDB(rent2);
        final Long rentId3 = rentService.addEntityToDB(rent3);
        final Long rentId4 = rentService.addEntityToDB(rent4);

        list = rentService.getUserInactiveRentDTOs();
        assertEquals(1, list.size());
    }

    @Test
    void getCarsWhichIsNotRented() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking4 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking5 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking6 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking7 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking8 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);
        final Long parkingId4 = parkingService.addEntityToDB(parking4);
        final Long parkingId5 = parkingService.addEntityToDB(parking5);
        final Long parkingId6 = parkingService.addEntityToDB(parking6);
        final Long parkingId7 = parkingService.addEntityToDB(parking7);
        final Long parkingId8 = parkingService.addEntityToDB(parking8);

        final Car car = new Car(null, "SBE00000", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2005, true, 120
                , modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId1), colourService.getEntityByName("Red"), typeService.getEntityByName("Coupe"));
        final Car car1 = new Car(null, "SBE11111", 150, 1, 4, 3,
                gearboxTypeService.getEntityByName("Automatic"), fuelTypeService.getEntityByName("Gasoline"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2004, true, 120000
                , modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId2), colourService.getEntityByName("Blue"), typeService.getEntityByName("Sedan"));
        final Car car2 = new Car(null, "SBE22222", 110, 1, 4, 3,
                gearboxTypeService.getEntityByName("Other"), fuelTypeService.getEntityByName("Petrol"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2020, true, 20000
                , modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId3), colourService.getEntityByName("Green"), typeService.getEntityByName("Coupe"));
        final Car car3 = new Car(null, "SBE33333", 320, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2007, true, 10000
                , modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId4), colourService.getEntityByName("Blue"), typeService.getEntityByName("Van"));

        carRepository.save(car);
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);

        final Role role1 = new Role(null, "Admin");
        final Role role2 = new Role(null, "User");

        roleRepository.save(role1);
        roleRepository.save(role2);

        final User user = new User(null, "login", "password", "email@email.com", "name", "surname", "123789456", roleService.getEntityByRoleName("User"));
        final User user1 = new User(null, "login1", "password", "email@email.com", "www", "eee", "333333333", roleService.getEntityByRoleName("Admin"));

        userRepository.save(user);
        userRepository.save(user1);

        final LocalDateTime dateFrom = LocalDateTime.now().plusDays(37);
        final LocalDateTime dateTo = LocalDateTime.now().plusDays(39);

        final LocalDateTime dateFrom1 = LocalDateTime.now().plusDays(40);
        final LocalDateTime dateTo1 = LocalDateTime.now().plusDays(44);

        final LocalDateTime dateFrom2 = LocalDateTime.now().plusDays(45);
        final LocalDateTime dateTo2 = LocalDateTime.now().plusDays(47);

        final LocalDateTime dateFrom3 = LocalDateTime.now().plusDays(48);
        final LocalDateTime dateTo3 = LocalDateTime.now().plusDays(55);

        final Rent rent1 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE00000", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId2), false, "comment", "Response", "");
        final Rent rent2 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE11111", true), dateFrom1, dateTo1
                , parkingService.getEntityById(parkingId3), parkingService.getEntityById(parkingId4), true, "comment", "Response", "");
        final Rent rent3 = new Rent(null, userService.getEntityByLogin("login1"), carRepository.findByLicensePlateAndIsOnCompany("SBE22222", true), dateFrom2, dateTo2
                , parkingService.getEntityById(parkingId5), parkingService.getEntityById(parkingId6), true, "comment", "Response", "");
        final Rent rent4 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33333", true), dateFrom3, dateTo3
                , parkingService.getEntityById(parkingId7), parkingService.getEntityById(parkingId8), true, "comment", "Response", "");

        final Long rentId1 = rentService.addEntityToDB(rent1);
        final Long rentId2 = rentService.addEntityToDB(rent2);
        final Long rentId3 = rentService.addEntityToDB(rent3);
        final Long rentId4 = rentService.addEntityToDB(rent4);

        final DateFromDateTo date1 = new DateFromDateTo(LocalDateTime.now().plusDays(36)
                , LocalDateTime.now().plusDays(47));

        final DateFromDateTo date2 = new DateFromDateTo(LocalDateTime.now().plusDays(31)
                , LocalDateTime.now().plusDays(35));

        List<CarDTO> list = new ArrayList<>(rentService.getActiveCarsBetweenDates(date2));
        assertEquals(4, list.size());

        list = rentService.getActiveCarsBetweenDates(date1);
        assertEquals(2, list.size());
    }

    @Test
    void whenRentEntityGiven_shouldAttachedCarAvailability() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking4 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking5 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking6 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking7 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking8 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);
        final Long parkingId4 = parkingService.addEntityToDB(parking4);
        final Long parkingId5 = parkingService.addEntityToDB(parking5);
        final Long parkingId6 = parkingService.addEntityToDB(parking6);
        final Long parkingId7 = parkingService.addEntityToDB(parking7);
        final Long parkingId8 = parkingService.addEntityToDB(parking8);

        final Car car = new Car(null, "SBE00000", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2005, true, 120
                , modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId1), colourService.getEntityByName("Red"), typeService.getEntityByName("Coupe"));

        carRepository.save(car);

        final Role role1 = new Role(null, "Admin");
        final Role role2 = new Role(null, "User");

        roleRepository.save(role1);
        roleRepository.save(role2);

        final User user = new User(null, "login", "password", "email@email.com", "name", "surname", "123789456", roleService.getEntityByRoleName("User"));
        final User user1 = new User(null, "login1", "password", "email@email.com", "www", "eee", "333333333", roleService.getEntityByRoleName("Admin"));

        userRepository.save(user);
        userRepository.save(user1);

        final LocalDateTime dateFrom = LocalDateTime.of(2020, 3, 27, 0, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2020, 3, 30, 0, 0);

        final LocalDateTime dateFrom1 = LocalDateTime.of(2020, 3, 20, 0, 0);
        final LocalDateTime dateTo1 = LocalDateTime.of(2020, 3, 26, 0, 0);


        final Rent rent1 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE00000", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId2), true, "comment", "Response", "");
        final Rent rent2 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE00000", true), dateFrom1, dateTo1
                , parkingService.getEntityById(parkingId3), parkingService.getEntityById(parkingId4), true, "comment", "Response", "");

        final Long rentId1 = rentService.addEntityToDB(rent1);
        final Long rentId2 = rentService.addEntityToDB(rent2);


        final LocalDateTime dateFrom2 = LocalDateTime.of(2020, 3, 28, 0, 0);
        final LocalDateTime dateTo2 = LocalDateTime.of(2020, 3, 29, 0, 0);

        final LocalDateTime dateFrom3 = LocalDateTime.of(2030, 9, 25, 0, 0);
        final LocalDateTime dateTo3 = LocalDateTime.of(2030, 9, 30, 0, 0);

        final Rent shouldNotBeAllowedRent = new Rent(100L, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE00000", true), dateFrom2, dateTo2
                , parkingService.getEntityById(parkingId5), parkingService.getEntityById(parkingId6), false, "comment", "Response", "");
        final Rent shouldBeAllowedRent = new Rent(101L, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE00000", true), dateFrom3, dateTo3
                , parkingService.getEntityById(parkingId7), parkingService.getEntityById(parkingId8), false, "comment", "Response", "");

        assertTrue(rentService.checkIfRentIsAllowedToBeRequested(shouldBeAllowedRent));
        assertFalse(rentService.checkIfRentIsAllowedToBeRequested(shouldNotBeAllowedRent));
    }

    @Test
    void whenUserGiven_deleteAllRentsBelongToThisUser() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking4 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking5 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking6 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking7 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking8 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking9 = new Parking(null, "Randomowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);
        final Long parkingId4 = parkingService.addEntityToDB(parking4);
        final Long parkingId5 = parkingService.addEntityToDB(parking5);
        final Long parkingId6 = parkingService.addEntityToDB(parking6);
        final Long parkingId7 = parkingService.addEntityToDB(parking7);
        final Long parkingId8 = parkingService.addEntityToDB(parking8);
        final Long parkingId9 = parkingService.addEntityToDB(parking9);

        final Car car = new Car(null, "SBE00000", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2005, true, 120
                , modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId9), colourService.getEntityByName("Red"), typeService.getEntityByName("Coupe"));


        carRepository.save(car);

        final Role role2 = new Role(null, "User");

        roleRepository.save(role2);

        final User user = new User(null, "login", "password", "email@email.com", "name", "surname", "123789456", roleService.getEntityByRoleName("User"));
        final User user1 = new User(null, "login1", "password", "email@email.com", "www", "eee", "333333333", roleService.getEntityByRoleName("User"));

        userRepository.save(user);
        userRepository.save(user1);

        final LocalDateTime dateFrom = LocalDateTime.now().plusDays(37);
        final LocalDateTime dateTo = LocalDateTime.now().plusDays(39);

        final LocalDateTime dateFrom1 = LocalDateTime.now().plusDays(40);
        final LocalDateTime dateTo1 = LocalDateTime.now().plusDays(44);

        final LocalDateTime dateFrom2 = LocalDateTime.now().plusDays(45);
        final LocalDateTime dateTo2 = LocalDateTime.now().plusDays(47);

        final LocalDateTime dateFrom3 = LocalDateTime.now().plusDays(48);
        final LocalDateTime dateTo3 = LocalDateTime.now().plusDays(55);

        final Rent rent1 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE00000", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId2), false, "comment", "Response", "");
        final Rent rent2 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE00000", true), dateFrom1, dateTo1
                , parkingService.getEntityById(parkingId3), parkingService.getEntityById(parkingId4), true, "comment", "Response", "");
        final Rent rent3 = new Rent(null, userService.getEntityByLogin("login1"), carRepository.findByLicensePlateAndIsOnCompany("SBE00000", true), dateFrom2, dateTo2
                , parkingService.getEntityById(parkingId5), parkingService.getEntityById(parkingId6), true, "comment", "Response", "");
        final Rent rent4 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE00000", true), dateFrom3, dateTo3
                , parkingService.getEntityById(parkingId7), parkingService.getEntityById(parkingId8), true, "comment", "Response", "");

        final Long rentId1 = rentService.addEntityToDB(rent1);
        final Long rentId2 = rentService.addEntityToDB(rent2);
        final Long rentId3 = rentService.addEntityToDB(rent3);
        final Long rentId4 = rentService.addEntityToDB(rent4);


        List<RentDTO> rentAllList = rentService.getAllDTOs();
        assertEquals(4, rentAllList.size());
        rentService.deleteRentsByUser(userService.getEntityByLogin("login"));
        rentAllList = rentService.getAllDTOs();
        assertEquals(1, rentAllList.size());
    }

    @Test
    void whenRentGiven_updateNextRentParkingFrom() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking4 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking9 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);
        final Long parkingId4 = parkingService.addEntityToDB(parking4);
        final Long parkingId9 = parkingService.addEntityToDB(parking9);

        final Car car = new Car(null, "SBE00000", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2005, true, 120
                , modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId9), colourService.getEntityByName("Red"), typeService.getEntityByName("Coupe"));

        carRepository.save(car);

        final Role role2 = new Role(null, "User");

        roleRepository.save(role2);

        final User user = new User(null, "login", "password", "email@email.com", "name", "surname", "123789456", roleService.getEntityByRoleName("User"));

        userRepository.save(user);

        final LocalDateTime dateFrom = LocalDateTime.now().plusDays(37);
        final LocalDateTime dateTo = LocalDateTime.now().plusDays(39);

        final LocalDateTime dateFrom1 = LocalDateTime.now().plusDays(40);
        final LocalDateTime dateTo1 = LocalDateTime.now().plusDays(44);


        final Rent rent1 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE00000", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId2), true, "comment", "Response", "");
        final Rent rent2 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE00000", true), dateFrom1, dateTo1
                , parkingService.getEntityById(parkingId3), parkingService.getEntityById(parkingId4), true, "comment", "Response", "");

        final Long rentId1 = rentService.addEntityToDB(rent1);
        final Long rentId2 = rentService.addEntityToDB(rent2);

        rentService.updateNextRent(rentService.getEntityById(rentId1));

        final Rent rent = rentService.getEntityById(rentId2);

        assertAll(() -> {
            assertEquals(rent.getParkingFrom().getTown(), parking2.getTown());
            assertEquals(rent.getParkingFrom().getPostalCode(), parking2.getPostalCode());
            assertEquals(rent.getParkingFrom().getStreetName(), parking2.getStreetName());
            assertEquals(rent.getParkingFrom().getComment(), parking2.getComment());
            assertEquals(rent.getParkingFrom().getNumber(), parking2.getNumber());
        });
    }

    @Test
    void whenRentGiven_updateNextRent_andDeleteThisRent() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking4 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
        final Parking parking9 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingId2 = parkingService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);
        final Long parkingId4 = parkingService.addEntityToDB(parking4);
        final Long parkingId9 = parkingService.addEntityToDB(parking9);

        final Car car = new Car(null, "SBE00000", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2005, true, 120
                , modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId9), colourService.getEntityByName("Red"), typeService.getEntityByName("Coupe"));

        carRepository.save(car);

        final Role role2 = new Role(null, "User");

        roleRepository.save(role2);

        final User user = new User(null, "login", "password", "email@email.com", "name", "surname", "123789456", roleService.getEntityByRoleName("User"));

        userRepository.save(user);

        final LocalDateTime dateFrom = LocalDateTime.now().plusDays(37);
        final LocalDateTime dateTo = LocalDateTime.now().plusDays(39);

        final LocalDateTime dateFrom1 = LocalDateTime.now().plusDays(40);
        final LocalDateTime dateTo1 = LocalDateTime.now().plusDays(44);


        final Rent rent1 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE00000", true), dateFrom, dateTo
                , parkingService.getEntityById(parkingId1), parkingService.getEntityById(parkingId2), true, "comment", "Response", "");
        final Rent rent2 = new Rent(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE00000", true), dateFrom1, dateTo1
                , parkingService.getEntityById(parkingId3), parkingService.getEntityById(parkingId4), true, "comment", "Response", "");

        final Long rentId1 = rentService.addEntityToDB(rent1);
        final Long rentId2 = rentService.addEntityToDB(rent2);

        List<RentDTO> rentDTOList = rentService.getAllDTOs();
        assertEquals(2, rentDTOList.size());

        rentService.deleteAndUpdateRentAndParkings(rentService.getEntityById(rentId1), true);

        rentDTOList = rentService.getAllDTOs();
        assertEquals(1, rentDTOList.size());
        final Rent rent = rentService.getEntityById(rentId2);

        assertAll(() -> {
            assertEquals(rent.getParkingFrom().getTown(), parking2.getTown());
            assertEquals(rent.getParkingFrom().getPostalCode(), parking2.getPostalCode());
            assertEquals(rent.getParkingFrom().getStreetName(), parking2.getStreetName());
            assertEquals(rent.getParkingFrom().getComment(), parking2.getComment());
            assertEquals(rent.getParkingFrom().getNumber(), parking2.getNumber());
        });

    }
}
