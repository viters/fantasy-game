package com.ls.soa.game.fantasy.server.daos;

import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.server.models.User;
import com.ls.soa.game.fantasy.server.utils.DBConnectionUtil;
import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
@SuppressWarnings("unchecked")
public class UserDao {
    @Inject
    private DBConnectionUtil dbConnectionUtil;

    private Session session;

    @PostConstruct
    private void init() {
        session = dbConnectionUtil.getSession();
    }

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
