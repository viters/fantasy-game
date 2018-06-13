package com.ls.soa.game.fantasy.api.server.models;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private long id;
    private String username;
    private String role;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
