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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Car car;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private Parking parkingFrom;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    private Parking parkingTo;

    @Column(nullable = false)
    private Boolean isActive;

    public Rent() {

    }

    public Rent(final Long id, final User user, final Car car, final Date dateFrom, final Date dateTo, final Parking parkingFrom, final Parking parkingTo, final Boolean isActive) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.parkingFrom = parkingFrom;
        this.parkingTo = parkingTo;
        this.isActive = isActive;
    }
}
