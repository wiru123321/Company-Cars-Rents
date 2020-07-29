package com.euvic.carrental.services;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Parking;
import com.euvic.carrental.model.Rent;
import com.euvic.carrental.model.User;
import com.euvic.carrental.repositories.RentRepository;
import com.euvic.carrental.responses.CarDTO;
import com.euvic.carrental.responses.ParkingDTO;
import com.euvic.carrental.responses.RentDTO;
import com.euvic.carrental.services.interfaces.RentServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service //TODO uzupelnic metody
public class RentService implements RentServiceInterface {

    final private RentRepository rentRepository;
    final private UserService userService;
    final private CarService carService;
    final private ParkingService parkingService;


    public RentService(final RentRepository rentRepository, final UserService userService, final CarService carService, final ParkingService parkingService) {
        this.rentRepository = rentRepository;
        this.userService = userService;
        this.carService = carService;
        this.parkingService = parkingService;
    }

    @Override
    public Long addEntityToDB(final Rent rent) {
        return rentRepository.save(rent).getId();
    }

    @Override
    public Rent getEntityById(final Long id) {
        return rentRepository.findById(id).get();
    }

    @Override
    public Rent getEntityByCarAndDateFrom(final Car car, final Date dateFrom) {
        return null;
    }

    @Override
    public RentDTO getDTOById(final Long id) {
        final Rent rent = rentRepository.findById(id).get();
        final User user = rent.getUser();
        final Car car = rent.getCar();
        final Parking parkingFrom = rent.getParkingFrom();
        final Parking parkingTo = rent.getParkingTo();
        return new RentDTO(rent, userService.getDTOByLogin(user.getLogin()), carService.getDTOByLicensePlate(car.getLicensePlate())
                , parkingService.getDTOById(parkingFrom.getId()), parkingService.getDTOById(parkingTo.getId()));
    }

    @Override
    public RentDTO getDTOByCarDTOAndDateFrom(final CarDTO carDTO, final Date dateFrom) {
        final Car car = carService.getEntityByLicensePlate(carDTO.getLicensePlate());
        final Rent rent = rentRepository.findByCarAndDateFrom(car, dateFrom);

        return new RentDTO(rent, userService.getDTOByLogin(rent.getUser().getLogin()), carDTO
                , new ParkingDTO(rent.getParkingFrom()), new ParkingDTO(rent.getParkingTo()));
    }


    public Rent mapRestModel(final Long id, final RentDTO rentDTO, final Long parkingFromId, final Long parkingToId) {

        return new Rent(id, userService.getEntityByLogin(rentDTO.getUserDTO().getLogin()), carService.getEntityByLicensePlate(rentDTO.getCarDTO().getLicensePlate())
                , rentDTO.getDateFrom(), rentDTO.getDateTo(), parkingService.getEntityById(parkingFromId), parkingService.getEntityById(parkingToId), rentDTO.getIsActive());
    }

    @Override
    public List<RentDTO> getAllDTOs() {
        final ArrayList<Rent> rentArrayList = new ArrayList<>();
        rentRepository.findAll().forEach(rentArrayList::add);

        final ArrayList<RentDTO> rentDTOArrayList = new ArrayList<>();
        rentArrayList.stream().forEach(rent -> {
            final RentDTO rentDTO = new RentDTO(rent, userService.getDTOByLogin(rent.getUser().getLogin()), carService.getDTOByLicensePlate(rent.getCar().getLicensePlate())
                    , new ParkingDTO(rent.getParkingFrom()), new ParkingDTO((rent.getParkingTo())));
        });
        return rentDTOArrayList;
    }
}
