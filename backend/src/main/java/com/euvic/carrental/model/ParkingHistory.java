package com.euvic.carrental.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ParkingHistory {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private Long id;

    @Column(nullable = false)
    private String town;

    private String postalCode;

    @Column(nullable = false)
    private String streetName;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Boolean isActive;

    public ParkingHistory() {
    }

    public ParkingHistory(String town, String postalCode, String streetName, String comment, Boolean isActive) {
        this.town = town;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.comment = comment;
        this.isActive = isActive;
    }
}
