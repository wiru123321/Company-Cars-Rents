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
    public Boolean checkIfOnCompanyCarWithLicensePlateExists(final String licensePlate) {
        return carRepository.existsByLicensePlateAndIsOnCompany(licensePlate, true);
    }

    @Override
    public Long addExistingImageToExistingCar(final String carImagePath, final String licensePlate) {
        final Car car = this.getOnCompanyEntityByLicensePlate(licensePlate);
        car.setImagePath(carImagePath);
        return this.addEntityToDB(car);
    }

    @Override
    public Long addEntityToDB(final Car car) {
        return carRepository.save(car).getId();
    }

    @Override
    public Long setCarActivity(final Boolean isActive, final String licensePlate) {
        final Car car = this.getOnCompanyEntityByLicensePlate(licensePlate);
        car.setIsActive(isActive);
        return this.addEntityToDB(car);
    }

    @Override
    public Long updateCarInDB(final String oldCarLicensePlate, final CarDTO newCarDTO) {
        final Car oldCar = this.getOnCompanyEntityByLicensePlate(oldCarLicensePlate);

        oldCar.setLicensePlate(newCarDTO.getLicensePlate());
        oldCar.setEnginePower(newCarDTO.getEnginePower());
        oldCar.setCapacityOfTrunkScale(newCarDTO.getCapacityOfTrunkScale());
        oldCar.setCapacityOfPeople(newCarDTO.getCapacityOfPeople());
        oldCar.setDoorsNumber(newCarDTO.getDoorsNumber());
        oldCar.setGearboxType(gearboxTypeService.getEntityByName(newCarDTO.getGearBoxTypeDTO().getName()));
        oldCar.setFuelType(fuelTypeService.getEntityByName(newCarDTO.getFuelTypeDTO().getName()));
        oldCar.setLastInspection(newCarDTO.getLastInspection());
        oldCar.setProductionYear(newCarDTO.getProductionYear());
        oldCar.setIsActive(newCarDTO.getIsActive());
        oldCar.setMileage(newCarDTO.getMileage());
        oldCar.setColour(colourService.getEntityByName(newCarDTO.getColourDTO().getName()));
        oldCar.setType(typeService.getEntityByName(newCarDTO.getTypeDTO().getName()));
        parkingService.updateParkingInDb(oldCar.getParking().getId(), newCarDTO.getParkingDTO());
        modelService.updateModelInDb(oldCar.getModel().getId(), newCarDTO.getModelDTO());

        return carRepository.save(oldCar).getId();
    }

    @Override
    public Long setCarIsNotInCompany(final String licensePlate) {
        final Car car = this.getOnCompanyEntityByLicensePlate(licensePlate);
        car.setIsOnCompany(false);
        car.setIsActive(false);
        return carRepository.save(car).getId();
    }

    @Override
    public Car mapRestModel(final Long id, final CarDTO carDTO, final Long parkingId, final Long modelId) {
        return new Car(id, carDTO, gearboxTypeService.getEntityByName(carDTO.getGearBoxTypeDTO().getName()),
                fuelTypeService.getEntityByName(carDTO.getFuelTypeDTO().getName()),
                modelService.getEntityById(modelId),
                parkingService.getEntityById(parkingId),
                colourService.getEntityByName(carDTO.getColourDTO().getName()),
                typeService.getEntityByName(carDTO.getTypeDTO().getName()));
    }

    @Override
    public Car getOnCompanyEntityByLicensePlate(final String licensePlate) {
        return carRepository.findByLicensePlateAndIsOnCompany(licensePlate, true);
    }

    @Override
    public CarDTO mapToCarDTO(final Car car) {
        return new CarDTO(car, new GearBoxTypeDTO(car.getGearboxType()), new FuelTypeDTO(car.getFuelType()), new ModelDTO(car.getModel()),
                new ParkingDTO(car.getParking()), new ColourDTO(car.getColour()), new TypeDTO(car.getType()));
    }

    @Override
    public CarDTO getDTOByLicensePlate(final String licensePlate) {
        final Car car = carRepository.findByLicensePlateAndIsOnCompany(licensePlate, true);
        final GearboxType gearboxType = car.getGearboxType();
        final FuelType fuelType = car.getFuelType();
        final Model model = car.getModel();
        final Parking parking = car.getParking();
        final Colour colour = car.getColour();
        final Type type = car.getType();
        return new CarDTO(car, gearboxTypeService.getDTOByName(gearboxType.getName()), fuelTypeService.getDTOByName(fuelType.getName())
                , modelService.getDTOByName(model.getName()), parkingService.getDTOById(parking.getId())
                , colourService.getDTOByName(colour.getName()), typeService.getDTOByName(type.getName()));
    }

    @Override
    public List<CarDTO> getInCompanyCarDTOs() {
        final ArrayList<Car> carList = new ArrayList<>(carRepository.findAllByIsOnCompany(true));

        return this.mapEntityList(carList);
    }

    @Override
    public List<CarDTO> getInCompanyActiveCarDTOs() {
        final ArrayList<Car> carList = new ArrayList<>(carRepository.findAllByIsOnCompanyAndIsActive(true, true));

        return this.mapEntityList(carList);
    }

    @Override
    public List<CarDTO> getInCompanyInactiveCarDTOs() {
        final ArrayList<Car> carList = new ArrayList<>(carRepository.findAllByIsOnCompanyAndIsActive(true, false));

        return this.mapEntityList(carList);
    }

    @Override
    public List<CarDTO> getAllDTOs() {
        final ArrayList<Car> carList = new ArrayList<>();
        carRepository.findAll().forEach(carList::add);

        return this.mapEntityList(carList);
    }

    private List<CarDTO> mapEntityList(final List<Car> carList) {
        final ArrayList<CarDTO> carDTOList = new ArrayList<>();
        carList.forEach((car) -> {
            final CarDTO carDTO = new CarDTO(car, new GearBoxTypeDTO(car.getGearboxType()), new FuelTypeDTO(car.getFuelType()), new ModelDTO(car.getModel()),
                    new ParkingDTO(car.getParking()), new ColourDTO(car.getColour()), new TypeDTO(car.getType()));
            carDTOList.add(carDTO);
        });
        return carDTOList;
    }
}