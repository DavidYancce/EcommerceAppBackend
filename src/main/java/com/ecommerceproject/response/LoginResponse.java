package com.ecommerceproject.response;

import lombok.Getter;

@Getter
public class LoginResponse {
    // Getters, si los necesitas
    private String token;
    private long expiresIn;

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;  // Devuelve la instancia actual para permitir el encadenamiento
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;  // Devuelve la instancia actual para permitir el encadenamiento
    }

}
