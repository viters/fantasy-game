package com.ls.soa.game.fantasy.api.server.models;

public class ElementDTO {
    private long id;
    private String param1;
    private int param2;
    private int param3;
    private int param4;
    private UserDTO author;

    public long getId() {
        return id;
    }

    public String getParam1() {
        return param1;
    }

    public int getParam2() {
        return param2;
    }

    public int getParam3() {
        return param3;
    }

    public int getParam4() {
        return param4;
    }

    public UserDTO getAuthor() {
        return author;
    }
}
