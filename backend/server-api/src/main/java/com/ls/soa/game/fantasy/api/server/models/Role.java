package com.ls.soa.game.fantasy.api.server.models;

import java.io.Serializable;

public enum Role implements Serializable {
    USER("user"), ADMIN("admin");

    private String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}