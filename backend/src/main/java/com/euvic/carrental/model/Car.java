package com.euvic.carrental.model;

import com.euvic.carrental.responses.CarDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "cars")
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String photoInFolderName = null;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private Integer enginePower;

    @Column(nullable = false)
    private Integer capacityOfTrunkScale;

    @Column(nullable = false)
    private Integer capacityOfPeople;

    @Column(nullable = false)
    private Integer doorsNumber;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private GearboxType gearboxType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private FuelType fuelType;

    @Column(nullable = false)
    private LocalDateTime lastInspection;

    @Column(nullable = false)
    private Integer productionYear;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private Boolean isOnCompany = true;

    @Column(nullable = false)
    private Integer mileage;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Model model;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private Parking parking;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Colour colour;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Type type;

    public Car() {
    }

    public Car(final Long id, final String licensePlate, final Integer enginePower, final Integer capacityOfTrunkScale, final Integer capacityOfPeople, final Integer doorsNumber, final GearboxType gearboxType, final FuelType fuelType, final LocalDateTime lastInspection, final Integer productionYear, final Boolean isActive, final Integer mileage, final Model model, final Parking parking, final Colour colour, final Type type) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.enginePower = enginePower;
        this.capacityOfTrunkScale = capacityOfTrunkScale;
        this.capacityOfPeople = capacityOfPeople;
        this.doorsNumber = doorsNumber;
        this.gearboxType = gearboxType;
        this.fuelType = fuelType;
        this.lastInspection = lastInspection;
        this.productionYear = productionYear;
        this.isActive = isActive;
        this.mileage = mileage;
        this.model = model;
        this.parking = parking;
        this.colour = colour;
        this.type = type;
    }

    public Car(final Long id, final CarDTO carDTO, final GearboxType gearboxType, final FuelType fuelType, final Model model, final Parking parking, final Colour colour, final Type type) {
        this.id = id;
        this.licensePlate = carDTO.getLicensePlate();
        this.enginePower = carDTO.getEnginePower();
        this.capacityOfTrunkScale = carDTO.getCapacityOfTrunkScale();
        this.capacityOfPeople = carDTO.getCapacityOfPeople();
        this.doorsNumber = carDTO.getDoorsNumber();
        this.gearboxType = gearboxType;
        this.fuelType = fuelType;
        this.lastInspection = carDTO.getLastInspection();
        this.productionYear = carDTO.getProductionYear();
        this.isActive = carDTO.getIsActive();
        this.mileage = carDTO.getMileage();
        this.model = model;
        this.parking = parking;
        this.colour = colour;
        this.type = type;
    }
}
