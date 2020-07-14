package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "cars")
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer year;

    @OneToMany(mappedBy = "car",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Fault> faults;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Mark mark;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Parking parking;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Colour colour;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Type type;

}
