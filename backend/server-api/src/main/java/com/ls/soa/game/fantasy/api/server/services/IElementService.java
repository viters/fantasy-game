package com.ls.soa.game.fantasy.api.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.*;
import com.ls.soa.game.fantasy.api.server.models.ElementDTO;

import java.util.List;

public interface IElementService {
    ElementDTO create(String token, ElementDTO elementDTO) throws UserNotFoundException, CategoryDictionaryNotFoundException, InvalidTokenException, InsufficientPermissionsException;

    List<ElementDTO> getAllForUser(String token) throws InvalidTokenException, UserNotFoundException;

    List<ElementDTO> getAll(String token) throws InsufficientPermissionsException, InvalidTokenException;

    List<ElementDTO> getTopForParamByCategoryDictionary(String token, String paramName, int limit) throws InvalidTokenException, InvalidElementParamException;

    ElementDTO update(String token, ElementDTO elementDTO) throws InsufficientPermissionsException, ElementNotFoundException, InvalidTokenException, UserNotFoundException, CategoryDictionaryNotFoundException;

    void delete(String token, long elementId) throws InsufficientPermissionsException, ElementNotFoundException, InvalidTokenException;
}
