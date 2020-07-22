package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "cars")
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String photoInFolderName;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private GearboxType gearboxType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private FuelType fuelType;

    @Column(nullable = false)
    private Date lastInspection;

    @Column(nullable = false)
    private Integer productionYear;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private Boolean isOnCompany;

    @Column(nullable = false)
    private Integer mileage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Model model;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Parking parking;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Colour colour;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Type type;

    public Car(){
    }

    public Car(String licensePlate, Integer enginePower, Integer capacityOfTrunkScale, Integer capacityOfPeople, Integer doorsNumber, GearboxType gearboxType, FuelType fuelType, Date lastInspection, Integer productionYear, Boolean isActive, Boolean isOnCompany, Integer mileage, Model model, Parking parking, Colour colour, Type type){
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
        this.isOnCompany = isOnCompany;
        this.mileage = mileage;
        this.model = model;
        this.parking = parking;
        this.colour = colour;
        this.type = type;
    }
}
