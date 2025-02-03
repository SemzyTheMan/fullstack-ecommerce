package com.semzy_ecommerce.soft.dtos.response;

public class AuthenticationResponse {
    private final int userId;
    private final String token;

    public AuthenticationResponse(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}
