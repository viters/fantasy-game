package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.CategoryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.models.CategoryDTO;
import com.ls.soa.game.fantasy.api.server.services.ICategoryService;
import com.ls.soa.game.fantasy.server.daos.CategoryDAO;
import com.ls.soa.game.fantasy.server.models.Category;
import com.ls.soa.game.fantasy.server.models.TokenMetadata;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Remote(com.ls.soa.game.fantasy.api.server.services.ICategoryService.class)
public class CategoryService extends Service implements ICategoryService {
    @Inject
    private CategoryDAO categoryDAO;

    @Override
    public CategoryDTO create(int param1, int categoryDictionaryId, String token) throws UserNotFoundException, CategoryDictionaryNotFoundException {
        TokenMetadata metadata = tokenUtil.validateToken(token);

        Category category = new Category(param1);
        categoryDAO.create(category, categoryDictionaryId, metadata.getUserId());

        return map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllForUser(String token) throws UserNotFoundException {
        TokenMetadata metadata = tokenUtil.validateToken(token);
        List<Category> categories = categoryDAO.getAllForUser(metadata.getUserId());

        return categories.stream().map(c -> map(c, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getAll(String token) throws InsufficientPermissionsException {
        if (!tokenUtil.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        List<Category> categories = categoryDAO.getAll();

        return categories.stream().map(c -> map(c, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO update(String token, CategoryDTO categoryDTO) throws InsufficientPermissionsException, CategoryNotFoundException {
        TokenMetadata metadata = tokenUtil.validateToken(token);

        if (!(metadata.isAdmin() || categoryDTO.getAuthor().getId() == metadata.getUserId())) {
            throw new InsufficientPermissionsException();
        }

        Category category = map(categoryDTO, Category.class);
        categoryDAO.update(category);

        return map(category, CategoryDTO.class);
    }

    @Override
    public void delete(String token, long categoryId) throws InsufficientPermissionsException, CategoryNotFoundException {
        TokenMetadata metadata = tokenUtil.validateToken(token);

        Optional<Category> category = categoryDAO.findById(categoryId);

        if (!category.isPresent()) {
            throw new CategoryNotFoundException();
        }

        if (!(metadata.isAdmin() || category.get().getAuthor().getId() == metadata.getUserId())) {
            throw new InsufficientPermissionsException();
        }

        categoryDAO.delete(category.get());
    }
}
