package com.ls.soa.game.fantasy.server.utils;

import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.models.TokenMetadataDTO;
import com.ls.soa.game.fantasy.api.server.utils.TokenUtil;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenManager {
    private TokenUtil tokenUtil = new TokenUtil();

    public String createToken(String id, String role, long expired) {
        return tokenUtil.createToken(id, role, expired);
    }

    public TokenMetadataDTO validateToken(String token) throws InvalidTokenException {
        return tokenUtil.validateToken(token);
    }
}
