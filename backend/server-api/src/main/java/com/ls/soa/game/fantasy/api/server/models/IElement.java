package com.ls.soa.game.fantasy.api.server.models;

public interface IElement {
    long getId();

    String getParam1();

    int getParam2();

    int getParam3();

    int getParam4();

    IUser getAuthor();
}
