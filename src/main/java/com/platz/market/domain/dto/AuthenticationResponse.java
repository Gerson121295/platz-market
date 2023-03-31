package com.platz.market.domain.dto;

public class AuthenticationResponse {
    private String jwt;

    //Un constructor para enviarle directamente el JWT
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    //Getters and Setters
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}





