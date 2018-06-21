package com.ls.soa.game.fantasy.server.daos;

import com.ls.soa.game.fantasy.server.utils.DBConnectionUtil;
import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

abstract class DAO {
    Session session;

    public DAO(Session session) {
        this.session = session;
    }
}
