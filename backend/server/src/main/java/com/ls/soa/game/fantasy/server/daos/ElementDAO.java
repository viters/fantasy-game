package com.ls.soa.game.fantasy.server.daos;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.ElementNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.server.models.Category;
import com.ls.soa.game.fantasy.server.models.Element;
import com.ls.soa.game.fantasy.server.models.User;
import org.hibernate.Session;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class ElementDAO extends DAO {
    private CategoryDAO categoryDAO;
    private UserDAO userDAO;

    public ElementDAO(Session session) {
        super(session);
        this.categoryDAO = new CategoryDAO(session);
        this.userDAO = new UserDAO(session);
    }

    public Optional<Element> findById(long id) {
        return session.getNamedNativeQuery("findElementById")
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    public Element createOrUpdate(Element element) throws CategoryDictionaryNotFoundException, UserNotFoundException {
        Optional<Category> category = categoryDAO.findById(element.getCategoryId());

        if (!category.isPresent()) {
            throw new CategoryDictionaryNotFoundException();
        }

        Optional<User> user = userDAO.findById(element.getAuthorId());

        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        session.getTransaction().begin();
        element.setCategory(category.get());
        element.setAuthor(user.get());
        session.saveOrUpdate(element);
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
