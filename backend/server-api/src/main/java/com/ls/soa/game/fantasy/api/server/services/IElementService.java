package com.ls.soa.game.fantasy.api.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.ElementNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.models.ElementDTO;

import java.util.List;

public interface IElementService {
    ElementDTO create(String param1, int param2, int param3, int param4, int categoryId, String token) throws UserNotFoundException, CategoryDictionaryNotFoundException;

    List<ElementDTO> getAllForUser(String token) throws UserNotFoundException;

    List<ElementDTO> getAll(String token) throws InsufficientPermissionsException;

    ElementDTO update(String token, ElementDTO elementDTO) throws InsufficientPermissionsException, ElementNotFoundException;

    void delete(String token, long elementId) throws InsufficientPermissionsException, ElementNotFoundException;
}
