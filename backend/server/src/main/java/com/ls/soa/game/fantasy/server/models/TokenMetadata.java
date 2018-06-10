package com.ls.soa.game.fantasy.server.models;

import com.ls.soa.game.fantasy.api.server.models.Role;

public class TokenMetadata {
    private long id;
    private String role;

    public TokenMetadata(String id, String role) {
        this.id = Long.parseLong(id);
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public boolean isAdmin() {
        return Role.ADMIN.toString().equals(role);
    }
}
