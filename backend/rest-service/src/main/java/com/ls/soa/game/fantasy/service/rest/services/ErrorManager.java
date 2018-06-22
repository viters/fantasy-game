package com.ls.soa.game.fantasy.service.rest.services;

import com.ls.soa.game.fantasy.service.rest.models.ErrorResponse;
import com.ls.soa.game.fantasy.service.rest.models.ErrorType;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ErrorManager {
    private Map<String, Map<ErrorType, ErrorResponse>> errors = new HashMap<>();

    ErrorManager() {
        errors.put("en", new HashMap<>());
        createEnglishErrors();

        errors.put("pl", new HashMap<>());
        createPolishErrors();
    }

    public Map<ErrorType, ErrorResponse> getErrorsForLang(String lang) {
        return errors.get(lang);
    }

    private void createEnglishErrors() {
        Map<ErrorType, ErrorResponse> englishErrors = errors.get("en");
        englishErrors.put(ErrorType.USERNAME_INVALID, new ErrorResponse(ErrorType.USERNAME_INVALID, "User with provided username was not found"));
        englishErrors.put(ErrorType.PASSWORD_INVALID, new ErrorResponse(ErrorType.PASSWORD_INVALID, "Password provided for given user was invalid"));
        englishErrors.put(ErrorType.USERNAME_TAKEN, new ErrorResponse(ErrorType.USERNAME_TAKEN, "User with provided username already exists"));
        englishErrors.put(ErrorType.NO_SUCH_CATEGORY_DICTIONARY, new ErrorResponse(ErrorType.NO_SUCH_CATEGORY_DICTIONARY, "Category dictionary not found"));
        englishErrors.put(ErrorType.NO_SUCH_CATEGORY, new ErrorResponse(ErrorType.NO_SUCH_CATEGORY, "Category not found"));
        englishErrors.put(ErrorType.NO_SUCH_ELEMENT, new ErrorResponse(ErrorType.NO_SUCH_ELEMENT, "Element not found"));
        englishErrors.put(ErrorType.UNAUTHORIZED, new ErrorResponse(ErrorType.UNAUTHORIZED, "Unauthorized to perform action"));
        englishErrors.put(ErrorType.NO_SUCH_ELEMENT_PARAM, new ErrorResponse(ErrorType.NO_SUCH_ELEMENT_PARAM, "Invalid element param selected"));
    }

    private void createPolishErrors() {
        Map<ErrorType, ErrorResponse> englishErrors = errors.get("pl");
        englishErrors.put(ErrorType.USERNAME_INVALID, new ErrorResponse(ErrorType.USERNAME_INVALID, "Nie istnieje taki użytkownik"));
        englishErrors.put(ErrorType.PASSWORD_INVALID, new ErrorResponse(ErrorType.PASSWORD_INVALID, "Podane hasło było nieprawidłowe"));
        englishErrors.put(ErrorType.USERNAME_TAKEN, new ErrorResponse(ErrorType.USERNAME_TAKEN, "Taki użytkownik już istnieje"));
        englishErrors.put(ErrorType.NO_SUCH_CATEGORY_DICTIONARY, new ErrorResponse(ErrorType.NO_SUCH_CATEGORY_DICTIONARY, "Definicja kategorii nie została znaleziona"));
        englishErrors.put(ErrorType.NO_SUCH_CATEGORY, new ErrorResponse(ErrorType.NO_SUCH_CATEGORY, "Kategoria nie została znaleziona"));
        englishErrors.put(ErrorType.NO_SUCH_ELEMENT, new ErrorResponse(ErrorType.NO_SUCH_ELEMENT, "Element nie został znaleziony"));
        englishErrors.put(ErrorType.UNAUTHORIZED, new ErrorResponse(ErrorType.UNAUTHORIZED, "Brak uprawnień, żeby wykonać akcję"));
        englishErrors.put(ErrorType.NO_SUCH_ELEMENT_PARAM, new ErrorResponse(ErrorType.NO_SUCH_ELEMENT_PARAM, "Wybrano nieprawidłowy parametr elementu"));
    }
}
