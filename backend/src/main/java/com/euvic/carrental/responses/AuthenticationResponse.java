package com.euvic.carrental.responses;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String token;
    private String role;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(final String token, final String role) {
        this.token = token;
        this.role = role;
    }
}
