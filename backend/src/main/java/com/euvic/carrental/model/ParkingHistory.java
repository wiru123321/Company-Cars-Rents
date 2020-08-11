package com.euvic.carrental.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "parkings_history")
@Entity
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
    private String number;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private Boolean isAccepted;

    public ParkingHistory() {
    }

    public ParkingHistory(final Long id, final String town, final String postalCode, final String street, final String number, final String comment, final Boolean isActive, final Boolean isAccepted) {
        this.id = id;
        this.town = town;
        this.postalCode = postalCode;
        this.streetName = street;
        this.number = number;
        this.comment = comment;
        this.isActive = isActive;
        this.isAccepted = isAccepted;
    }
}
