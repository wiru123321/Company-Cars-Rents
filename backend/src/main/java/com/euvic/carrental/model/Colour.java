package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

// ready Entity
@Data
@Table(name = "colours")
@Entity
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    public Colour() {
    }

    public Colour(String name) {
        this.name = name;
    }
}
