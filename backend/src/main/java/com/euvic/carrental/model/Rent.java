package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "rents")
@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //TODO check date/time (get know how to use it, and format)
    @Column(nullable = false)
    private Date dateFrom; // Here must be date + time;

    @Column(nullable = false)
    private Date dateTo;

    @OneToOne(fetch = FetchType.LAZY, optional = false) //TODO here is also many to one
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Parking parkingFrom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Parking parkingTo;

    @Column(nullable = false)
    private Boolean isActive;

    public Rent() {

    }

    public Rent(User user, Car car, Date dateFrom, Date dateTo, Parking parkingFrom, Parking parkingTo, boolean isActive) {
        this.user = user;
        this.car = car;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.parkingFrom = parkingFrom;
        this.parkingTo = parkingTo;
        this.isActive = isActive;
    }
}
