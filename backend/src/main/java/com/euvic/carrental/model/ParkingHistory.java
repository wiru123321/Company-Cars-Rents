package com.euvic.carrental.model;

import com.euvic.carrental.responses.ParkingDTO;
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

    public ParkingHistory() {
    }

    public ParkingHistory(final Parking parking) {
        this.id = parking.getId();
        this.town = parking.getTown();
        this.postalCode = parking.getPostalCode();
        this.streetName = parking.getStreetName();
        this.number = parking.getNumber();
        this.comment = parking.getComment();
        this.isActive = parking.getIsActive();

    }

    public ParkingHistory(final Long id, final Parking parking) {
        this.id = id;
        this.town = parking.getTown();
        this.postalCode = parking.getPostalCode();
        this.streetName = parking.getStreetName();
        this.number = parking.getNumber();
        this.comment = parking.getComment();
        this.isActive = parking.getIsActive();

    }

    public ParkingHistory(final Long id, final ParkingDTO parking) {
        this.id = id;
        this.town = parking.getTown();
        this.postalCode = parking.getPostalCode();
        this.streetName = parking.getStreetName();
        this.number = parking.getNumber();
        this.comment = parking.getComment();
        this.isActive = parking.getIsActive();

    }

    public ParkingHistory(final Long id, final String town, final String postalCode, final String street, final String number, final String comment, final Boolean isActive) {
        this.id = id;
        this.town = town;
        this.postalCode = postalCode;
        this.streetName = street;
        this.number = number;
        this.comment = comment;
        this.isActive = isActive;
    }
}
