package com.ls.soa.game.fantasy.server.daos;

import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.server.models.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
@SuppressWarnings("unchecked")
public class UserDAO extends DAO {
    public Optional<User> findByUsername(String username) {
        return session.getNamedNativeQuery("findUserByUsername")
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    public User createUser(User user) throws UserAlreadyExistsException {
        if (findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        session.getTransaction().begin();
        session.persist(user);
        session.getTransaction().commit();

        return user;
    }
}
