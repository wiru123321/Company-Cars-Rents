package com.euvic.carrental.services;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.*;
import com.euvic.carrental.responses.CarDTO;
import com.euvic.carrental.responses.RentHistoryDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class RentHistoryServiceTest {

    @Autowired
    private RentHistoryRepository rentHistoryRepository;

    @Autowired
    private RentHistoryService rentHistoryService;

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
    private ParkingHistoryService parkingHistoryService;

    @Autowired
    private ParkingHistoryRepository parkingHistoryRepository;

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
        rentHistoryRepository.deleteAll();
        parkingHistoryRepository.deleteAll();
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
    void whenRentHistoryEntityGiven_shouldAddRentHistoryEntityToDB() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final ParkingHistory parkingHistory1 = new ParkingHistory(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final ParkingHistory parkingHistory2 = new ParkingHistory(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingHistoryId1 = parkingHistoryService.addEntityToDB(parkingHistory1);
        final Long parkingHistoryId2 = parkingHistoryService.addEntityToDB(parkingHistory2);

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

        final RentHistory rentHistory = new RentHistory(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212",true)
                , LocalDateTime.of(2000, 3, 25, 10, 0), LocalDateTime.of(2000, 3, 25, 18, 0)
                , parkingHistoryService.getEntityById(parkingHistoryId1), parkingHistoryService.getEntityById(parkingHistoryId2), true, false, "", "");

        assertEquals(0, rentHistoryRepository.count());
        rentHistoryService.addEntityToDB(rentHistory);
        assertEquals(1, rentHistoryRepository.count());
    }


    @Test
    void shouldReturnDBRentHistoryEntity() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final ParkingHistory parkingHistory1 = new ParkingHistory(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final ParkingHistory parkingHistory2 = new ParkingHistory(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingHistoryId1 = parkingHistoryService.addEntityToDB(parkingHistory1);
        final Long parkingHistoryId2 = parkingHistoryService.addEntityToDB(parkingHistory2);

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

        final RentHistory rentHistory = new RentHistory(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212",true)
                , LocalDateTime.of(2000, 3, 25, 10, 0), LocalDateTime.of(2000, 3, 25, 18, 0)
                , parkingHistoryService.getEntityById(parkingHistoryId1), parkingHistoryService.getEntityById(parkingHistoryId2), true, false, "", "");

        assertEquals(0, rentHistoryRepository.count());
        final Long rentId = rentHistoryService.addEntityToDB(rentHistory);
        assertEquals(1, rentHistoryRepository.count());
        final RentHistory serviceRent1 = rentHistoryService.getEntityById(rentId);
        final RentHistory serviceRent2 = rentHistoryService.getEntityByCarAndDateFrom(car, LocalDateTime.of(2000, 3, 25, 10, 0));

        assertAll(() -> {
            assertNotEquals(null, serviceRent1.getId());
            assertEquals(rentHistory.getUser(), serviceRent1.getUser());
            assertEquals(rentHistory.getCar(), serviceRent1.getCar());
            assertEquals(rentHistory.getDateFrom(), serviceRent1.getDateFrom());
            assertEquals(rentHistory.getDateTo(), serviceRent1.getDateTo());
            assertEquals(rentHistory.getParkingHistoryFrom(), serviceRent1.getParkingHistoryFrom());
            assertEquals(rentHistory.getParkingHistoryTo(), serviceRent1.getParkingHistoryTo());
            assertEquals(rentHistory.getIsActive(), serviceRent1.getIsActive());

            assertNotEquals(null, serviceRent2.getId());
            assertEquals(rentHistory.getUser(), serviceRent2.getUser());
            assertEquals(rentHistory.getCar(), serviceRent2.getCar());
            assertEquals(rentHistory.getDateFrom(), serviceRent2.getDateFrom());
            assertEquals(rentHistory.getDateTo(), serviceRent2.getDateTo());
            assertEquals(rentHistory.getParkingHistoryFrom(), serviceRent2.getParkingHistoryFrom());
            assertEquals(rentHistory.getParkingHistoryTo(), serviceRent2.getParkingHistoryTo());
            assertEquals(rentHistory.getIsActive(), serviceRent2.getIsActive());
        });
    }

    @Test
    void whenRentHistoryDTOGiven_thenReturnRentHistoryEntity() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final ParkingHistory parkingHistory1 = new ParkingHistory(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final ParkingHistory parkingHistory2 = new ParkingHistory(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingHistoryId1 = parkingHistoryService.addEntityToDB(parkingHistory1);
        final Long parkingHistoryId2 = parkingHistoryService.addEntityToDB(parkingHistory2);

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

        final LocalDateTime dateFrom = LocalDateTime.of(2000, 3, 25, 10, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2000, 3, 30, 12, 0);
        final RentHistory rentHistory = new RentHistory(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212",true)
                , dateFrom, dateTo, parkingHistoryService.getEntityById(parkingHistoryId1), parkingHistoryService.getEntityById(parkingHistoryId2), true, false, "", "");

        final Long rentHistoryId = rentHistoryService.addEntityToDB(rentHistory);

        final RentHistoryDTO rentHistoryDTO = rentHistoryService.getDTOById(rentHistoryId);

        final RentHistory restModelToEntityModel = rentHistoryService.mapRestModel(null, rentHistoryDTO, parkingHistoryId1, parkingHistoryId2);
        assertAll(() -> {
            assertNotEquals(restModelToEntityModel.getId(), rentHistory.getId());
            assertEquals(restModelToEntityModel.getUser(), rentHistory.getUser());
            assertEquals(restModelToEntityModel.getCar(), rentHistory.getCar());
            assertEquals(restModelToEntityModel.getDateFrom(), rentHistory.getDateFrom());
            assertEquals(restModelToEntityModel.getDateTo(), rentHistory.getDateTo());
            assertEquals(restModelToEntityModel.getParkingHistoryFrom(), rentHistory.getParkingHistoryFrom());
            assertEquals(restModelToEntityModel.getParkingHistoryTo(), rentHistory.getParkingHistoryTo());
            assertEquals(restModelToEntityModel.getIsActive(), rentHistory.getIsActive());
        });
    }


    @Test
    void shouldReturnDBRentHistoryDTO() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final ParkingHistory parkingHistory1 = new ParkingHistory(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final ParkingHistory parkingHistory2 = new ParkingHistory(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingHistoryId1 = parkingHistoryService.addEntityToDB(parkingHistory1);
        final Long parkingHistoryId2 = parkingHistoryService.addEntityToDB(parkingHistory2);

        final Car car = new Car(null, "SBE33212", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2000
                , true, 120000, modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId1), colourService.getEntityByName("Blue"), typeService.getEntityByName("Coupe"));

        carRepository.save(car);
        final CarDTO carDTO = carService.getDTOByLicensePlate("SBE33212");

        final Role role1 = new Role(null, "Admin");
        final Role role2 = new Role(null, "User");

        roleRepository.save(role1);
        roleRepository.save(role2);

        final User user = new User(null, "login", "password", "email@email.com", "name", "surname"
                , "123789456", roleService.getEntityByRoleName("User"));

        userRepository.save(user);

        final LocalDateTime dateFrom = LocalDateTime.of(2000, 3, 25, 10, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2000, 3, 30, 12, 0);
        final RentHistory rentHistory = new RentHistory(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212",true)
                , dateFrom, dateTo, parkingHistoryService.getEntityById(parkingHistoryId1), parkingHistoryService.getEntityById(parkingHistoryId2), true, false, "", "");

        final Long rentHistoryId = rentHistoryService.addEntityToDB(rentHistory);

        final RentHistoryDTO rentHistoryDTO1 = rentHistoryService.getDTOByCarDTOAndDateFrom(carDTO, dateFrom);

        assertAll(() -> {
            assertEquals(rentHistory.getUser().getLogin(), rentHistoryDTO1.getUserDTO().getLogin());
            assertEquals(rentHistory.getCar().getLicensePlate(), rentHistoryDTO1.getCarDTO().getLicensePlate());
            assertEquals(rentHistory.getDateFrom(), rentHistoryDTO1.getDateFrom());
            assertEquals(rentHistory.getDateTo(), rentHistoryDTO1.getDateTo());
            assertEquals(rentHistory.getParkingHistoryFrom().getTown(), rentHistoryDTO1.getParkingHistoryDTOFrom().getTown());
            assertEquals(rentHistory.getParkingHistoryTo().getTown(), rentHistoryDTO1.getParkingHistoryDTOTo().getTown());
            assertEquals(rentHistory.getIsActive(), rentHistoryDTO1.getIsActive());
        });
    }


    @Test
    void shouldReturnAllDBRentsHistoryDTO() {
        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final ParkingHistory parkingHistory1 = new ParkingHistory(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final ParkingHistory parkingHistory2 = new ParkingHistory(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final ParkingHistory parkingHistory3 = new ParkingHistory(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final ParkingHistory parkingHistory4 = new ParkingHistory(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final ParkingHistory parkingHistory5 = new ParkingHistory(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final ParkingHistory parkingHistory6 = new ParkingHistory(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);

        final Long parkingId1 = parkingService.addEntityToDB(parking1);
        final Long parkingHistoryId1 = parkingHistoryService.addEntityToDB(parkingHistory1);
        final Long parkingHistoryId2 = parkingHistoryService.addEntityToDB(parkingHistory2);
        final Long parkingHistoryId3 = parkingHistoryService.addEntityToDB(parkingHistory3);
        final Long parkingHistoryId4 = parkingHistoryService.addEntityToDB(parkingHistory4);
        final Long parkingHistoryId5 = parkingHistoryService.addEntityToDB(parkingHistory4);
        final Long parkingHistoryId6 = parkingHistoryService.addEntityToDB(parkingHistory5);

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

        final LocalDateTime dateFrom = LocalDateTime.of(2000, 3, 25, 10, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2000, 3, 30, 12, 0);
        final RentHistory rentHistory1 = new RentHistory(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212",true)
                , dateFrom, dateTo, parkingHistoryService.getEntityById(parkingHistoryId1), parkingHistoryService.getEntityById(parkingHistoryId2), true, false, "", "");
        final RentHistory rentHistory2 = new RentHistory(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212",true)
                , dateFrom, dateTo, parkingHistoryService.getEntityById(parkingHistoryId3), parkingHistoryService.getEntityById(parkingHistoryId4), true, false, "", "");
        final RentHistory rentHistory3 = new RentHistory(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212",true)
                , dateFrom, dateTo, parkingHistoryService.getEntityById(parkingHistoryId5), parkingHistoryService.getEntityById(parkingHistoryId6), true, false, "", "");

        assertEquals(0, rentHistoryRepository.count());
        final Long rentHistoryId1 = rentHistoryService.addEntityToDB(rentHistory1);
        final Long rentHistoryId2 = rentHistoryService.addEntityToDB(rentHistory2);
        final Long rentHistoryId3 = rentHistoryService.addEntityToDB(rentHistory3);
        assertEquals(3, rentHistoryRepository.count());

        final List<RentHistoryDTO> rentHistoryDTOList = rentHistoryService.getAllDTOs();

        assertEquals(rentHistoryRepository.count(), rentHistoryDTOList.size());
    }

    @Test
    void shouldReturnListOfRentDTOListWithUserHistoryRents() {
        final ParkingHistory parking1 = new ParkingHistory(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final ParkingHistory parking2 = new ParkingHistory(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        final Long parkingId1 = parkingHistoryService.addEntityToDB(parking1);
        final Long parkingId2 = parkingHistoryService.addEntityToDB(parking2);
        final Long parkingId3 = parkingService.addEntityToDB(parking3);

        final Car car = new Car(null, "SBE33212", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                LocalDateTime.of(2000, 3, 25, 0, 0), 2000, true, 120000
                , modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId3), colourService.getEntityByName("Blue"), typeService.getEntityByName("Coupe"));

        carRepository.save(car);

        final Role role1 = new Role(null, "Admin");
        final Role role2 = new Role(null, "User");

        roleRepository.save(role1);
        roleRepository.save(role2);

        final User user = new User(null, "login", "password", "email@email.com", "name", "surname", "123789456", roleService.getEntityByRoleName("User"));

        userRepository.save(user);

        final LocalDateTime dateFrom = LocalDateTime.of(2000, 3, 25, 0, 0);
        final LocalDateTime dateTo = LocalDateTime.of(2000, 3, 30, 0, 0);

        final RentHistory rent1 = new RentHistory(null, userService.getEntityByLogin("login"), carRepository.findByLicensePlateAndIsOnCompany("SBE33212",true), dateFrom, dateTo
                , parkingHistoryService.getEntityById(parkingId1), parkingHistoryService.getEntityById(parkingId2), true, true, "comment", "Response");

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        final List<RentHistoryDTO> list = new ArrayList<>();
        assertEquals(list, rentHistoryService.getUserRentHistoryDTOs());
        final Long rentId1 = rentHistoryService.addEntityToDB(rent1);
        final RentHistoryDTO rentHistoryDTO = rentHistoryService.getDTOById(rentId1);
        list.add(rentHistoryDTO);
        assertEquals(list, rentHistoryService.getUserRentHistoryDTOs());

    }
}