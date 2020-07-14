package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "parkings")
@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String town;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String street;

    private String placeNumber;

    private String comment;

    public Parking() {
    }

    public Parking(String town, String postalCode, String street, String placeNumber, String comment) {
        this.town = town;
        this.postalCode = postalCode;
        this.street = street;
        this.placeNumber = placeNumber;
        this.comment = comment;
    }
}
