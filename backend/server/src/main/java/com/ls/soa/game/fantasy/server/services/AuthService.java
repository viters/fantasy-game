package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.IncorrectPasswordException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.models.UserDTO;
import com.ls.soa.game.fantasy.api.server.services.IAuthService;
import com.ls.soa.game.fantasy.server.daos.UserDAO;
import com.ls.soa.game.fantasy.server.models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Calendar;

@Stateless
@Remote(com.ls.soa.game.fantasy.api.server.services.IAuthService.class)
public class AuthService extends Service implements IAuthService {
    @Inject
    private UserDAO userDAO;

    @Override
    public String login(String username, String password) throws UserNotFoundException, IncorrectPasswordException {
        User user = userDAO.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new IncorrectPasswordException();
        }

        System.out.println("[SERVER] Creating token for user: " + user.getUsername());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);

        return tokenUtil.createToken(Long.toString(user.getId()), user.getRole(), cal.getTimeInMillis());
    }

    @Override
    public UserDTO register(String username, String password) throws UserAlreadyExistsException {
        User user = userDAO.create(new User(username, password));

        return map(user, UserDTO.class);
    }
}
