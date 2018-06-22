package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.models.CategoryDictionaryDTO;
import com.ls.soa.game.fantasy.api.server.services.ICategoryDictionaryService;
import com.ls.soa.game.fantasy.server.daos.CategoryDictionaryDAO;
import com.ls.soa.game.fantasy.server.models.CategoryDictionary;
import org.hibernate.Session;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Remote(com.ls.soa.game.fantasy.api.server.services.ICategoryDictionaryService.class)
public class CategoryDictionaryService extends Service implements ICategoryDictionaryService {
    @Override
    public CategoryDictionaryDTO create(String token, CategoryDictionaryDTO categoryDictionaryDTO) throws InsufficientPermissionsException, InvalidTokenException {
        if (!tokenManager.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        Session session = dbConnectionUtil.createSession();
        CategoryDictionaryDAO categoryDictionaryDAO = new CategoryDictionaryDAO(session);

        CategoryDictionary categoryDictionary = map(categoryDictionaryDTO, CategoryDictionary.class);
        categoryDictionaryDAO.createOrUpdate(categoryDictionary);

        CategoryDictionaryDTO dto = map(categoryDictionary, CategoryDictionaryDTO.class);
        session.close();
        return dto;
    }

    @Override
    public List<CategoryDictionaryDTO> getAll(String token) throws InvalidTokenException {
        tokenManager.validateToken(token);

        Session session = dbConnectionUtil.createSession();
        CategoryDictionaryDAO categoryDictionaryDAO = new CategoryDictionaryDAO(session);

        List<CategoryDictionary> categoryDictionaries = categoryDictionaryDAO.getAll();

        List<CategoryDictionaryDTO> categoryDictionaryDTOS =
                categoryDictionaries.stream().map(c -> map(c, CategoryDictionaryDTO.class)).collect(Collectors.toList());
        session.close();
        return categoryDictionaryDTOS;
    }

    @Override
    public CategoryDictionaryDTO update(String token, CategoryDictionaryDTO categoryDictionaryDTO) throws InsufficientPermissionsException, CategoryDictionaryNotFoundException, InvalidTokenException {
        if (!tokenManager.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        Session session = dbConnectionUtil.createSession();
        CategoryDictionaryDAO categoryDictionaryDAO = new CategoryDictionaryDAO(session);

        CategoryDictionary newCategoryDictionary = map(categoryDictionaryDTO, CategoryDictionary.class);

        Optional<CategoryDictionary> oldCategoryDictionary = categoryDictionaryDAO.findById(newCategoryDictionary.getId());

        if (!oldCategoryDictionary.isPresent()) {
            session.close();
            throw new CategoryDictionaryNotFoundException();
        }

        CategoryDictionary entity = oldCategoryDictionary.get();
        entity.merge(newCategoryDictionary);

        categoryDictionaryDAO.createOrUpdate(entity);

        CategoryDictionaryDTO dto = map(entity, CategoryDictionaryDTO.class);
        session.close();
        return dto;
    }

    @Override
    public void delete(String token, long categoryDictionaryId) throws InsufficientPermissionsException, CategoryDictionaryNotFoundException, InvalidTokenException {
        if (!tokenManager.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }
        Session session = dbConnectionUtil.createSession();
        CategoryDictionaryDAO categoryDictionaryDAO = new CategoryDictionaryDAO(session);

        categoryDictionaryDAO.delete(categoryDictionaryId);
        session.close();
    }
}
