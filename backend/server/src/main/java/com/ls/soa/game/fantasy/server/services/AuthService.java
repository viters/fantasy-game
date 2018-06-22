package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.IncorrectPasswordException;
import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.models.TokenMetadataDTO;
import com.ls.soa.game.fantasy.api.server.models.UserDTO;
import com.ls.soa.game.fantasy.api.server.services.IAuthService;
import com.ls.soa.game.fantasy.server.daos.UserDAO;
import com.ls.soa.game.fantasy.server.models.User;
import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Calendar;

@Stateless
@Remote(com.ls.soa.game.fantasy.api.server.services.IAuthService.class)
public class AuthService extends Service implements IAuthService {
    @Override
    public String login(String username, String password) throws UserNotFoundException, IncorrectPasswordException {
        Session session = dbConnectionUtil.createSession();
        UserDAO userDAO = new UserDAO(session);

        User user = userDAO.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (!BCrypt.checkpw(password, user.getPassword())) {
            session.close();
            throw new IncorrectPasswordException();
        }

        System.out.println("[SERVER] Creating token for user: " + user.getUsername());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);

        String token = tokenManager.createToken(Long.toString(user.getId()), user.getRole(), cal.getTimeInMillis());
        session.close();
        return token;
    }

    @Override
    public UserDTO register(String username, String password) throws UserAlreadyExistsException {
        Session session = dbConnectionUtil.createSession();
        UserDAO userDAO = new UserDAO(session);

        User user = userDAO.createOrUpdate(new User(username, password));

        UserDTO userDTO = map(user, UserDTO.class);
        session.close();
        return userDTO;
    }

    @Override
    public TokenMetadataDTO validateToken(String token) throws InvalidTokenException {
        return tokenManager.validateToken(token);
    }
}
