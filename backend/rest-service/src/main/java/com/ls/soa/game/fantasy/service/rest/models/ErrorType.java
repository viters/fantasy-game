package com.ls.soa.game.fantasy.service.rest.models;

public enum ErrorType {
    USERNAME_INVALID("username-invalid"),
    PASSWORD_INVALID("password-invalid"),
    USERNAME_TAKEN("username-taken"),
    NO_SUCH_CATEGORY_DICTIONARY("no-such-category-dictionary"),
    NO_SUCH_CATEGORY("no-such-category"),
    NO_SUCH_ELEMENT("no-such-element"),
    NO_SUCH_ELEMENT_PARAM("no-such-element-param"),
    UNAUTHORIZED("unauthorized"),;

    private String name;

    ErrorType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Error{" +
                "name='" + name + '\'' +
                '}';
    }
}
