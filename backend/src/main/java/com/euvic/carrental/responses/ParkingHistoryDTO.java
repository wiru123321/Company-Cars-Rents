package com.euvic.carrental.responses;

import com.euvic.carrental.model.ParkingHistory;
import lombok.Data;

@Data
public class ParkingHistoryDTO {
    private String town;
    private String postalCode;
    private String streetName;
    private String number;
    private String comment;
    private Boolean isActive;
    private Boolean isAccepted;

    public ParkingHistoryDTO() {
    }

    public ParkingHistoryDTO(final ParkingHistory entity) {
        this.town = entity.getTown();
        this.postalCode = entity.getPostalCode();
        this.streetName = entity.getStreetName();
        this.number = entity.getNumber();
        this.comment = entity.getComment();
        this.isActive = entity.getIsActive();
        this.isAccepted = entity.getIsAccepted();
    }

    public ParkingHistoryDTO(final String town, final String postalCode, final String street, final String number, final String comment, final boolean isActive, final boolean isAccepted) {
        this.town = town;
        this.postalCode = postalCode;
        this.streetName = street;
        this.number = number;
        this.comment = comment;
        this.isActive = isActive;
        this.isAccepted = isAccepted;
    }
}
