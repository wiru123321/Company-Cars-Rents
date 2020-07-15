package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

// ready Entity
@Data
@Table(name = "fuel_type")
@Entity
public class FuelType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public FuelType() {
    }

    public FuelType(String name) {
        this.name = name;
    }
}
