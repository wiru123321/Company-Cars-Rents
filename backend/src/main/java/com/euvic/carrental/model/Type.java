package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

// ready Entity
@Data
@Table(name = "car_types")
@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Type() {
    }

    public Type(String typeName) {
        this.name = typeName;
    }
}
