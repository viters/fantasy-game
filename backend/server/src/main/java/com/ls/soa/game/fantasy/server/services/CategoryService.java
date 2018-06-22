package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.*;
import com.ls.soa.game.fantasy.api.server.models.*;
import com.ls.soa.game.fantasy.api.server.services.ICategoryService;
import com.ls.soa.game.fantasy.server.daos.CategoryDAO;
import com.ls.soa.game.fantasy.server.models.Category;
import org.hibernate.Session;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Remote(com.ls.soa.game.fantasy.api.server.services.ICategoryService.class)
public class CategoryService extends Service implements ICategoryService {
    @Override
    public CategoryDTO create(String token, CategoryDTO categoryDTO) throws UserNotFoundException, CategoryDictionaryNotFoundException, InvalidTokenException, InsufficientPermissionsException {
        TokenMetadataDTO metadata = tokenUtil.validateToken(token);

        Category category = map(categoryDTO, Category.class);
        if (category.getAuthorId() != null && category.getAuthorId() != 0
                && category.getAuthorId() != metadata.getUserId() && !metadata.isAdmin()) {
            throw new InsufficientPermissionsException();
        } else {
            category.setAuthorId(metadata.getUserId());
        }

        Session session = dbConnectionUtil.createSession();
        CategoryDAO categoryDAO = new CategoryDAO(session);

        categoryDAO.createOrUpdate(category);

        CategoryDTO result = map(category, CategoryDTO.class);
        session.close();
        return result;
    }

    @Override
    public List<CategoryDTO> getAllForUser(String token) throws UserNotFoundException, InvalidTokenException {
        TokenMetadataDTO metadata = tokenUtil.validateToken(token);

        Session session = dbConnectionUtil.createSession();
        CategoryDAO categoryDAO = new CategoryDAO(session);

        List<Category> categories = categoryDAO.getAllForUser(metadata.getUserId());

        List<CategoryDTO> categoryDTOS = categories.stream().map(c -> map(c, CategoryDTO.class)).collect(Collectors.toList());
        session.close();
        return categoryDTOS;
    }

    @Override
    public List<CategoryDTO> getAll(String token) throws InsufficientPermissionsException, InvalidTokenException {
        if (!tokenUtil.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        Session session = dbConnectionUtil.createSession();
        CategoryDAO categoryDAO = new CategoryDAO(session);

        List<Category> categories = categoryDAO.getAll();

        List<CategoryDTO> categoryDTOS = categories.stream().map(c -> map(c, CategoryDTO.class)).collect(Collectors.toList());
        session.close();
        return categoryDTOS;
    }

    @Override
    public CategoryDTO get(String token, long id) throws InsufficientPermissionsException, InvalidTokenException, CategoryNotFoundException {
        TokenMetadataDTO metadata = tokenUtil.validateToken(token);

        Session session = dbConnectionUtil.createSession();
        CategoryDAO categoryDAO = new CategoryDAO(session);

        Optional<Category> category = categoryDAO.findById(id);

        if (!category.isPresent()) {
            throw new CategoryNotFoundException();
        }

        Category entity = category.get();

        if (entity.getAuthorId() != metadata.getUserId() && !metadata.isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        CategoryDTO result = map(entity, CategoryDTO.class);

        result.setAuthorDTO(map(entity.getAuthor(), UserDTO.class));
        result.setCategoryDictionaryDTO(map(entity.getCategoryDictionary(), CategoryDictionaryDTO.class));
        result.setElementDTOList(entity.getElements().stream().map(e -> map(e, ElementDTO.class)).collect(Collectors.toList()));

        session.close();
        return result;
    }

    @Override
    public CategoryDTO update(String token, CategoryDTO categoryDTO) throws InsufficientPermissionsException, CategoryNotFoundException, InvalidTokenException, UserNotFoundException, CategoryDictionaryNotFoundException {
        TokenMetadataDTO metadata = tokenUtil.validateToken(token);

        Session session = dbConnectionUtil.createSession();
        CategoryDAO categoryDAO = new CategoryDAO(session);

        Category newCategory = map(categoryDTO, Category.class);

        Optional<Category> oldCategory = categoryDAO.findById(newCategory.getId());

        if (!oldCategory.isPresent()) {
            session.close();
            throw new CategoryNotFoundException();
        }

        Category entity = oldCategory.get();

        if (!(metadata.isAdmin() || entity.getAuthorId() == metadata.getUserId())) {
            throw new InsufficientPermissionsException();
        }

        entity.merge(newCategory);
        categoryDAO.createOrUpdate(entity);

        CategoryDTO result = map(entity, CategoryDTO.class);
        session.close();
        return result;
    }

    @Override
    public void delete(String token, long categoryId) throws InsufficientPermissionsException, CategoryNotFoundException, InvalidTokenException {
        TokenMetadataDTO metadata = tokenUtil.validateToken(token);

        Session session = dbConnectionUtil.createSession();
        CategoryDAO categoryDAO = new CategoryDAO(session);

        Optional<Category> category = categoryDAO.findById(categoryId);

        if (!category.isPresent()) {
            session.close();
            throw new CategoryNotFoundException();
        }

        if (!(metadata.isAdmin() || category.get().getAuthor().getId() == metadata.getUserId())) {
            session.close();
            throw new InsufficientPermissionsException();
        }

        categoryDAO.delete(category.get());
        session.close();
    }
}
