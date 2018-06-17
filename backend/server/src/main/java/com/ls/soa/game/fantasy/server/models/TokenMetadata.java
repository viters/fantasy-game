package com.ls.soa.game.fantasy.server.models;

import com.ls.soa.game.fantasy.api.server.models.Role;

public class TokenMetadata {
    private long userId;
    private String role;

    public TokenMetadata(String userId, String role) {
        this.userId = Long.parseLong(userId);
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public boolean isAdmin() {
        return Role.ADMIN.toString().equals(role);
    }
}
