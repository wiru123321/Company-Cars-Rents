package com.euvic.carrental.model;

import lombok.Data;
import org.hibernate.exception.DataException;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "rents_history")
@Entity
public class RentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Date dateFrom;

    @Column(nullable = false)
    private Date dateTo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Car car;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Parking parkingFrom;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Parking parkingTo;

    @Column(nullable = false)
    private Boolean isActive;


    public RentHistory() {

    }

    public RentHistory(User user, Car car, Date dateFrom, Date dateTo, Parking parkingFrom, Parking parkingTo, Boolean isActive) {
        this.user = user;
        this.car = car;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.parkingFrom = parkingFrom;
        this.parkingTo = parkingTo;
        this.isActive = isActive;
    }
}
