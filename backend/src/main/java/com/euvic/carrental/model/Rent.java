package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "rents")
@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateFrom;

    @Column(nullable = false)
    private LocalDateTime dateTo;

    @Column()
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Car car;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private Parking parkingFrom;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private Parking parkingTo;

    @Column(nullable = false)
    private Boolean isActive;

    public Rent() {

    }

    public Rent(final Long id, final User user, final Car car, final LocalDateTime dateFrom, final LocalDateTime dateTo, final Parking parkingFrom
            , final Parking parkingTo, final Boolean isActive, final String comment) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.parkingFrom = parkingFrom;
        this.parkingTo = parkingTo;
        this.isActive = isActive;
        this.comment = comment;
    }
}
