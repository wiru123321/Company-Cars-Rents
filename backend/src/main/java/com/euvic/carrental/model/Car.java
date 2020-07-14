package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "cars")
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String licensePlate;
//
    @Column(nullable = false)
    private Integer enginePower;

    @Column(nullable = false)
    private Integer capacityOfTrunkScale;

    @Column(nullable = false)
    private Integer capacityOfPeople;

    @Column(nullable = false)
    private Integer doorsNumer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private GearboxType gearboxType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private FuelType fuelType;

    @Column(nullable = false)
    private String lastInspection;
//
    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private Boolean isOnCompany;

    @Column(nullable = false)
    private Integer mileage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Mark mark;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Parking parking;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Colour colour;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Type type;

}
