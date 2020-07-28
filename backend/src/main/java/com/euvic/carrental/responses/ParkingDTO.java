package com.euvic.carrental.responses;

import com.euvic.carrental.model.Parking;
import lombok.Data;

@Data
public class ParkingDTO {

    private String town;
    private String postalCode;
    private String streetName;
    private String number;
    private String comment;
    private Boolean isActive;

    public ParkingDTO() {
    }

    public ParkingDTO(final Parking entity) {
        this.town = entity.getTown();
        this.postalCode = entity.getPostalCode();
        this.streetName = entity.getStreetName();
        this.number = entity.getNumber();
        this.comment = entity.getComment();
        this.isActive = entity.getIsActive();
    }

    public ParkingDTO(final String town, final String postalCode, final String street, final String number, final String comment, final boolean isActive) {
        this.town = town;
        this.postalCode = postalCode;
        this.streetName = street;
        this.number = number;
        this.comment = comment;
        this.isActive = isActive;
    }
}
