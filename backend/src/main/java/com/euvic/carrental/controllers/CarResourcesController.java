package com.euvic.carrental.controllers;

import com.euvic.carrental.responses.*;
import com.euvic.carrental.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/a/car-components")
public class CarResourcesController {

    private final ColourService colourService;
    private final FuelTypeService fuelTypeService;
    private final GearboxTypeService gearboxTypeService;
    private final MarkService markService;
    private final RoleService roleService;
    private final TypeService typeService;

    public CarResourcesController(ColourService colourService, FuelTypeService fuelTypeService,
                                  GearboxTypeService gearboxTypeService, MarkService markService,
                                  RoleService roleService, TypeService typeService) {
        this.colourService = colourService;
        this.fuelTypeService = fuelTypeService;
        this.gearboxTypeService = gearboxTypeService;
        this.markService = markService;
        this.roleService = roleService;
        this.typeService = typeService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/colours")
    public ResponseEntity getAllColours(){
        return ResponseEntity.ok(colourService.getAllDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fuelTypes")
    public ResponseEntity getAllFuelTypes(){
        return ResponseEntity.ok(fuelTypeService.getAllDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gearboxTypes")
    public ResponseEntity getAllGearboxTypes(){
        return ResponseEntity.ok(gearboxTypeService.getAllDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/marks")
    public ResponseEntity getAllMarks(){
        return ResponseEntity.ok(markService.getAllDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/types")
    public ResponseEntity getAllTypes(){
        return ResponseEntity.ok(typeService.getAllDTOs());
    }
}
