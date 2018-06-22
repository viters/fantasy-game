package com.ls.soa.game.fantasy.api.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.*;
import com.ls.soa.game.fantasy.api.server.models.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO create(String token, CategoryDTO categoryDTO) throws UserNotFoundException, CategoryDictionaryNotFoundException, InvalidTokenException, InsufficientPermissionsException;

    List<CategoryDTO> getAllForUser(String token) throws InvalidTokenException, UserNotFoundException;

    List<CategoryDTO> getAll(String token) throws InsufficientPermissionsException, InvalidTokenException;

    CategoryDTO get(String token, long id) throws InsufficientPermissionsException, InvalidTokenException, CategoryNotFoundException;

    CategoryDTO update(String token, CategoryDTO categoryDTO) throws InsufficientPermissionsException, CategoryNotFoundException, InvalidTokenException, UserNotFoundException, CategoryDictionaryNotFoundException;

    void delete(String token, long categoryId) throws InsufficientPermissionsException, CategoryNotFoundException, InvalidTokenException;
}
