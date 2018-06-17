package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.models.CategoryDictionaryDTO;
import com.ls.soa.game.fantasy.api.server.services.ICategoryDictionaryService;
import com.ls.soa.game.fantasy.server.daos.CategoryDictionaryDAO;
import com.ls.soa.game.fantasy.server.models.CategoryDictionary;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(com.ls.soa.game.fantasy.api.server.services.ICategoryDictionaryService.class)
public class CategoryDictionaryService extends Service implements ICategoryDictionaryService {
    @Inject
    private CategoryDictionaryDAO categoryDictionaryDAO;

    @Override
    public CategoryDictionaryDTO create(String token, CategoryDictionaryDTO categoryDictionaryDTO) throws InsufficientPermissionsException {
        if (!tokenUtil.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        CategoryDictionary categoryDictionary = map(categoryDictionaryDTO, CategoryDictionary.class);
        categoryDictionaryDAO.create(categoryDictionary);

        return map(categoryDictionary, CategoryDictionaryDTO.class);
    }

    @Override
    public List<CategoryDictionaryDTO> getAll(String token) throws InsufficientPermissionsException {
        if (!tokenUtil.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        List<CategoryDictionary> categoryDictionaries = categoryDictionaryDAO.getAll();

        return categoryDictionaries.stream().map(c -> map(c, CategoryDictionaryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDictionaryDTO update(String token, CategoryDictionaryDTO categoryDictionaryDTO) throws InsufficientPermissionsException, CategoryDictionaryNotFoundException {
        if (!tokenUtil.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        CategoryDictionary categoryDictionary = map(categoryDictionaryDTO, CategoryDictionary.class);
        categoryDictionaryDAO.update(categoryDictionary);

        return map(categoryDictionary, CategoryDictionaryDTO.class);
    }

    @Override
    public void delete(String token, long categoryDictionaryId) throws InsufficientPermissionsException, CategoryDictionaryNotFoundException {
        if (!tokenUtil.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        categoryDictionaryDAO.delete(categoryDictionaryId);
    }
}
