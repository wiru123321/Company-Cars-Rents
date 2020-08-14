package com.euvic.carrental.controllers;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Fault;
import com.euvic.carrental.responses.FaultDTO;
import com.euvic.carrental.services.CarService;
import com.euvic.carrental.services.FaultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/a")
public class FaultController {

    private final FaultService faultService;
    private final CarService carService;

    public FaultController(FaultService faultService, CarService carService) {
        this.faultService = faultService;
        this.carService = carService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/active-faults")
    public ResponseEntity getAllActiveFaults() {
        return ResponseEntity.ok(faultService.getAllActiveFaultDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/active-faults/{licensePlate}")
    public ResponseEntity getAllActiveFaultsForCertainCar(@PathVariable String licensePlate) {
        if(!carService.checkIfOnCompanyCarWithLicensePlateExists(licensePlate)){
            ResponseEntity.status(HttpStatus.CONFLICT).body("Car with this license plate doesn't exist.");
        }
        return ResponseEntity.ok(faultService.getAllActiveFaultDTOsByCarLicensePlate(licensePlate));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/fault")
    public ResponseEntity addFaultToDatabase(@RequestBody final FaultDTO faultDTO) {
        if(!carService.checkIfOnCompanyCarWithLicensePlateExists(faultDTO.getCarLicensePlate())){
            ResponseEntity.status(HttpStatus.CONFLICT).body("Car with this license plate doesn't exist.");
        }
        else if(faultService.checkIfCarFaultWithDescriptionExists(carService.getOnCompanyEntityByLicensePlate(faultDTO.getCarLicensePlate()), faultDTO.getDescription())){
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

    @RequestMapping(method = RequestMethod.DELETE, value = "/fault")
    public ResponseEntity setFaultAsDeletedInDB(@RequestBody FaultDTO faultDTO) {
        if(!carService.checkIfOnCompanyCarWithLicensePlateExists(faultDTO.getCarLicensePlate())){
            ResponseEntity.status(HttpStatus.CONFLICT).body("Car with this license plate doesn't exist.");
        }
        else if(!faultService.checkIfCarFaultWithDescriptionExists(carService.getOnCompanyEntityByLicensePlate(faultDTO.getCarLicensePlate()), faultDTO.getDescription())){
            ResponseEntity.status(HttpStatus.CONFLICT).body("Fault with this description doesn't exist for this car.");
        }

        Car car = carService.getOnCompanyEntityByLicensePlate(faultDTO.getCarLicensePlate());
        return ResponseEntity.ok(faultService.setInactiveCarFaultWithDescription(car, faultDTO.getDescription()));
    }
}
