package com.euvic.carrental.services;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.CarRepository;
import com.euvic.carrental.responses.*;
import com.euvic.carrental.services.interfaces.CarServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService implements CarServiceInterface {

    private final CarRepository carRepository;
    private final GearboxTypeService gearboxTypeService;
    private final FuelTypeService fuelTypeService;
    private final ModelService modelService;
    private final ParkingService parkingService;
    private final ColourService colourService;
    private final TypeService typeService;

    @Autowired
    public CarService(final CarRepository carRepository, final GearboxTypeService gearboxTypeService, final FuelTypeService fuelTypeService,
                      final ModelService modelService, final ParkingService parkingService, final ColourService colourService, final TypeService typeService) {
        this.carRepository = carRepository;
        this.gearboxTypeService = gearboxTypeService;
        this.fuelTypeService = fuelTypeService;
        this.modelService = modelService;
        this.parkingService = parkingService;
        this.colourService = colourService;
        this.typeService = typeService;
    }

    @Override
    public Car mapRestModel(final CarDTO carDTO) {
        return new Car(null, carDTO, gearboxTypeService.getEntityByName(carDTO.getGearBoxTypeDTO().getName()),
                fuelTypeService.getEntityByName(carDTO.getFuelTypeDTO().getName()),
                modelService.getEntityByName(carDTO.getModelDTO().getName()),
                parkingService.getEntityByTown(carDTO.getParkingDTO().getTown()).get(0),
                colourService.getEntityByName(carDTO.getColourDTO().getName()),
                typeService.getEntityByName(carDTO.getTypeDTO().getName()));
    }

    @Override
    public Car getEntityByLicensePlate(final String licensePlate) {
        return carRepository.findByLicensePlate(licensePlate);
    }

    @Override
    public CarDTO getDTOByLicensePlate(final String licensePlate) {
        final Car car = carRepository.findByLicensePlate(licensePlate);
        final GearboxType gearboxType = car.getGearboxType();
        final FuelType fuelType = car.getFuelType();
        final Model model = car.getModel();
        final Parking parking = car.getParking();
        final Colour colour = car.getColour();
        final Type type = car.getType();
        return new CarDTO(car, gearboxTypeService.getDTOByName(gearboxType.getName()), fuelTypeService.getDTOByName(fuelType.getName())
                , modelService.getDTOByName(model.getName()), parkingService.getDTOByTown(parking.getTown()).get(0)
                , colourService.getDTOByName(colour.getName()), typeService.getDTOByName(type.getName()));
    }

    @Override
    public List<CarDTO> getAll() {
        final ArrayList<Car> carList = new ArrayList<>();
        carRepository.findAll().forEach(carList::add);

        final ArrayList<CarDTO> carDTOList = new ArrayList<>();
        carList.stream().forEach((car) -> {
            final CarDTO carDTO = new CarDTO(car, new GearBoxTypeDTO(car.getGearboxType()), new FuelTypeDTO(car.getFuelType()), new ModelDTO(car.getModel()),
                    new ParkingDTO(car.getParking()), new ColourDTO(car.getColour()), new TypeDTO(car.getType()));
            carDTOList.add(carDTO);
        });

        return carDTOList;
    }

    @Override
    public void add(final CarDTO car) {
        carRepository.save(this.mapRestModel(car));
    }
}
