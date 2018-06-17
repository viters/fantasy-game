package com.ls.soa.game.fantasy.api.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.CategoryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.models.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO create(int param1, int categoryDictionaryId, String token) throws UserNotFoundException, CategoryDictionaryNotFoundException;

    List<CategoryDTO> getAllForUser(String token) throws UserNotFoundException;

    List<CategoryDTO> getAll(String token) throws InsufficientPermissionsException;

    CategoryDTO update(String token, CategoryDTO categoryDTO) throws InsufficientPermissionsException, CategoryNotFoundException;

    void delete(String token, long categoryId) throws InsufficientPermissionsException, CategoryNotFoundException;
}
