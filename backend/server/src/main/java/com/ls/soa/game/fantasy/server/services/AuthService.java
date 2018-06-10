package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.api.server.services.IAuthService;
import com.ls.soa.game.fantasy.api.server.exceptions.IncorrectPasswordException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.models.IUser;
import com.ls.soa.game.fantasy.server.daos.UserDao;
import com.ls.soa.game.fantasy.server.models.User;
import com.ls.soa.game.fantasy.server.utils.TokenUtil;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Remote;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Calendar;

@Stateless
@Remote(com.ls.soa.game.fantasy.api.server.services.IAuthService.class)
@Startup
public class AuthService implements IAuthService {
    @Inject
    private UserDao userDao;

    @Inject
    private TokenUtil tokenUtil;

    @Override
    public String login(String username, String password) throws UserNotFoundException, IncorrectPasswordException {
        User user = userDao.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (!BCrypt.checkpw(password, user.getPassword())) {
           throw new IncorrectPasswordException();
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);

        return tokenUtil.createToken(Long.toString(user.getId()), user.getRole(), cal.getTimeInMillis());
    }

    @Override
    public IUser register(String username, String password) {
        User user = new User(username, password);
        return userDao.createUser(user);
    }
}
