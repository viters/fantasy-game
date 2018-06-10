package com.ls.soa.game.fantasy.service.rest.models;

public class TokenCredentials {
    private String token;

    public TokenCredentials(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
