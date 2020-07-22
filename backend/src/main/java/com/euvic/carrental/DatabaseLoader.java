package com.euvic.carrental;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.*;
import com.euvic.carrental.services.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Component
public class DatabaseLoader implements CommandLineRunner {

    //TODO add reposittories and services
    private final CarRepository carRepository;
    private final ColourRepository colourRepository;
    private final FaultRepository faultRepository;
    private final MarkRepository markRepository;
    private final ModelRepository modelRepository;
    private final ParkingRepository parkingRepository;
    private final RentHistoryRepository rentHistoryRepository;
    private final RentRepository rentRepository;
    private final RoleRepository roleRepository;
    private final TypeRepository typeRepository;
    private final UserRepository userRepository;
    private final GearboxTypeRepository gearboxTypeRepository;
    private final FuelTypeRepository fuelTypeRepository;

    @Value("${spring.datasource.username}")
    private String dataBase;

    @Autowired
    public DatabaseLoader(final CarRepository carRepository, final ColourRepository colourRepository,
                          final FaultRepository faultRepository, final MarkRepository markRepository,
                          final ModelRepository modelRepository, final ParkingRepository parkingRepository,
                          final RentHistoryRepository rentHistoryRepository, final RentRepository rentRepository,
                          final RoleRepository roleRepository, final TypeRepository typeRepository,
                          final UserRepository userRepository, final GearboxTypeRepository gearboxTypeRepository,
                          final FuelTypeRepository fuelTypeRepository) {
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
    }

    @Override
    public void run(final String... strings) throws Exception {
        
        if (dataBase.equals("postgres")) {
            Path path = Paths.get("./DataBase/Types.txt");
            List<String> stringList = Files.readAllLines(path);
            for (int i = 0; i < stringList.size(); i++) {
                typeRepository.save(new Type(null, stringList.get(i)));
            }
            stringList.clear();

            path = Paths.get("./DataBase/Roles.txt");
            stringList = Files.readAllLines(path);
            for (int i = 0; i < stringList.size(); i++) {
                roleRepository.save(new Role(null, stringList.get(i)));
            }
            stringList.clear();


            path = Paths.get("./DataBase/Marks.txt");
            stringList = Files.readAllLines(path);
            for (int i = 0; i < stringList.size(); i++) {
                markRepository.save(new Mark(null, stringList.get(i)));
            }
            stringList.clear();

            path = Paths.get("./DataBase/GearBoxTypes.txt");
            stringList = Files.readAllLines(path);
            for (int i = 0; i < stringList.size(); i++) {
                gearboxTypeRepository.save(new GearboxType(null, stringList.get(i)));
            }
            stringList.clear();

            path = Paths.get("./DataBase/FuelTypes.txt");
            stringList = Files.readAllLines(path);
            for (int i = 0; i < stringList.size(); i++) {
                fuelTypeRepository.save(new FuelType(null, stringList.get(i)));
            }
            stringList.clear();

            path = Paths.get("./DataBase/Colours.txt");
            stringList = Files.readAllLines(path);
            for (int i = 0; i < stringList.size(); i++) {
                colourRepository.save(new Colour(null, stringList.get(i)));
            }
            stringList.clear();
        }
    }
}
