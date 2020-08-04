package com.euvic.carrental.controllers;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Model;
import com.euvic.carrental.model.Parking;
import com.euvic.carrental.responses.*;
import com.euvic.carrental.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/a")
public class CarController {
    @Autowired private CarService carService;
    @Autowired private ParkingService parkingService;
    @Autowired private ModelService modelService;

    @RequestMapping(method = RequestMethod.GET,value = "/hello")
    public String sayHelloAdmin(){
        return "Hello admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cars")
    public List<CarDTO> getAllCars(){
        return carService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/car")
    public Long addCarToDatabase(@RequestBody CarDTO carDTO){
        Parking parking = parkingService.mapRestModel(null, carDTO.getParkingDTO());
        Long parkingId = parkingService.addEntityToDB(parking);
        Model model = modelService.mapRestModel(null, carDTO.getModelDTO());
        Long modelId = modelService.addEntityToDB(model);

        Car car = carService.mapRestModel(null, carDTO, parkingId, modelId);
        return carService.addEntityToDB(car);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/car/{licensePlate}")
    public Long updateDataBaseCar(@RequestBody CarDTO newCarDTO, @PathVariable String licensePlate){
        return carService.updateCarInDB(licensePlate, newCarDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/car/{licensePlate}")
    public Long setCarAsDeletedInDB(@PathVariable String licensePlate){
        return carService.setCarIsNotInCompany(licensePlate);
    }
}
