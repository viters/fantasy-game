package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.server.utils.DBConnectionUtil;
import com.ls.soa.game.fantasy.server.utils.MapperUtil;
import com.ls.soa.game.fantasy.server.utils.TokenManager;

import javax.inject.Inject;

abstract class Service {
    @Inject
    protected TokenManager tokenManager;

    @Inject
    private MapperUtil mapperUtil;

    @Inject
    protected DBConnectionUtil dbConnectionUtil;

    protected <T> T map(Object a, Class<T> b) {
        return mapperUtil.getMapper().map(a, b);
    }
}
