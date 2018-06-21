package com.ls.soa.game.fantasy.api.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.models.CategoryDictionaryDTO;

import java.util.List;

public interface ICategoryDictionaryService {
    CategoryDictionaryDTO create(String token, CategoryDictionaryDTO categoryDictionaryDTO) throws InsufficientPermissionsException, InvalidTokenException;

    List<CategoryDictionaryDTO> getAll(String token) throws InvalidTokenException;

    CategoryDictionaryDTO update(String token, CategoryDictionaryDTO categoryDictionaryDTO) throws InsufficientPermissionsException, CategoryDictionaryNotFoundException, InvalidTokenException;

    void delete(String token, long categoryDictionaryId) throws InsufficientPermissionsException, CategoryDictionaryNotFoundException, InvalidTokenException;
}
