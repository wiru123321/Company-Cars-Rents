package com.euvic.carrental.responses;

import com.euvic.carrental.model.Role;
import com.euvic.carrental.model.User;
import lombok.Data;

@Data
public class UserDTO {

    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private Boolean isActive;
    private Role role;

    //TODO role zmienic  na roleDTO
    public UserDTO(final User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phoneNumber = user.getPhoneNumber();
        this.isActive = user.getIsActive();
        this.role = user.getRole();
    }

    public UserDTO(final String login, final String password, final String email, final String name, final String surname, final String phoneNumber, final Boolean isActive, final Role role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.role = role;
    }
}
