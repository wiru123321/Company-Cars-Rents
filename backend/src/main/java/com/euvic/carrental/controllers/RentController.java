package com.euvic.carrental.controllers;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Rent;
import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.RentDTO;
import com.euvic.carrental.responses.RentListCarByTime;
import com.euvic.carrental.services.CarService;
import com.euvic.carrental.services.ParkingService;
import com.euvic.carrental.services.RentService;
import com.euvic.carrental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@CrossOrigin
public class RentController {
    private final UserService userService;
    private final RentService rentService;
    private final CarService carService;
    private final ParkingService parkingService;

    @Autowired
    public RentController(final UserService userService, final RentService rentService, final CarService carService, final ParkingService parkingService) {
        this.userService = userService;
        this.rentService = rentService;
        this.carService = carService;
        this.parkingService = parkingService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/rent/pending")
    public ResponseEntity<?> getPendingRents() {
        return ResponseEntity.ok(rentService.getAllPendingRents());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/e/rent/my")
    public ResponseEntity<?> getMyRents() {
        return ResponseEntity.ok(rentService.getUserRentDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/e/rent/carsOnTime")
    public ResponseEntity<?> getCarsOnTime(@RequestBody final RentListCarByTime rentListCarByTime) {
        return ResponseEntity.ok(rentService.getActiveCarsBetweenDates(rentListCarByTime));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/a/rent/permit/{id}")
    public ResponseEntity<?> permitRent(@PathVariable final Long id, @RequestBody final String licensePlate) {
        final Rent rent = rentService.getEntityById(id);
        int responseCode;
        String message;
        final Car car = carService.getEntityByLicensePlate(licensePlate);
        if (car != null) {
            try {
                rent.setIsActive(true);
                rentService.addEntityToDB(rent);
                responseCode = 200;
                message = "Updated";
            } catch (final NullPointerException | NoSuchElementException e) {
                responseCode = 400;
                message = "Invalid rent ID";
            }
        } else {
            responseCode = 400;
            message = "Car with this license plate doesn't exist";
        }
        return ResponseEntity.status(responseCode).body(message);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/e/rent/{licensePlate}")
    public ResponseEntity<?> addRent(@PathVariable final String licensePlate, @RequestBody final RentDTO rentDTO) {
        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        final Car car = carService.getEntityByLicensePlate(licensePlate);

        int responseCode;
        final Long id;
        String message;
        try {
            id = parkingService.addEntityToDB(parkingService.mapRestModel(null, rentDTO.getParkingDTOTo()));
            car.setParking(parkingService.getEntityById(id));
            final Rent rent = new Rent(null, user, car, rentDTO.getDateFrom(), rentDTO.getDateTo(), car.getParking(), parkingService.getEntityById(id), false, null);
            rentService.addEntityToDB(rent);
            responseCode = 200;
            message = "Ok";

        } catch (final NullPointerException e) {
            responseCode = 406;
            message = "Cannot find user or car in database";
        }

        return ResponseEntity.status(responseCode).body(message);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/a/rent/reject/{id}")
    public ResponseEntity<?> rejectRent(@PathVariable final Long id) {
        final Rent rent = rentService.getEntityById(id);
        int responseCode;
        String message;
        try {


            responseCode = 200;
            message = "ok";
        } catch (final NullPointerException e) {
            message = "Invalid rent ID";
            responseCode = 400;
        }
        return ResponseEntity.status(responseCode).body(message);
    }

}
