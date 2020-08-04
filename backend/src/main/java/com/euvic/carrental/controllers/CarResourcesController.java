package com.euvic.carrental.controllers;

import com.euvic.carrental.responses.*;
import com.euvic.carrental.services.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ColourDTO> getAllColours(){
        return colourService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fuelTypes")
    public List<FuelTypeDTO> getAllFuelTypes(){
        return fuelTypeService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gearboxTypes")
    public List<GearBoxTypeDTO> getAllGearboxTypes(){
        return gearboxTypeService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/marks")
    public List<MarkDTO> getAllMarks(){
        return markService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/roles")
    public List<RoleDTO> getAllRoles(){
        return roleService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/types")
    public List<TypeDTO> getAllTypes(){
        return typeService.getAllDTOs();
    }
}
