package com.euvic.carrental;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


//@Component
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


    @Autowired
    public DatabaseLoader(CarRepository carRepository, ColourRepository colourRepository,
                          FaultRepository faultRepository, MarkRepository markRepository,
                          ModelRepository modelRepository, ParkingRepository parkingRepository,
                          RentHistoryRepository rentHistoryRepository, RentRepository rentRepository,
                          RoleRepository roleRepository, TypeRepository typeRepository,
                          UserRepository userRepository, GearboxTypeRepository gearboxTypeRepository,
                          FuelTypeRepository fuelTypeRepository) {
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
    public void run(String... strings) throws Exception {
/*
        Path path = Paths.get("./DataBase/Types.txt");
        List<String> stringList = Files.readAllLines(path);
        for (int i = 0; i < stringList.size(); i++) {
            typeRepository.save(new Type(stringList.get(i)));
        }
        stringList.clear();

        path = Paths.get("./DataBase/Roles.txt");
        stringList = Files.readAllLines(path);
        for (int i = 0; i < stringList.size(); i++) {
            roleRepository.save(new Role(stringList.get(i)));
        }
        stringList.clear();

        /*
        path = Paths.get("./DataBase/Marks.txt");
        stringList = Files.readAllLines(path);
        for (int i = 0; i < stringList.size(); i++) {
            markRepository.save(new Mark(stringList.get(i)));
        }
        stringList.clear();*/ //Dont work because of changed constructor (Repair or delete)
/*
        path = Paths.get("./DataBase/GearBoxTypes.txt");
        stringList = Files.readAllLines(path);
        for (int i = 0; i < stringList.size(); i++) {
            gearboxTypeRepository.save(new GearboxType(stringList.get(i)));
        }
        stringList.clear();

        path = Paths.get("./DataBase/FuelTypes.txt");
        stringList = Files.readAllLines(path);
        for (int i = 0; i < stringList.size(); i++) {
            fuelTypeRepository.save(new FuelType(stringList.get(i)));
        }
        stringList.clear();

        path = Paths.get("./DataBase/Colours.txt");
        stringList = Files.readAllLines(path);
        for (int i = 0; i < stringList.size(); i++) {
            colourRepository.save(new Colour(stringList.get(i)));
        }
        stringList.clear();*/
    }
}
