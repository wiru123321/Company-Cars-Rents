package com.euvic.carrental.responses;

import com.euvic.carrental.model.Parking;
import com.euvic.carrental.model.ParkingHistory;
import lombok.Data;

@Data
public class ParkingDTO {

    private String town;
    private String postalCode;
    private String streetName;
    private String number;
    private String comment;

    public ParkingDTO(final ParkingHistory entity) {
        this.town = entity.getTown();
        this.postalCode = entity.getPostalCode();
        this.streetName = entity.getStreetName();
        this.number = entity.getNumber();
        this.comment = entity.getComment();
    }

    public ParkingDTO(final Parking entity) {
        this.town = entity.getTown();
        this.postalCode = entity.getPostalCode();
        this.streetName = entity.getStreetName();
        this.number = entity.getNumber();
        this.comment = entity.getComment();
    }

    public ParkingDTO(final String town, final String postalCode, final String street, final String number, final String comment, final boolean isActive) {
        this.town = town;
        this.postalCode = postalCode;
        this.streetName = street;
        this.number = number;
        this.comment = comment;
    }
}
