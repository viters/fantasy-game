package com.ls.soa.game.fantasy.service.rest.models;

public class ErrorResponse {
    private String code;
    private String devMessage;

    public ErrorResponse(ErrorType errorType, String devMessage) {
        this.code = errorType.toString();
        this.devMessage = devMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDevMessage() {
        return devMessage;
    }
}
