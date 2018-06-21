package com.ls.soa.game.fantasy.server.daos;

import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.server.models.User;
import org.hibernate.Session;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class UserDAO extends DAO {
    public UserDAO(Session session) {
        super(session);
    }

    public User createOrUpdate(User user) throws UserAlreadyExistsException {
        if (findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        session.getTransaction().begin();
        session.saveOrUpdate(user);
        session.getTransaction().commit();

        return user;
    }

    public Optional<User> findById(long id) {
        return session.getNamedNativeQuery("findUserById")
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    public Optional<User> findByUsername(String username) {
        return (Optional<User>) session.getNamedNativeQuery("findUserByUsername")
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }


    public List<User> getAll() {
        return (List<User>) session.getNamedNativeQuery("getAllUsers")
                .getResultStream()
                .collect(Collectors.toList());
    }

    public void delete(long userId) throws UserNotFoundException {
        Optional<User> user = findById(userId);

        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        session.getTransaction().begin();
        session.delete(user.get());
        session.getTransaction().commit();
    }
}
