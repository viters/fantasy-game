package com.ls.soa.game.fantasy.api.server.models;

public class CategoryDTO {
    private long id;
    private int param1;
    private UserDTO author;

    public long getId() {
        return id;
    }

    public int getParam1() {
        return param1;
    }

    public UserDTO getAuthor() {
        return author;
    }
}
