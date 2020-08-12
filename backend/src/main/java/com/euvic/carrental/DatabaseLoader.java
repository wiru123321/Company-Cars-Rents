package com.euvic.carrental;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.*;
import com.euvic.carrental.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;


@Component
public class DatabaseLoader implements CommandLineRunner {

    private final CarRepository carRepository;
    private final ColourRepository colourRepository;
    private final FaultRepository faultRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final GearboxTypeRepository gearboxTypeRepository;
    private final MarkRepository markRepository;
    private final ModelRepository modelRepository;
    private final ParkingRepository parkingRepository;
    private final ParkingHistoryRepository parkingHistoryRepository;
    private final RentHistoryRepository rentHistoryRepository;
    private final RentRepository rentRepository;
    private final RoleRepository roleRepository;
    private final TypeRepository typeRepository;
    private final UserRepository userRepository;

    private final CarService carService;
    private final ColourService colourService;
    private final FaultService faultService;
    private final FuelTypeService fuelTypeService;
    private final GearboxTypeService gearboxTypeService;
    private final MarkService markService;
    private final ModelService modelService;
    private final ParkingService parkingService;
    private final ParkingHistoryService parkingHistoryService;
    private final RentHistoryService rentHistoryService;
    private final RentService rentService;
    private final RoleService roleService;
    private final TypeService typeService;
    private final UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.username}")
    private String dataBase;

    @Autowired
    public DatabaseLoader(final CarRepository carRepository, final ColourRepository colourRepository,
                          final FaultRepository faultRepository, final MarkRepository markRepository,
                          final ModelRepository modelRepository, final ParkingRepository parkingRepository,
                          final RentHistoryRepository rentHistoryRepository, final RentRepository rentRepository,
                          final RoleRepository roleRepository, final TypeRepository typeRepository,
                          final UserRepository userRepository, final GearboxTypeRepository gearboxTypeRepository,
                          final FuelTypeRepository fuelTypeRepository, final ParkingHistoryRepository parkingHistoryRepository,
                          final CarService carService, final ColourService colourService, final FaultService faultService,
                          final FuelTypeService fuelTypeService, final GearboxTypeService gearboxTypeService,
                          final MarkService markService, final ModelService modelService, final ParkingService parkingService,
                          final ParkingHistoryService parkingHistoryService, final RentHistoryService rentHistoryService,
                          final RentService rentService, final RoleService roleService, final TypeService typeService,
                          final UserService userService) {
        this.carRepository = carRepository;
        this.colourRepository = colourRepository;
        this.faultRepository = faultRepository;
        this.markRepository = markRepository;
        this.modelRepository = modelRepository;
        this.parkingRepository = parkingRepository;
        this.rentHistoryRepository = rentHistoryRepository;
        this.rentRepository = rentRepository;
        this.roleRepository = roleRepository;
        this.typeRepository = typeRepository;
        this.userRepository = userRepository;
        this.gearboxTypeRepository = gearboxTypeRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.parkingHistoryRepository = parkingHistoryRepository;

        this.carService = carService;
        this.colourService = colourService;
        this.faultService = faultService;
        this.fuelTypeService = fuelTypeService;
        this.gearboxTypeService = gearboxTypeService;
        this.markService = markService;
        this.modelService = modelService;
        this.parkingService = parkingService;
        this.parkingHistoryService = parkingHistoryService;
        this.rentHistoryService = rentHistoryService;
        this.rentService = rentService;
        this.roleService = roleService;
        this.typeService = typeService;
        this.userService = userService;
    }

    @Override
    public void run(final String... strings) throws Exception {

        if (dataBase.equals("postgres")) {
            Path path = Paths.get("./DataBase/Types.txt");
            List<String> stringList = Files.readAllLines(path);
            for (final String name : stringList) {
                typeRepository.save(new Type(null, name));
            }
            stringList.clear();

            path = Paths.get("./DataBase/Roles.txt");
            stringList = Files.readAllLines(path);
            for (final String name : stringList) {
                roleRepository.save(new Role(null, name));
            }
            stringList.clear();


            path = Paths.get("./DataBase/Marks.txt");
            stringList = Files.readAllLines(path);
            for (final String name : stringList) {
                markRepository.save(new Mark(null, name));
            }
            stringList.clear();

            path = Paths.get("./DataBase/GearBoxTypes.txt");
            stringList = Files.readAllLines(path);
            for (final String name : stringList) {
                gearboxTypeRepository.save(new GearboxType(null, name));
            }
            stringList.clear();

            path = Paths.get("./DataBase/FuelTypes.txt");
            stringList = Files.readAllLines(path);
            for (final String name : stringList) {
                fuelTypeRepository.save(new FuelType(null, name));
            }
            stringList.clear();

            path = Paths.get("./DataBase/Colours.txt");
            stringList = Files.readAllLines(path);
            for (final String name : stringList) {
                colourRepository.save(new Colour(null, name));
            }
            stringList.clear();

            //FOR SECURITY TESTS
            //Haslo powinno byc encodowane wraz ze stworzeniem konta encrypter (Bcrypter) / powinniśmy zadbać żeby hasło zostało bezpiecznie przesłane do backendu
            // login i password w bazie danych nie mogą się powtarzać //TODO
            userRepository.save(new User(null, "admin123", passwordEncoder.encode("apassword123"), "admin@email.com", "Jan", "Kowalski", "123456789", roleRepository.findByName("ADMIN")));
            userRepository.save(new User(null, "user123", passwordEncoder.encode("upassword123"), "user@email.com", "Andrzej", "Wywrot", "123456798", roleRepository.findByName("EMPLOYEE")));
            userRepository.save(new User(null, "kama123", passwordEncoder.encode("upassword123"), "user@email.com", "Kamil", "Susek", "700100110", roleRepository.findByName("EMPLOYEE")));
            userRepository.save(new User(null, "walo123", passwordEncoder.encode("upassword123"), "walo@email.com", "Wojciech", "Waleszczyk", "666999666", roleRepository.findByName("EMPLOYEE")));


            final Model model1 = new Model(null, "C350", markService.getEntityByName("Audi"));
            final Model model2 = new Model(null, "Astra", markService.getEntityByName("Opel"));
            final Model model3 = new Model(null, "M5", markService.getEntityByName("BMW"));

            final Long modelId1 = modelService.addEntityToDB(model1);
            final Long modelId2 = modelService.addEntityToDB(model2);
            final Long modelId3 = modelService.addEntityToDB(model3);


            final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
            final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
            final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);
            final Parking parking4 = new Parking(null, "Bydgoszcz", "40-033", "Bydgoska 11", "E-6", "Parking przy sklepiku Avea", true);
            final Parking parking5 = new Parking(null, "Ruda", "40-111", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
            final Parking parking6 = new Parking(null, "Warszawa", "40-222", "Weteranow 3", "B-4", "Parking przy dworcu", true);
            final Parking parking7 = new Parking(null, "Piotrowice", "40-333", "Bydgoska 77", "E-6", "Parking przy sklepiku Avea", true);
            final Parking parking8 = new Parking(null, "Kraków", "40-444", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
            final Parking parking9 = new Parking(null, "Rzeszów", "40-555", "Weteranow 2", "B-4", "Parking przy dworcu", true);

            final Long parkingId1 = parkingService.addEntityToDB(parking1);
            final Long parkingId2 = parkingService.addEntityToDB(parking2);
            final Long parkingId3 = parkingService.addEntityToDB(parking3);
            final Long parkingId4 = parkingService.addEntityToDB(parking4);
            final Long parkingId5 = parkingService.addEntityToDB(parking5);
            final Long parkingId6 = parkingService.addEntityToDB(parking6);
            final Long parkingId7 = parkingService.addEntityToDB(parking7);
            final Long parkingId8 = parkingService.addEntityToDB(parking8);
            final Long parkingId9 = parkingService.addEntityToDB(parking9);


            final Car car1 = new Car(null, "WN101", 100, 4, 5, 5,
                    gearboxTypeService.getEntityByName("Automatic"), fuelTypeService.getEntityByName("Gasoline"),
                    LocalDateTime.of(2000, 3, 25, 0, 0), 1990, true, 200000, modelService.getEntityById(modelId1),
                    parkingService.getEntityById(parkingId1), colourService.getEntityByName("Red"), typeService.getEntityByName("Sedan"));

            final Car car2 = new Car(null, "SBE33212", 120, 1, 4, 3,
                    gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                    LocalDateTime.of(2000, 3, 25, 0, 0), 2000, true, 120000, modelService.getEntityById(modelId2),
                    parkingService.getEntityById(parkingId2), colourService.getEntityByName("Blue"), typeService.getEntityByName("Coupe"));

            final Car car3 = new Car(null, "SBE11212", 250, 4, 5, 5,
                    gearboxTypeService.getEntityByName("Automatic"), fuelTypeService.getEntityByName("Diesel"),
                    LocalDateTime.of(2000, 3, 25, 0, 0), 2014, true, 100000, modelService.getEntityById(modelId3),
                    parkingService.getEntityById(parkingId3), colourService.getEntityByName("Green"), typeService.getEntityByName("Sedan"));

            carService.addEntityToDB(car1);
            carService.addEntityToDB(car2);
            carService.addEntityToDB(car3);

            final Rent rent1 = new Rent(null
                    , userService.getEntityByLogin("user123")
                    , carService.getEntityByLicensePlate("WN101")
                    , LocalDateTime.of(2020, 9, 25, 0, 0)
                    , LocalDateTime.of(2020, 10, 1, 0, 0)
                    , parkingService.getEntityById(parkingId1)
                    , parkingService.getEntityById(parkingId2)
                    , false, "Simple comment", "Response");

            final Rent rent2 = new Rent(null
                    , userService.getEntityByLogin("walo123")
                    , carService.getEntityByLicensePlate("SBE33212")
                    , LocalDateTime.of(2020, 11, 25, 0, 0)
                    , LocalDateTime.of(2020, 12, 1, 0, 0)
                    , parkingService.getEntityById(parkingId3)
                    , parkingService.getEntityById(parkingId4)
                    , true, "walo comment", "Response");

            final Rent rent3 = new Rent(null
                    , userService.getEntityByLogin("kama123")
                    , carService.getEntityByLicensePlate("SBE11212")
                    , LocalDateTime.of(2020, 11, 25, 0, 0)
                    , LocalDateTime.of(2020, 12, 1, 0, 0)
                    , parkingService.getEntityById(parkingId5)
                    , parkingService.getEntityById(parkingId6)
                    , true, "kama comment", "Response");

            final Rent rent4 = new Rent(null
                    , userService.getEntityByLogin("kama123")
                    , carService.getEntityByLicensePlate("WN101")
                    , LocalDateTime.of(2020, 8, 25, 0, 0)
                    , LocalDateTime.of(2020, 9, 1, 0, 0)
                    , parkingService.getEntityById(parkingId7)
                    , parkingService.getEntityById(parkingId8)
                    , true, "kama comment", "Response");

            rentRepository.save(rent1);
            rentRepository.save(rent2);
            rentRepository.save(rent3);
            rentRepository.save(rent4);
        }
    }
}
