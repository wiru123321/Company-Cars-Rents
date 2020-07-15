package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "parkings")
@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String town;

    private String postalCode;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Boolean isActive;

    public Parking() {
    }

    public Parking(String town, String postalCode, String street, String comment, Boolean isActive) {
        this.town = town;
        this.postalCode = postalCode;
        this.street = street;
        this.comment = comment;
        this.isActive = isActive;
    }
}
