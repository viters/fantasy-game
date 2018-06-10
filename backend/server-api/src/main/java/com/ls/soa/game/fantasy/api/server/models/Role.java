package com.ls.soa.game.fantasy.api.server.models;

public enum Role {
    ADMIN("admin"), USER("user");

    private final String role;

    Role(final String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}