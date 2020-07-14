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

    @Column(nullable = false)
    private Integer capacity;

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
