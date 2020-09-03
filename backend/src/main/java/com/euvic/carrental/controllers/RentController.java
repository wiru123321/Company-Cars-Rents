package com.euvic.carrental.controllers;

import com.euvic.carrental.model.*;
import com.euvic.carrental.responses.*;
import com.euvic.carrental.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class RentController {
    private final UserService userService;
    private final RentService rentService;
    private final CarService carService;
    private final ParkingService parkingService;
    private final RentHistoryService rentHistoryService;

    @Autowired
    public RentController(final UserService userService, final RentService rentService, final CarService carService, final ParkingService parkingService, final RentHistoryService rentHistoryService) {
        this.userService = userService;
        this.rentService = rentService;
        this.carService = carService;
        this.parkingService = parkingService;
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
        return ResponseEntity.ok(rentService.getRentPendingDTOById(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/rent/end/{id}")
    public ResponseEntity<?> getEndRent(@PathVariable final Long id) {
        return ResponseEntity.ok(rentHistoryService.getRentEndById(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/rent/end_pending")
    public ResponseEntity<?> getEndRentPending() {
        return ResponseEntity.ok(rentHistoryService.getAllEndRentPending());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/rent/car_history/{licensePlate}")
    public ResponseEntity<?> checkCarHistory(@PathVariable final String licensePlate) {
        return ResponseEntity.ok(rentHistoryService.getAllDTOsByCar(carService.getOnCompanyEntityByLicensePlate(licensePlate)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/a/rent/change_car_in_rent/{id}")
    public ResponseEntity<?> editRent(@PathVariable final Long id, @RequestParam final String licensePlate) {

        final Rent rent = rentService.getEntityById(id);
        final Car car = carService.getOnCompanyEntityByLicensePlate(licensePlate);
        if (car == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rent or car doesn't exist");
        }
        rent.setCar(car);
        if (!rentService.checkIfRentIsAllowedToBeRequested(rent)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Car is not available");
        }
        rentService.addEntityToDB(rent);
        return ResponseEntity.status(HttpStatus.OK).body("Passed");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/a/rent/permit_end_rent/{id}")
    public ResponseEntity<?> permitEndRent(@PathVariable final Long id) {
        final RentHistory rentHistory = rentHistoryService.getEntityById(id);
        if (rentHistory == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RentHistory with this ID doesn't exist");
        }
        rentHistory.setIsActive(true);
        rentHistoryService.addEntityToDB(rentHistory);
        return ResponseEntity.status(HttpStatus.OK).body("Passed");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/a/rent/permit/{id}")
    public ResponseEntity<?> permitRent(@PathVariable final Long id, @RequestBody final RentPermitRejectDTO rentPermitRejectDTO) {
        final Rent rent = rentService.getEntityById(id);
        final Car car = carService.getOnCompanyEntityByLicensePlate(rentPermitRejectDTO.getLicensePlate());

        if (car == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car with this license plate doesn't exist");
        }
        if (rent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid rent ID");
        }
        rent.setIsActive(true);
        rent.setCar(car);
        rent.setAdminResponseForTheRequest(rentPermitRejectDTO.getResponse());
        if (!rentService.checkIfRentIsAllowedToBeRequested(rent)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car is rented");
        }
        rentService.addEntityToDB(rent);
        return ResponseEntity.status(HttpStatus.OK).body("Passed");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/a/rent/reject/{id}")
    public ResponseEntity<?> rejectRent(@PathVariable final Long id, @RequestBody final RentPermitRejectDTO rentPermitRejectDTO) {
        final Rent rent = rentService.getEntityById(id);

        if (rent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid rent ID");
        }
        if (rent.getIsActive()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rent request doesn't exist");
        }
        rentHistoryService.addNewRentHistoryWhenRentEnd(rentPermitRejectDTO.getResponse(), rent, null);
        rentService.deleteAndUpdateRentAndParkings(rent, false);

        return ResponseEntity.status(HttpStatus.OK).body("Passed");
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

        final List<CarDTO> carDTOList = rentService.getActiveCarsBetweenDates(new DateFromDateTo(dateFrom, dateTo));
        if (carDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no car");
        }
        return ResponseEntity.ok(carDTOList);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/e/rent/{licensePlate}")
    public ResponseEntity<?> addRent(@PathVariable final String licensePlate, @RequestBody final RentDTO rentDTO) {
        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        final Car car = carService.getOnCompanyEntityByLicensePlate(licensePlate);

        if (user == null || car == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Cannot find user or car in database");
        }
        if (!rentService.checkMyRentsBeforeAddNewRent(rentDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You have rent in this time or give invalid time range");
        }
        final Long id = parkingService.choosesNotNullParkingAndAddToDB(new ParkingDTO(car.getParking()), rentDTO.getParkingDTOTo());
        final Long parkingFromId = parkingService.addEntityToDB(parkingService.mapRestModel(null, new ParkingDTO(car.getParking())));
        final Rent rent = new Rent(null, user, car, rentDTO.getDateFrom(), rentDTO.getDateTo(), parkingService.getEntityById(parkingFromId), parkingService.getEntityById(id), false, rentDTO.getReasonForTheLoan(), "", "");
        if (!rentService.checkIfRentIsAllowedToBeRequested(rent)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Car is already rented for this date or date is before current date");
        }
        rentService.addEntityToDB(rent);
        return ResponseEntity.status(HttpStatus.OK).body("Passed");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/e/rent/revoke_request/{id}")
    public ResponseEntity<?> revokeRentRequest(@PathVariable final Long id) {
        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        final Rent rent = rentService.getEntityById(id);

        if (rent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rent doesn't exist");
        }

        if (!rent.getUser().equals(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rent is not belong to this user");
        }
        rentService.deleteAndUpdateRentAndParkings(rent, false);

        return ResponseEntity.status(HttpStatus.OK).body("Passed");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/e/rent/end_rent/{id}")
    public ResponseEntity<?> endRent(@PathVariable final Long id, @RequestBody final EndRentDTO endRentDTO) {
        final Rent rent = rentService.getEntityById(id);
        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if (rent == null || !rent.getIsActive()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Rent doesn't exist");
        }
        if (!rent.getUser().equals(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Rent is not belong to this user");
        }
        if (rent.getDateFrom().isAfter(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Your reservation cannot be end now. Wait for reservation time.");
        }

        final ParkingHistory parkingTo;
        final Parking actuallyCarParking = rent.getCar().getParking();
        if (endRentDTO.getParkingDTO() == null) {
            parkingTo = new ParkingHistory(null, rent.getParkingTo());
            final Parking carParking = new Parking(rent.getParkingTo());
            rent.getCar().setParking(carParking);
        } else {
            parkingTo = new ParkingHistory(null, endRentDTO.getParkingDTO());
            final Parking carParking = new Parking(null, endRentDTO.getParkingDTO());
            parkingService.addEntityToDB(carParking);
            rent.getCar().setParking(carParking);
        }
        parkingService.deleteParkingById(actuallyCarParking.getId());
        rentHistoryService.addNewRentHistoryWhenRentEnd(rent.getAdminResponseForTheRequest(), rent, parkingTo);
        carService.addEntityToDB(rent.getCar());
        rentService.deleteAndUpdateRentAndParkings(rent, true);

        return ResponseEntity.status(HttpStatus.OK).body("Passed");
    }
}
