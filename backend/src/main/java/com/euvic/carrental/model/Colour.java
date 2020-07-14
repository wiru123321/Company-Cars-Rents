package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "colours")
@Entity
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private String name;

    public void ColourEntity() {
    }

    public Colour(String name) {
        this.name = name;
    }
}
