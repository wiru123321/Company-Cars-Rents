package com.euvic.carrental.responses;

import com.euvic.carrental.model.*;
import lombok.Data;

import java.util.Date;

@Data
public class CarDTO {

    private String photoInFolderName;
    private String licensePlate;
    private Integer enginePower;
    private Integer capacityOfTrunkScale;
    private Integer capacityOfPeople;
    private Integer doorsNumber;
    private GearBoxTypeDTO gearBoxTypeDTO;
    private FuelTypeDTO fuelTypeDTO;
    private Date lastInspection;
    private Integer productionYear;
    private Boolean isActive;
    private Boolean isOnCompany;
    private Integer mileage;
    private ModelDTO modelDTO;
    private ParkingDTO parkingDTO;
    private ColourDTO colourDTO;
    private TypeDTO typeDTO;

    public CarDTO(){

    }

    public CarDTO(final Car entity, GearBoxTypeDTO gearBoxTypeDTO, FuelTypeDTO fuelTypeDTO, ModelDTO modelDTO, ParkingDTO parkingDTO, ColourDTO colourDTO, TypeDTO typeDTO){
        this.photoInFolderName = entity.getPhotoInFolderName();
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
        this.isOnCompany = entity.getIsOnCompany();
        this.mileage = entity.getMileage();
        this.modelDTO = modelDTO;
        this.parkingDTO = parkingDTO;
        this.colourDTO = colourDTO;
        this.typeDTO = typeDTO;
    }

    public CarDTO(String photoInFolderName, String licensePlate, Integer enginePower, Integer capacityOfTrunkScale, Integer capacityOfPeople, Integer doorsNumber, GearBoxTypeDTO gearBoxTypeDTO, FuelTypeDTO fuelTypeDTO, Date lastInspection, Integer productionYear, Boolean isActive, Boolean isOnCompany, Integer mileage, ModelDTO modelDTO, ParkingDTO parkingDTO, ColourDTO colourDTO, TypeDTO typeDTO){
        this.photoInFolderName = photoInFolderName;
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
        this.isOnCompany = isOnCompany;
        this.mileage = mileage;
        this.modelDTO = modelDTO;
        this.parkingDTO = parkingDTO;
        this.colourDTO = colourDTO;
        this.typeDTO = typeDTO;
    }
}
