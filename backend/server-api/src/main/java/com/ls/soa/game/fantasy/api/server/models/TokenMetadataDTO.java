package com.ls.soa.game.fantasy.api.server.models;

import java.io.Serializable;
import java.util.Objects;

public class TokenMetadataDTO implements Serializable {
    private long userId;
    private String role;

    public TokenMetadataDTO(String userId, String role) {
        this.userId = Long.parseLong(userId);
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return Role.ADMIN.toString().equals(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenMetadataDTO)) return false;
        TokenMetadataDTO that = (TokenMetadataDTO) o;
        return getUserId() == that.getUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    @Override
    public String toString() {
        return "TokenMetadataDTO{" +
                "userId=" + userId +
                ", role='" + role + '\'' +
                '}';
    }
}
