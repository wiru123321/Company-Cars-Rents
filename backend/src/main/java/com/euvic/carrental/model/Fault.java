package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "faults")
@Entity
public class Fault {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String describe;

    @Column(nullable = false)
    private Boolean setCarInactive;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Car car;

    public Fault() {
    }

    public Fault(final Long id,final Car car, final String describe, final Boolean setCarInactive, final Boolean isActive) {
        this.id = id;
        this.car = car;
        this.describe = describe;
        this.setCarInactive = setCarInactive;
        this.isActive = isActive;
    }
}