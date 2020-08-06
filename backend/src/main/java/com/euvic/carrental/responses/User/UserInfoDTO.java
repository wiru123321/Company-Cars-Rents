package com.euvic.carrental.responses.User;

import com.euvic.carrental.model.User;
import lombok.Data;

@Data
public class UserInfoDTO {

    private String login;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String role;

    public UserInfoDTO() {
    }

    public UserInfoDTO(final User user) {
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phoneNumber = user.getPhoneNumber();
        this.role = user.getRole().getName();
    }

    public UserInfoDTO(final UserDTO userDTO) {
        this.login = userDTO.getLogin();
        this.email = userDTO.getEmail();
        this.name = userDTO.getName();
        this.surname = userDTO.getSurname();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.role = userDTO.getRoleDTO().getName();
    }
}
