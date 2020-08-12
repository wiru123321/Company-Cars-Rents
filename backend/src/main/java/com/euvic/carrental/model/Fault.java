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
    private String description;

    @Column(nullable = false)
    private Boolean setCarInactive;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Car car;

    public Fault() {
    }

    public Fault(final Long id, final Car car, final String description, final Boolean setCarInactive, final Boolean isActive) {
        this.id = id;
        this.car = car;
        this.description = description;
        this.setCarInactive = setCarInactive;
        this.isActive = isActive;
    }
}