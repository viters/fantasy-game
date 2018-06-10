package com.ls.soa.game.fantasy.api.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.api.server.models.IUser;

public interface IUserService {
    IUser createUser(String token, String username, String password, String role) throws InsufficientPermissionsException, UserAlreadyExistsException;
}
