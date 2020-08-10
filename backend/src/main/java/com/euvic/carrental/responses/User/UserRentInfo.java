package com.euvic.carrental.responses.User;

import lombok.Data;

@Data
public class UserRentInfo {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    public UserRentInfo() {
    }

    public UserRentInfo(final String name, final String surname, final String phoneNumber, final String email) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
