package com.euvic.carrental.controllers;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Model;
import com.euvic.carrental.model.Parking;
import com.euvic.carrental.responses.*;
import com.euvic.carrental.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Wyswietla tylko samochody w firmie i takich co ich nie ma

@RestController
@CrossOrigin
public class CarController {
    private final CarService carService;
    private final ParkingService parkingService;
    private final ModelService modelService;

    public CarController(CarService carService, ParkingService parkingService, ModelService modelService) {
        this.carService = carService;
        this.parkingService = parkingService;
        this.modelService = modelService;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/a/hello")
    public String sayHelloAdmin(){
        return "Hello admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/cars")
    public ResponseEntity getAllInCompanyCars(){
        return ResponseEntity.ok(carService.getInCompanyCarDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ae/active-cars")
    public ResponseEntity getAllActiveCars(){
        return ResponseEntity.ok(carService.getInCompanyActiveCarDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a/inactive-cars")
    public ResponseEntity getAllInActiveCars(){
        return ResponseEntity.ok(carService.getInCompanyInActiveCarDTOs());
    }

    @RequestMapping(method = RequestMethod.POST,value = "/a/car")
    public ResponseEntity addCarToDatabase(@RequestBody CarDTO carDTO){
        Parking parking = parkingService.mapRestModel(null, carDTO.getParkingDTO());
        Long parkingId = parkingService.addEntityToDB(parking);
        Model model = modelService.mapRestModel(null, carDTO.getModelDTO());
        Long modelId = modelService.addEntityToDB(model);

        Car car = carService.mapRestModel(null, carDTO, parkingId, modelId);
        return ResponseEntity.ok(carService.addEntityToDB(car));
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/a/car/{licensePlate}")
    public ResponseEntity updateDataBaseCar(@PathVariable String licensePlate, @RequestBody CarDTO newCarDTO){
        return ResponseEntity.ok(carService.updateCarInDB(licensePlate, newCarDTO));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/a/car/{licensePlate}")
    public ResponseEntity setCarAsDeletedInDB(@PathVariable String licensePlate){
        return ResponseEntity.ok(carService.setCarIsNotInCompany(licensePlate));
    }
}