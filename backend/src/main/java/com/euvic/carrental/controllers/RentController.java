package com.euvic.carrental.controllers;

import com.euvic.carrental.model.*;
import com.euvic.carrental.responses.*;
import com.euvic.carrental.services.*;
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
    private final ParkingHistoryService parkingHistoryService;
    private final RentHistoryService rentHistoryService;

    @Autowired
    public RentController(final UserService userService, final RentService rentService, final CarService carService, final ParkingService parkingService, final ParkingHistoryService parkingHistoryService, final RentHistoryService rentHistoryService) {
        this.userService = userService;
        this.rentService = rentService;
        this.carService = carService;
        this.parkingService = parkingService;
        this.parkingHistoryService = parkingHistoryService;
        this.rentHistoryService = rentHistoryService;
    }

    //ADMIN
    @RequestMapping(method = RequestMethod.GET, value = "/a/rent/active_rents")
    public ResponseEntity<?> getActiveRents() {
        return ResponseEntity.ok(rentService.getAllActiveRents());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/rent/pending")
    public ResponseEntity<?> getPendingRents() {
        return ResponseEntity.ok(rentService.getAllPendingRents());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/rent/{id}")
    public ResponseEntity<?> getRentWithId(@PathVariable final Long id) {
        return ResponseEntity.ok(rentService.getDTOById(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/rent/car_history/{licensePlate}")
    public ResponseEntity<?> checkCarHistory(@PathVariable final String licensePlate) {
        return ResponseEntity.ok(rentHistoryService.getAllDTOsByCar(carService.getOnCompanyEntityByLicensePlate(licensePlate)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/a/rent/change_car_in_rent/{id}")
    public ResponseEntity<?> editRent(@PathVariable final Long id, @RequestParam final String licensePlate) {

        int responseCode;
        String message;
        try {
            final Rent rent = rentService.getEntityById(id);
            final Car car = carService.getOnCompanyEntityByLicensePlate(licensePlate);
            if (car == null)
                throw new NullPointerException();
            rent.setCar(car);
            if (rentService.checkCarAvailability(rent)) {
                throw new NoSuchElementException();
            }
            rentService.addEntityToDB(rent);
            responseCode = 200;
            message = "Ok";
        } catch (final NullPointerException e) {
            responseCode = 400;
            message = "Rent or car doesn't exist";
        } catch (final NoSuchElementException e) {
            responseCode = 406;
            message = "Car is not available";
        }

        return ResponseEntity.status(responseCode).body(message);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/a/rent/permit/{id}")
    public ResponseEntity<?> permitRent(@PathVariable final Long id, @RequestBody final RentPermitRejectDTO rentPermitRejectDTO) {
        final Rent rent = rentService.getEntityById(id);
        int responseCode;
        String message;
        final Car car = carService.getOnCompanyEntityByLicensePlate(rentPermitRejectDTO.getLicensePlate());

        if (car != null) {
            try {
                rent.setIsActive(true);
                rent.setCar(car);
                rent.setAdminResponseForTheRequest(rentPermitRejectDTO.getResponse());
                if (rentService.checkCarAvailability(rent)) {
                    rentService.addEntityToDB(rent);
                    responseCode = 200;
                    message = "Updated";
                } else {
                    responseCode = 400;
                    message = "Car is rented";
                }
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

    @RequestMapping(method = RequestMethod.DELETE, value = "/a/rent/reject/{id}")
    public ResponseEntity<?> rejectRent(@PathVariable final Long id, @RequestBody final RentPermitRejectDTO rentPermitRejectDTO) {
        final Rent rent = rentService.getEntityById(id);
        int responseCode;
        String message;
        try {
            if (!rent.getIsActive()) {
                final ParkingHistory parkingFrom = new ParkingHistory(null, rent.getParkingFrom());
                final ParkingHistory parkingTo = new ParkingHistory(null, rent.getParkingTo());
                final RentHistory rentHistory = new RentHistory(null, rent.getUser(), rent.getCar(), rent.getDateFrom(), rent.getDateTo(), parkingFrom
                        , parkingTo, true, false, rent.getReasonForTheLoan(), rentPermitRejectDTO.getResponse(), "");
                parkingHistoryService.addEntityToDB(parkingFrom);
                parkingHistoryService.addEntityToDB(parkingTo);
                rentHistoryService.addEntityToDB(rentHistory);
                final Long parkingFromId = rent.getParkingFrom().getId();
                final Long parkingToId = rent.getParkingTo().getId();
                rentService.deleteRent(rent);
                parkingService.deleteParkingById(parkingFromId);
                parkingService.deleteParkingById(parkingToId);
                responseCode = 200;
                message = "ok";
            } else {
                responseCode = 400;
                message = "Rent request doesn't exist";
            }
        } catch (final NullPointerException e) {
            message = "Invalid rent ID";
            responseCode = 400;
        }
        return ResponseEntity.status(responseCode).body(message);
    }

    //EMPLOYEE
    @RequestMapping(method = RequestMethod.GET, value = "/e/rent/my_history")
    public ResponseEntity<?> getMyHistory() {
        return ResponseEntity.ok(rentHistoryService.getUserRentHistoryDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/e/rent/my_rents")
    public ResponseEntity<?> getMyRents() {
        return ResponseEntity.ok(rentService.getUserActiveRentDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/e/rent/my_requests")
    public ResponseEntity<?> getMyRequests() {
        return ResponseEntity.ok(rentService.getUserInactiveRentDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/e/rent/carsOnTime")
    public ResponseEntity<?> getCarsOnTime(@RequestParam(value = "dateFrom") final String dateFrom, @RequestParam(value = "dateTo") final String dateTo) {
        return ResponseEntity.ok(rentService.getActiveCarsBetweenDates(new DateFromDateTo(dateFrom, dateTo)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/e/rent/{licensePlate}")
    public ResponseEntity<?> addRent(@PathVariable final String licensePlate, @RequestBody final RentDTO rentDTO) {
        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        final Car car = carService.getOnCompanyEntityByLicensePlate(licensePlate);

        int responseCode;
        final Long id;
        String message;
        try {
            if (rentService.checkMyRentsBeforeAddNewRent(rentDTO)) {
                if (rentDTO.getParkingDTOTo() != null) {
                    id = parkingService.addEntityToDB(parkingService.mapRestModel(null, rentDTO.getParkingDTOTo()));
                } else {
                    id = parkingService.addEntityToDB(parkingService.mapRestModel(null, new ParkingDTO(car.getParking())));
                }
                final Rent rent = new Rent(null, user, car, rentDTO.getDateFrom(), rentDTO.getDateTo(), car.getParking(), parkingService.getEntityById(id), false, rentDTO.getReasonForTheLoan(), "", "");
                if (!rentService.checkCarAvailability(rent)) {
                    throw new NoSuchElementException();
                }
                rentService.addEntityToDB(rent);
                responseCode = 200;
                message = "Ok";
            } else {
                responseCode = 400;
                message = "You have rent in this time or give invalid time range";
            }
        } catch (final NullPointerException e) {
            responseCode = 406;
            message = "Cannot find user or car in database";
        } catch (final NoSuchElementException e) {
            responseCode = 409;
            message = "Car is already rented for this date";
        }

        return ResponseEntity.status(responseCode).body(message);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/e/rent/revoke_request/{id}")
    public ResponseEntity<?> revokeRentRequest(@PathVariable final Long id) {
        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        final Rent rent = rentService.getEntityById(id);
        int responseCode;
        String message;

        try {
            if (rent.getUser().equals(user)) {
                final Long parkingFromId = rent.getParkingFrom().getId();
                final Long parkingToId = rent.getParkingTo().getId();
                rentService.deleteRent(rent);
                parkingService.deleteParkingById(parkingFromId);
                parkingService.deleteParkingById(parkingToId);
                responseCode = 200;
                message = "Ok";
            } else {
                responseCode = 400;
                message = "Rent is not belong to this user";
            }

        } catch (final NullPointerException e) {
            responseCode = 400;
            message = "Rent doesn't exist";
        }
        return ResponseEntity.status(responseCode).body(message);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/e/rent/end_rent/{id}")
    public ResponseEntity<?> endRent(@PathVariable final Long id, @RequestBody final EndRentDTO endRentDTO) {
        final Rent rent = rentService.getEntityById(id);
        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        int responseCode;
        String message;

        if (rent.getIsActive()) {
            try {
                if (rent.getUser().equals(user)) {
                    final ParkingHistory parkingFrom = new ParkingHistory(null, rent.getParkingFrom());
                    final ParkingHistory parkingTo;

                    if (endRentDTO.getParkingDTO() == null) {
                        parkingTo = new ParkingHistory(null, rent.getParkingTo());
                        rent.getCar().setParking(rent.getParkingTo());
                    } else {
                        parkingTo = new ParkingHistory(null, endRentDTO.getParkingDTO());
                        final Parking carParking = new Parking(null, endRentDTO.getParkingDTO());
                        parkingService.addEntityToDB(carParking);
                        rent.getCar().setParking(carParking);
                    }

                    final RentHistory rentHistory = new RentHistory(null, rent.getUser(), rent.getCar(), rent.getDateFrom(), rent.getDateTo(), parkingFrom
                            , parkingTo, true, true, rent.getReasonForTheLoan(), rent.getAdminResponseForTheRequest(), endRentDTO.getFaultMessage());
                    parkingHistoryService.addEntityToDB(parkingFrom);
                    parkingHistoryService.addEntityToDB(parkingTo);
                    rentHistoryService.addEntityToDB(rentHistory);
                    final Long parkingFromId = rent.getParkingFrom().getId();
                    final Long parkingToId = rent.getParkingTo().getId();
                    rentService.updateNextRent(rent);
                    rentService.deleteRent(rent);
                    parkingService.deleteParkingById(parkingFromId);
                    if (endRentDTO.getParkingDTO() != null)
                        parkingService.deleteParkingById(parkingToId);

                    responseCode = 200;
                    message = "ok";
                } else {
                    responseCode = 403;
                    message = "Rent is not belong to this user";
                }

            } catch (final NullPointerException e) {
                responseCode = 401;
                message = "Rent doesn't exist";
            }
        } else {
            responseCode = 400;
            message = "Rent doesn't exist";
        }

        return ResponseEntity.status(responseCode).body(message);
    }
}
