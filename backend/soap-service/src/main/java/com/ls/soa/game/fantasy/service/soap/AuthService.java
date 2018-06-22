package com.ls.soa.game.fantasy.service.soap;

import com.ls.soa.game.fantasy.api.server.exceptions.IncorrectPasswordException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;

import javax.jws.WebService;

@WebService
public interface AuthService {
    String login(String username, String password) throws UserNotFoundException, IncorrectPasswordException;
}
