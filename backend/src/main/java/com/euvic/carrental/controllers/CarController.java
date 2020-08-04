package com.euvic.carrental.controllers;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Model;
import com.euvic.carrental.model.Parking;
import com.euvic.carrental.responses.*;
import com.euvic.carrental.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET, value = "/ae/cars")
    public List<CarDTO> getAllCars(){
        return carService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/a/car")
    public Long addCarToDatabase(@RequestBody CarDTO carDTO){
        Parking parking = parkingService.mapRestModel(null, carDTO.getParkingDTO());
        Long parkingId = parkingService.addEntityToDB(parking);
        Model model = modelService.mapRestModel(null, carDTO.getModelDTO());
        Long modelId = modelService.addEntityToDB(model);

        Car car = carService.mapRestModel(null, carDTO, parkingId, modelId);
        return carService.addEntityToDB(car);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/a/car/{licensePlate}")
    public Long updateDataBaseCar(@PathVariable String licensePlate, @RequestBody CarDTO newCarDTO){
        return carService.updateCarInDB(licensePlate, newCarDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/a/car/{licensePlate}")
    public Long setCarAsDeletedInDB(@PathVariable String licensePlate){
        return carService.setCarIsNotInCompany(licensePlate);
    }
}
