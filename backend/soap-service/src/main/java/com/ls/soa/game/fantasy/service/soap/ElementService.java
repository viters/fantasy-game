package com.ls.soa.game.fantasy.service.soap;

import com.ls.soa.game.fantasy.api.server.exceptions.*;
import com.ls.soa.game.fantasy.api.server.models.ElementDTO;

import javax.jws.WebService;

@WebService
public interface ElementService {
    ElementDTO update(String token, ElementDTO elementDTO) throws ElementNotFoundException, InsufficientPermissionsException, CategoryDictionaryNotFoundException, UserNotFoundException, InvalidTokenException;
}
