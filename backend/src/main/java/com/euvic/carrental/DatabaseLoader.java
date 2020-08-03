package com.euvic.carrental;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                          final FuelTypeRepository fuelTypeRepository, final ParkingHistoryRepository parkingHistoryRepository) {
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
            // login i password w bazie danych nie mogą się powtarzać
            userRepository.save(new User(null, "admin123", passwordEncoder.encode("apassword123"), "admin@email.com", "Jan", "Kowalski", "123456789", true, roleRepository.findByName("ADMIN")));
            userRepository.save(new User(null, "user123", passwordEncoder.encode("upassword123"), "user@email.com", "Andrzej", "Wywrot", "123456798", true, roleRepository.findByName("EMPLOYEE")));
        }
    }
}
