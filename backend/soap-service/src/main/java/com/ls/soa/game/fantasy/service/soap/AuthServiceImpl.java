package com.ls.soa.game.fantasy.service.soap;

import com.ls.soa.game.fantasy.api.server.exceptions.IncorrectPasswordException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.services.IAuthService;

import javax.ejb.EJB;
import javax.jws.WebService;

@WebService(endpointInterface = "com.ls.soa.game.fantasy.service.soap.AuthService")
public class AuthServiceImpl implements AuthService {
    @EJB(mappedName = "java:global/server/AuthService!com.ls.soa.game.fantasy.api.server.services.IAuthService")
    private IAuthService authService;

    @Override
    public String login(String username, String password) throws UserNotFoundException, IncorrectPasswordException {
        return authService.login(username, password);
    }
}
