package com.ls.soa.game.fantasy.service.rest.services;

import com.ls.soa.game.fantasy.service.rest.models.ErrorResponse;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ErrorManager {
    private Map<String, ErrorResponse> errors = new HashMap<>();

    ErrorManager() {
        errors.put("username-invalid", new ErrorResponse("username-invalid", "User with provided username was not found"));
        errors.put("password-invalid", new ErrorResponse("password-invalid", "Password provided for given user was invalid"));
        errors.put("username-taken", new ErrorResponse("username-taken", "User with provided username already exists"));
    }

    public Map<String, ErrorResponse> getErrors() {
        return errors;
    }
}
