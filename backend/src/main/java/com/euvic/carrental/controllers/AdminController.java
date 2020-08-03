package com.euvic.carrental.controllers;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Model;
import com.euvic.carrental.model.Parking;
import com.euvic.carrental.responses.*;
import com.euvic.carrental.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/a")
public class AdminController {
    @Autowired
    private CarService carService;
    @Autowired
    private ColourService colourService;
    @Autowired
    private FuelTypeService fuelTypeService;
    @Autowired
    private GearboxTypeService gearboxTypeService;
    @Autowired
    private MarkService markService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TypeService typeService;


    @Autowired
    private ParkingService parkingService;
    @Autowired
    private ModelService modelService;

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String sayHelloAdmin() {
        return "Hello admin";
    }

    //TODO
    @RequestMapping(method = RequestMethod.POST, value = "/car")
    public Long addCarToDatabase(@RequestBody final CarDTO carDTO) {
        //operacje zmienające obiekty DTO na Encje
        //dodawanie niezbędnych informacji do bazy danych
        //złożenie obiektu Car ze zmnienionych DTO oraz dodanych informacji do bazy dancyh
        //dodanie obiektu car do bazy danych

        final Parking parking = parkingService.mapRestModel(null, carDTO.getParkingDTO());
        final Long parkingId = parkingService.addEntityToDB(parking);
        final Model model = modelService.mapRestModel(null, carDTO.getModelDTO());
        final Long modelId = modelService.addEntityToDB(model);

        final Car car = carService.mapRestModel(null, carDTO, parkingId); // TODO May be changed to pass parking instead of parkingId, can be added modelId(same as parking)
        return carService.addEntityToDB(car);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cars")
    public List<CarDTO> getAllCars() {
        return carService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/colours")
    public List<ColourDTO> getAllColours() {
        return colourService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fuelTypes")
    public List<FuelTypeDTO> getAllFuelTypes() {
        return fuelTypeService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gearboxTypes")
    public List<GearBoxTypeDTO> getAllGearboxTypes() {
        return gearboxTypeService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/marks")
    public List<MarkDTO> getAllMarks() {
        return markService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/roles")
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllDTOs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/types")
    public List<TypeDTO> getAllTypes() {
        return typeService.getAllDTOs();
    }
}
