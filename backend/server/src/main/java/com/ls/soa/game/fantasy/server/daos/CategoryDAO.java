package com.ls.soa.game.fantasy.server.daos;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.CategoryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.server.models.Category;
import com.ls.soa.game.fantasy.server.models.CategoryDictionary;
import com.ls.soa.game.fantasy.server.models.User;
import org.hibernate.Session;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class CategoryDAO extends DAO {
    private CategoryDictionaryDAO categoryDictionaryDAO;

    private UserDAO userDAO;

    public CategoryDAO(Session session) {
        super(session);
        this.categoryDictionaryDAO = new CategoryDictionaryDAO(session);
        this.userDAO = new UserDAO(session);
    }

    public Category createOrUpdate(Category category) throws CategoryDictionaryNotFoundException, UserNotFoundException {
        Optional<CategoryDictionary> categoryDictionary = categoryDictionaryDAO.findById(category.getCategoryDictionaryId());

        if (!categoryDictionary.isPresent()) {
            throw new CategoryDictionaryNotFoundException();
        }

        Optional<User> user = userDAO.findById(category.getAuthorId());

        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        session.getTransaction().begin();
        category.setCategoryDictionary(categoryDictionary.get());
        category.setAuthor(user.get());
        session.saveOrUpdate(category);
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
