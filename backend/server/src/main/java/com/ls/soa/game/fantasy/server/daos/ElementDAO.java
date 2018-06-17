package com.ls.soa.game.fantasy.server.daos;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.ElementNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.server.models.Category;
import com.ls.soa.game.fantasy.server.models.Element;
import com.ls.soa.game.fantasy.server.models.User;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class ElementDAO extends DAO {
    @Inject
    private CategoryDAO categoryDAO;

    @Inject
    private UserDAO userDAO;

    public Optional<Element> findById(long id) {
        return session.getNamedNativeQuery("findElementById")
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    public Element create(Element element, long categoryId, long userId) throws CategoryDictionaryNotFoundException, UserNotFoundException {
        Optional<Category> category = categoryDAO.findById(categoryId);

        if (!category.isPresent()) {
            throw new CategoryDictionaryNotFoundException();
        }

        Optional<User> user = userDAO.findById(userId);

        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        session.getTransaction().begin();
        element.setCategory(category.get());
        element.setAuthor(user.get());
        session.save(element);
        session.getTransaction().commit();

        return element;
    }

    public List<Element> getAllForUser(long userId) throws UserNotFoundException {
        Optional<User> user = userDAO.findById(userId);

        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        return user.get().getElements();
    }

    public List<Element> getAll() {
        return (List<Element>) session.getNamedNativeQuery("getAllElements")
                .getResultStream()
                .collect(Collectors.toList());
    }

    public Element update(Element newElement) throws ElementNotFoundException {
        Optional<Element> oldElement = findById(newElement.getId());

        if (!oldElement.isPresent()) {
            throw new ElementNotFoundException();
        }

        Element entity = oldElement.get();
        entity.merge(newElement);

        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();

        return entity;
    }

    public void delete(long elementId) throws ElementNotFoundException {
        Optional<Element> element = findById(elementId);

        if (!element.isPresent()) {
            throw new ElementNotFoundException();
        }

        session.getTransaction().begin();
        session.delete(element.get());
        session.getTransaction().commit();
    }

    public void delete(Element element) {
        session.getTransaction().begin();
        session.delete(element);
        session.getTransaction().commit();
    }
}
