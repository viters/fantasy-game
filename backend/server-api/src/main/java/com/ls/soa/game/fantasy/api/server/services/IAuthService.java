package com.ls.soa.game.fantasy.api.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.IncorrectPasswordException;
import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.models.TokenMetadataDTO;
import com.ls.soa.game.fantasy.api.server.models.UserDTO;

public interface IAuthService {
    String login(String username, String password) throws UserNotFoundException, IncorrectPasswordException;

    UserDTO register(String username, String password) throws UserAlreadyExistsException;

    TokenMetadataDTO validateToken(String token) throws InvalidTokenException;
}
