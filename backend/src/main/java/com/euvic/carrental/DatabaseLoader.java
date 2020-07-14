package com.euvic.carrental;

import com.euvic.carrental.model.Role;
import com.euvic.carrental.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DatabaseLoader implements CommandLineRunner {

    //TODO add reposittories and services
    private final CarRepository carRepository;
    private final ColourRepository colourRepository;
    private final FaultRepository faultRepository;
    private final MarkRepository markRepository;
    private final ModelRepository modelRepository;
    private final ParkingRepository parkingRepository;
    private final RoleRepository roleRepository;
    private final TypeRepository typeRepository;
    private final UserRepository userRepository;


    @Autowired
    public DatabaseLoader(CarRepository carRepository, ColourRepository colourRepository,
                          FaultRepository faultRepository, MarkRepository markRepository,
                          ModelRepository modelRepository, ParkingRepository parkingRepository,
                          RoleRepository roleRepository, TypeRepository typeRepository,
                          UserRepository userRepository) {
        this.carRepository = carRepository;
        this.colourRepository = colourRepository;
        this.faultRepository = faultRepository;
        this.markRepository = markRepository;
        this.modelRepository = modelRepository;
        this.parkingRepository = parkingRepository;
        this.roleRepository = roleRepository;
        this.typeRepository = typeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        Role r1 = new Role("Admin");
        r1.setId(1L);
        Role r2 = new Role("Manager");
        r2.setId(2L);
        Role r3 = new Role("Employee");
        r3.setId(3L);

        roleRepository.save(r1);
        roleRepository.save(r2);
        roleRepository.save(r3);


    }
}
