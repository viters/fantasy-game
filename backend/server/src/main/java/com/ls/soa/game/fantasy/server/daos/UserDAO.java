package com.ls.soa.game.fantasy.server.daos;

import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.server.models.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@SuppressWarnings("unchecked")
public class UserDAO extends DAO {
    public Optional<User> findById(long id) {
        return session.getNamedNativeQuery("findUserById")
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    public Optional<User> findByUsername(String username) {
        return session.getNamedNativeQuery("findUserByUsername")
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    public User create(User user) throws UserAlreadyExistsException {
        if (findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();

        return user;
    }

    public List<User> getAll() {
        return (List<User>) session.getNamedNativeQuery("getAllUsers")
                .getResultStream()
                .collect(Collectors.toList());
    }

    public User update(User newUser) throws UserNotFoundException, UserAlreadyExistsException {
        if (findByUsername(newUser.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Optional<User> oldUser = findById(newUser.getId());

        if (!oldUser.isPresent()) {
            throw new UserNotFoundException();
        }

        User entity = oldUser.get();
        entity.merge(newUser);

        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();

        return entity;
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
