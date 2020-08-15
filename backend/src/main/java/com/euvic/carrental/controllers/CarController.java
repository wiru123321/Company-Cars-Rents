package com.euvic.carrental.controllers;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Model;
import com.euvic.carrental.model.Parking;
import com.euvic.carrental.responses.CarDTO;
import com.euvic.carrental.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin
public class CarController {
    private final CarService carService;
    private final ParkingService parkingService;
    private final ModelService modelService;
    private final FileService fileService;
    private final FaultService faultService;
    private final RentHistoryService rentHistoryService;
    private final RentService rentService;

    @Autowired
    public CarController(final CarService carService, final ParkingService parkingService, final ModelService modelService, final FileService fileService, final FaultService faultService, final RentHistoryService rentHistoryService, final RentService rentService) {
        this.carService = carService;
        this.parkingService = parkingService;
        this.modelService = modelService;
        this.fileService = fileService;
        this.faultService = faultService;
        this.rentHistoryService = rentHistoryService;
        this.rentService = rentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/hello")
    public String sayHelloAdmin() {
        return "Hello admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/cars")
    public ResponseEntity<?> getAllInCompanyCars() {
        return ResponseEntity.ok(carService.getInCompanyCarDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ae/active-cars")
    public ResponseEntity<?> getAllActiveCars() {
        return ResponseEntity.ok(carService.getInCompanyActiveCarDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/inactive-cars")
    public ResponseEntity<?> getAllInActiveCars() {
        return ResponseEntity.ok(carService.getInCompanyInactiveCarDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/car/{licensePlate}")
    public ResponseEntity<?> getCarByLicensePlate(@PathVariable final String licensePlate) {
        return ResponseEntity.ok(carService.getDTOByLicensePlate(licensePlate));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/a/car")
    public ResponseEntity<?> addCarToDatabase(@RequestBody final CarDTO carDTO) {
        if (carService.checkIfOnCompanyCarWithLicensePlateExists(carDTO.getLicensePlate())) {
            return new ResponseEntity<>("Car with given license plate already exist.", HttpStatus.CONFLICT);
        }

        final Parking parking = parkingService.mapRestModel(null, carDTO.getParkingDTO());
        final Long parkingId = parkingService.addEntityToDB(parking);
        final Model model = modelService.mapRestModel(null, carDTO.getModelDTO());
        final Long modelId = modelService.addEntityToDB(model);

        final Car car = carService.mapRestModel(null, carDTO, parkingId, modelId);
        return ResponseEntity.ok(carService.addEntityToDB(car));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/a/car/{licensePlate}")
    public ResponseEntity<?> updateDataBaseCar(@PathVariable final String licensePlate, @RequestBody final CarDTO newCarDTO) {
        if (!carService.checkIfOnCompanyCarWithLicensePlateExists(newCarDTO.getLicensePlate())) {
            return new ResponseEntity<>("Car with given license plate doesn't exist.", HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(carService.updateCarInDB(licensePlate, newCarDTO));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/a/car/activity/{licensePlate}")
    public ResponseEntity<?> setCarActivityInDB(@RequestParam("isActive") final Boolean isActive, @PathVariable final String licensePlate) {
        if (!carService.checkIfOnCompanyCarWithLicensePlateExists(licensePlate)) {
            return new ResponseEntity<>("Car with given license plate doesn't exist.", HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(carService.setCarActivity(isActive, licensePlate));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/a/car/{licensePlate}")
    public ResponseEntity<?> setCarAndItRelationsAsDeletedInDB(@PathVariable final String licensePlate) {
        if (!carService.checkIfOnCompanyCarWithLicensePlateExists(licensePlate)) {
            return new ResponseEntity<>("Car with given license plate doesn't exist.", HttpStatus.CONFLICT);
        }
        if (rentService.getRentsByLicensePlate(licensePlate) == null) {
            final List<Long> deletedCarFaultIds = faultService.setAllFaultsAsInactiveForCertainCar(licensePlate);
            final Long deletedCarId = carService.setCarIsNotInCompany(licensePlate);
            rentHistoryService.setToInactiveByLicensePlate(licensePlate);

            return ResponseEntity.ok("Car with id: " + deletedCarId + " is deleted, same its:\n" + "faults with ids: " + deletedCarFaultIds + ".");
        } else {
            return new ResponseEntity<>("Car with active rents cannot be deleted", HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/a/car/upload-car-image/{licensePlate}", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> uploadCarImageForExistingCar(@RequestParam("imageFile") final MultipartFile file, @PathVariable final String licensePlate) {

        if (!carService.checkIfOnCompanyCarWithLicensePlateExists(licensePlate)) {
            return new ResponseEntity<>("Car with given license plate doesn't exist.", HttpStatus.CONFLICT);
        }

        final String addedImagePath;
        try {
            addedImagePath = fileService.uploadCarImage(file);
        } catch (final IOException e) {
            return new ResponseEntity<>("Image get not uploaded", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(carService.addExistingImageToExistingCar(addedImagePath, licensePlate));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/u/car/download-car-image/{licensePlate}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> downloadCarImageForExistingCar(@PathVariable final String licensePlate) {
        final byte[] image;
        try {
            image = fileService.downloadCarImage(licensePlate);
        } catch (final NullPointerException e) {
            return new ResponseEntity<>("Image not found.", HttpStatus.CONFLICT);
        } catch (final IOException e) {
            return new ResponseEntity<>("Image get not downloaded", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
