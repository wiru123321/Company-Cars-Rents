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
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Car car;

    public Fault() {
    }

    public Fault(Car car, String describe, Boolean isActive) {
        this.car = car;
        this.describe = describe;
        this.isActive = isActive;
    }
}
