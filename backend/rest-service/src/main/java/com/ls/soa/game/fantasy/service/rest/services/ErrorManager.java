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
        errors.put("invalid-category-dictionary", new ErrorResponse("invalid-category-dictionary", "Category dictionary not found"));
        errors.put("invalid-category", new ErrorResponse("invalid-category-dictionary", "Category not found"));
        errors.put("invalid-element", new ErrorResponse("invalid-category-dictionary", "Element not found"));
        errors.put("unauthorized", new ErrorResponse("unauthorized", "Unauthorized to perform action"));
        errors.put("invalid-element-param", new ErrorResponse("invalid-element-param", "Invalid element param selected"));
    }

    public Map<String, ErrorResponse> getErrors() {
        return errors;
    }
}
