package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "rents")
@Entity
public class Rents {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Car car;

    @Column(nullable = false)
    private String dateFrom;
    @Column(nullable = false)
    private String dateTo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Parking parkingFrom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Parking parkingTo;

    @Column(nullable = false)
    private Boolean isActive;

    public Rents(){

    }

    public Rents(User user,Car car, String dateFrom, String dateTo, Parking parkingFrom, Parking parkingTo, boolean isActive){
        this.user = user;
        this.car = car;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.parkingFrom = parkingFrom;
        this.parkingTo = parkingTo;
        this.isActive = isActive;
    }
}
