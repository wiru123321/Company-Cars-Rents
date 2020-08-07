package com.euvic.carrental.responses.User;

import lombok.Data;

@Data
public class EditAccount {

    private String newEmail;
    private String password;
    private String newPhoneNumber;
    private String newPassword;
}
