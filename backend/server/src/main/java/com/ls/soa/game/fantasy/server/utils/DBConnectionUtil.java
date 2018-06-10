package com.ls.soa.game.fantasy.server.utils;

import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class DBConnectionUtil {
    private Session session;

    @PostConstruct
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("fantasy");
        session = entityManagerFactory.createEntityManager().unwrap(Session.class);
    }

    public Session getSession() {
        return session;
    }
}
