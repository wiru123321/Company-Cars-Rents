package com.euvic.carrental.responses;

import com.euvic.carrental.model.Car;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CarDTO {

    private String licensePlate;
    private Integer enginePower;
    private Integer capacityOfTrunkScale;
    private Integer capacityOfPeople;
    private Integer doorsNumber;
    private GearBoxTypeDTO gearBoxTypeDTO;
    private FuelTypeDTO fuelTypeDTO;
    private LocalDateTime lastInspection;
    private Integer productionYear;
    private Boolean isActive;
    private Integer mileage;
    private ModelDTO modelDTO;
    private ParkingDTO parkingDTO;
    private ColourDTO colourDTO;
    private TypeDTO typeDTO;

    public CarDTO() {

    }

    public CarDTO(final Car entity, final GearBoxTypeDTO gearBoxTypeDTO, final FuelTypeDTO fuelTypeDTO, final ModelDTO modelDTO, final ParkingDTO parkingDTO, final ColourDTO colourDTO, final TypeDTO typeDTO) {
        this.licensePlate = entity.getLicensePlate();
        this.enginePower = entity.getEnginePower();
        this.capacityOfTrunkScale = entity.getCapacityOfTrunkScale();
        this.capacityOfPeople = entity.getCapacityOfPeople();
        this.doorsNumber = entity.getDoorsNumber();
        this.gearBoxTypeDTO = gearBoxTypeDTO;
        this.fuelTypeDTO = fuelTypeDTO;
        this.lastInspection = entity.getLastInspection();
        this.productionYear = entity.getProductionYear();
        this.isActive = entity.getIsActive();
        this.mileage = entity.getMileage();
        this.modelDTO = modelDTO;
        this.parkingDTO = parkingDTO;
        this.colourDTO = colourDTO;
        this.typeDTO = typeDTO;
    }

    public CarDTO(final String licensePlate, final Integer enginePower, final Integer capacityOfTrunkScale, final Integer capacityOfPeople, final Integer doorsNumber, final GearBoxTypeDTO gearBoxTypeDTO, final FuelTypeDTO fuelTypeDTO, final LocalDateTime lastInspection, final Integer productionYear, final Boolean isActive, final Integer mileage, final ModelDTO modelDTO, final ParkingDTO parkingDTO, final ColourDTO colourDTO, final TypeDTO typeDTO) {
        this.licensePlate = licensePlate;
        this.enginePower = enginePower;
        this.capacityOfTrunkScale = capacityOfTrunkScale;
        this.capacityOfPeople = capacityOfPeople;
        this.doorsNumber = doorsNumber;
        this.gearBoxTypeDTO = gearBoxTypeDTO;
        this.fuelTypeDTO = fuelTypeDTO;
        this.lastInspection = lastInspection;
        this.productionYear = productionYear;
        this.isActive = isActive;
        this.mileage = mileage;
        this.modelDTO = modelDTO;
        this.parkingDTO = parkingDTO;
        this.colourDTO = colourDTO;
        this.typeDTO = typeDTO;
    }
}
