package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "gearbox_type")
@Entity
public class GearboxType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public GearboxType() {
    }

    public GearboxType(String name) {
        this.name = name;
    }
}
