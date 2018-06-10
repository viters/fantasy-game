package com.ls.soa.game.fantasy.api.server.models;

import java.util.List;

public interface IUser {
    long getId();

    String getUsername();

    String getRole();

    List<? extends ICategory> getCategories();

    List<? extends IElement> getElements();
}
