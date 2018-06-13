package com.ls.soa.game.fantasy.server.daos;

import com.ls.soa.game.fantasy.server.utils.DBConnectionUtil;
import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

abstract class DAO {
    @Inject
    private DBConnectionUtil dbConnectionUtil;

    protected Session session;

    @PostConstruct
    private void init() {
        session = dbConnectionUtil.getSession();
    }
}
