package com.euvic.carrental.controllers;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Model;
import com.euvic.carrental.model.Parking;
import com.euvic.carrental.responses.CarDTO;
import com.euvic.carrental.services.CarService;
import com.euvic.carrental.services.FileService;
import com.euvic.carrental.services.ModelService;
import com.euvic.carrental.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Executable;


@RestController
@CrossOrigin
public class CarController {
    private final CarService carService;
    private final ParkingService parkingService;
    private final ModelService modelService;
    private final FileService fileService;

    public CarController(final CarService carService, final ParkingService parkingService, final ModelService modelService, final  FileService fileService) {
        this.carService = carService;
        this.parkingService = parkingService;
        this.modelService = modelService;
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/hello")
    public String sayHelloAdmin() {
        return "Hello admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/cars")
    public ResponseEntity getAllInCompanyCars() {
        return ResponseEntity.ok(carService.getInCompanyCarDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ae/active-cars")
    public ResponseEntity getAllActiveCars() {
        return ResponseEntity.ok(carService.getInCompanyActiveCarDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/inactive-cars")
    public ResponseEntity getAllInActiveCars() {
        return ResponseEntity.ok(carService.getInCompanyInactiveCarDTOs());
    }

    //TODO Add validation by licenceplate
    //TODO filtrowanie samochodów po marce i po rejestracji po znakach od lewej do prawej
    //użytkownik po im. i nazwisku
    @RequestMapping(method = RequestMethod.POST, value = "/a/car")
    public ResponseEntity addCarToDatabase(@RequestBody final CarDTO carDTO) {
        final Parking parking = parkingService.mapRestModel(null, carDTO.getParkingDTO());
        final Long parkingId = parkingService.addEntityToDB(parking);
        final Model model = modelService.mapRestModel(null, carDTO.getModelDTO());
        final Long modelId = modelService.addEntityToDB(model);

        final Car car = carService.mapRestModel(null, carDTO, parkingId, modelId);
        return ResponseEntity.ok(carService.addEntityToDB(car));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/a/car/{licensePlate}")
    public ResponseEntity updateDataBaseCar(@PathVariable final String licensePlate, @RequestBody final CarDTO newCarDTO) {
        return ResponseEntity.ok(carService.updateCarInDB(licensePlate, newCarDTO));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/a/car/{licensePlate}")
    public ResponseEntity setCarAsDeletedInDB(@PathVariable final String licensePlate) {
        return ResponseEntity.ok(carService.setCarIsNotInCompany(licensePlate));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/a/car/upload-car-image/{licensePlate}", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> uploadCarImageForExistingCar(@RequestParam("imageFile") final MultipartFile file, @PathVariable final String licensePlate){

        if(!carService.checkIfCarWithLicensePlateExists(licensePlate)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Car with given license plate doesn't exist.");
        }

        ResponseEntity carImageUploadResponse = fileService.uploadCarImage(file);
        if(carImageUploadResponse.getStatusCode() == HttpStatus.BAD_REQUEST){
            return carImageUploadResponse;
        }

        String carImageName = carImageUploadResponse.getBody().toString();
        return ResponseEntity.ok(carService.addExistingImageToExistingCar(carImageName, licensePlate));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/car/download-car-image/{licensePlate}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> downloadCarImageForExistingCar(@PathVariable final String licensePlate) throws IOException{
        return fileService.downloadCarImage(licensePlate);
    }
}
