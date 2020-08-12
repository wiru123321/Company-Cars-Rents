package com.euvic.carrental.controllers;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Fault;
import com.euvic.carrental.responses.FaultDTO;
import com.euvic.carrental.services.CarService;
import com.euvic.carrental.services.FaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/a")
public class FaultController {

    @Autowired private FaultService faultService;
    @Autowired private CarService carService;

    @RequestMapping(method = RequestMethod.GET, value = "/active-faults")
    public ResponseEntity getAllActiveFaults() {
        return ResponseEntity.ok(faultService.getAllActiveFaultDTOs());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/fault")
    public ResponseEntity addFaultToDatabase(@RequestBody final FaultDTO faultDTO) {
        if(!carService.checkIfCarWithLicensePlateExists(faultDTO.getCarLicensePlate())){
            ResponseEntity.status(HttpStatus.CONFLICT).body("Car with this license plate doesn't exist.");
        }
        else if(!faultService.checkIfCarFaultWithDescriptionExists(carService.getEntityByLicensePlate(faultDTO.getCarLicensePlate()), faultDTO.getDescription())){
            ResponseEntity.status(HttpStatus.CONFLICT).body("Fault with this description already exist for this car.");
        }

        final Fault fault = faultService.mapRestModel(null, faultDTO);
        if(fault.getSetCarInactive()){
            Car car = fault.getCar();
            car.setIsActive(false);
            carService.addEntityToDB(car);
        }
        return ResponseEntity.ok(faultService.addEntityToDB(fault));
    }
/*
    @RequestMapping(method = RequestMethod.DELETE, value = "/fault")
    public ResponseEntity setCarAsDeletedInDB(@RequestBody FaultDTO faultDTO) {

        return ResponseEntity.ok(carService.setCarIsNotInCompany(licensePlate));
    }*/
}
