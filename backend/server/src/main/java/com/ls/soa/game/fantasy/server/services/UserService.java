package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.models.Role;
import com.ls.soa.game.fantasy.api.server.models.TokenMetadataDTO;
import com.ls.soa.game.fantasy.api.server.models.UserDTO;
import com.ls.soa.game.fantasy.api.server.services.IUserService;
import com.ls.soa.game.fantasy.server.daos.UserDAO;
import com.ls.soa.game.fantasy.server.models.User;
import org.hibernate.Session;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Optional;

@Stateless
@Remote(com.ls.soa.game.fantasy.api.server.services.IUserService.class)
public class UserService extends Service implements IUserService {
    @Override
    public UserDTO create(String token, String username, String password, String role) throws InsufficientPermissionsException, UserAlreadyExistsException, InvalidTokenException {
        if (!tokenManager.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        Session session = dbConnectionUtil.createSession();
        UserDAO userDAO = new UserDAO(session);

        User user = new User(username, password, Role.valueOf(role));
        userDAO.createOrUpdate(user);

        UserDTO dto = map(user, UserDTO.class);
        session.close();
        return dto;
    }

    @Override
    public UserDTO update(String token, UserDTO userDTO) throws InsufficientPermissionsException, UserNotFoundException, UserAlreadyExistsException, InvalidTokenException {
        TokenMetadataDTO metadata = tokenManager.validateToken(token);

        if (!(metadata.isAdmin() || userDTO.getId() == metadata.getUserId())) {
            throw new InsufficientPermissionsException();
        }

        Session session = dbConnectionUtil.createSession();
        UserDAO userDAO = new UserDAO(session);

        User newUser = map(userDTO, User.class);

        Optional<User> oldUser = userDAO.findById(newUser.getId());

        if (!oldUser.isPresent()) {
            session.close();
            throw new UserNotFoundException();
        }

        User entity = oldUser.get();
        entity.merge(newUser);

        userDAO.createOrUpdate(entity);

        UserDTO result = map(entity, UserDTO.class);
        session.close();
        return result;
    }

    @Override
    public void delete(String token, long userId) throws InsufficientPermissionsException, UserNotFoundException, InvalidTokenException {
        if (!tokenManager.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }
        Session session = dbConnectionUtil.createSession();
        UserDAO userDAO = new UserDAO(session);

        userDAO.delete(userId);
        session.close();
    }
}
