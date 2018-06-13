package com.ls.soa.game.fantasy.server.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MapperUtil {
    private Mapper mapper;

    @PostConstruct
    public void init() {
        mapper = new DozerBeanMapper();
    }

    public Mapper getMapper() {
        return mapper;
    }
}
