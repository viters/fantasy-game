package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.api.server.models.Role;
import com.ls.soa.game.fantasy.api.server.models.UserDTO;
import com.ls.soa.game.fantasy.api.server.services.IUserService;
import com.ls.soa.game.fantasy.server.daos.UserDAO;
import com.ls.soa.game.fantasy.server.models.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote(com.ls.soa.game.fantasy.api.server.services.IUserService.class)
public class UserService extends Service implements IUserService {
    @Inject
    private UserDAO userDAO;

    @Override
    public UserDTO createUser(String token, String username, String password, String role) throws InsufficientPermissionsException, UserAlreadyExistsException {
        if (!tokenUtil.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        User user = new User(username, password, Role.valueOf(role));
        userDAO.createUser(user);

        return map(user, UserDTO.class);
    }
}
