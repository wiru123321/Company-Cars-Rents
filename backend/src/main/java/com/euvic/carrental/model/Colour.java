package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "colours")
@Entity
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private String colourName;

    @OneToMany(mappedBy = "colour",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Car> cars;

    public void ColourEntity() {
    }

    public Colour(String name) {
        this.colourName = name;
    }
}
