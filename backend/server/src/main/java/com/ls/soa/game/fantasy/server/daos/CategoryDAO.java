package com.ls.soa.game.fantasy.server.daos;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.CategoryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.server.models.Category;
import com.ls.soa.game.fantasy.server.models.CategoryDictionary;
import com.ls.soa.game.fantasy.server.models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@SuppressWarnings("unchecked")
public class CategoryDAO extends DAO {
    @Inject
    private CategoryDictionaryDAO categoryDictionaryDAO;

    @Inject
    private UserDAO userDAO;

    public Category create(Category category, long categoryDictionaryId, long userId) throws CategoryDictionaryNotFoundException, UserNotFoundException {
        Optional<CategoryDictionary> categoryDictionary = categoryDictionaryDAO.findById(categoryDictionaryId);

        if (!categoryDictionary.isPresent()) {
            throw new CategoryDictionaryNotFoundException();
        }

        Optional<User> user = userDAO.findById(userId);

        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        session.getTransaction().begin();
        category.setCategoryDictionary(categoryDictionary.get());
        category.setAuthor(user.get());
        session.save(category);
        session.getTransaction().commit();

        return category;
    }

    public Optional<Category> findById(long id) {
        return session.getNamedNativeQuery("findCategoryById")
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    public List<Category> getAllForUser(long userId) throws UserNotFoundException {
        Optional<User> user = userDAO.findById(userId);

        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        return user.get().getCategories();
    }

    public List<Category> getAll() {
        return (List<Category>) session.getNamedNativeQuery("getAllCategories")
                .getResultStream()
                .collect(Collectors.toList());
    }

    public Category update(Category newCategory) throws CategoryNotFoundException {
        Optional<Category> oldCategory = findById(newCategory.getId());

        if (!oldCategory.isPresent()) {
            throw new CategoryNotFoundException();
        }

        Category entity = oldCategory.get();
        entity.merge(newCategory);

        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();

        return entity;
    }

    public void delete(long categoryId) throws CategoryNotFoundException {
        Optional<Category> category = findById(categoryId);

        if (!category.isPresent()) {
            throw new CategoryNotFoundException();
        }

        session.getTransaction().begin();
        session.delete(category.get());
        session.getTransaction().commit();
    }

    public void delete(Category category) {
        session.getTransaction().begin();
        session.delete(category);
        session.getTransaction().commit();
    }
}
