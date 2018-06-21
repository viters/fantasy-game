package com.ls.soa.game.fantasy.server.utils;

import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class DBConnectionUtil {
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("fantasy");
    }

    public Session createSession() {
        return entityManagerFactory.createEntityManager().unwrap(Session.class);
    }
}
