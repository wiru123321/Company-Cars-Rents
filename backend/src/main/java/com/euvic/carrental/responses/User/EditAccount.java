package com.euvic.carrental.responses.User;

import lombok.Data;

@Data
public class EditAccount {

    private String email;
    private String password;
    private String phoneNumber;
    private String newPassword;
}
