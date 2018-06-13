package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.server.utils.MapperUtil;
import com.ls.soa.game.fantasy.server.utils.TokenUtil;

import javax.inject.Inject;

abstract class Service {
    @Inject
    protected TokenUtil tokenUtil;

    @Inject
    private MapperUtil mapperUtil;

    protected <T> T map(Object a, Class<T> b) {
        return mapperUtil.getMapper().map(a, b);
    }
}
