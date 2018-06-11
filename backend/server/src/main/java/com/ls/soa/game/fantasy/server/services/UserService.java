package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.api.server.models.IUser;
import com.ls.soa.game.fantasy.api.server.models.Role;
import com.ls.soa.game.fantasy.api.server.services.IUserService;
import com.ls.soa.game.fantasy.server.daos.UserDao;
import com.ls.soa.game.fantasy.server.models.User;
import com.ls.soa.game.fantasy.server.utils.TokenUtil;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote(com.ls.soa.game.fantasy.api.server.services.IUserService.class)
public class UserService implements IUserService {
    @Inject
    private UserDao userDao;

    @Inject
    private TokenUtil tokenUtil;

    @Override
    public IUser createUser(String token, String username, String password, String role) throws InsufficientPermissionsException, UserAlreadyExistsException {
        if (!tokenUtil.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        User user = new User(username, password, Role.valueOf(role));

        return this.userDao.createUser(user);
    }
}
