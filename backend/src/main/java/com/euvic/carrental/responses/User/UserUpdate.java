package com.euvic.carrental.responses.User;

import com.euvic.carrental.model.User;
import com.euvic.carrental.responses.RoleDTO;
import lombok.Data;

@Data
public class UserUpdate {
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;

    public UserUpdate(final User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phoneNumber = user.getPhoneNumber();
    }

    public UserUpdate(final String email, final String name, final String surname, final String phoneNumber) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }
}
