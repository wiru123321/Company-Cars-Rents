package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "faults")
@Entity
public class Fault {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String describe;

    @Column(nullable = false)
    private Boolean isActive;

    public Fault() {
    }

    public Fault(String describe, Boolean isActive) {
        this.describe = describe;
        this.id = id;
    }
}
