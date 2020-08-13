package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "gearbox_types")
@Entity
public class GearboxType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public GearboxType() {
    }

    public GearboxType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
