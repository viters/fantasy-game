package com.ls.soa.game.fantasy.api.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.models.UserDTO;

public interface IUserService {
    UserDTO create(String token, String username, String password, String role) throws InsufficientPermissionsException, UserAlreadyExistsException, InvalidTokenException;

    UserDTO update(String token, UserDTO userDTO) throws InsufficientPermissionsException, UserNotFoundException, UserAlreadyExistsException, InvalidTokenException;

    void delete(String token, long userId) throws InsufficientPermissionsException, UserNotFoundException, InvalidTokenException;
}
