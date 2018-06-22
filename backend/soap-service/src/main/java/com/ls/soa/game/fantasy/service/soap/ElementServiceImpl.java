package com.ls.soa.game.fantasy.service.soap;

import com.ls.soa.game.fantasy.api.server.exceptions.*;
import com.ls.soa.game.fantasy.api.server.models.ElementDTO;
import com.ls.soa.game.fantasy.api.server.services.IElementService;

import javax.ejb.EJB;
import javax.jws.WebService;

@WebService(endpointInterface = "com.ls.soa.game.fantasy.service.soap.ElementService")
public class ElementServiceImpl implements ElementService {
    @EJB(mappedName = "java:global/server/ElementService!com.ls.soa.game.fantasy.api.server.services.IElementService")
    private IElementService elementService;

    @Override
    public ElementDTO update(String token, ElementDTO elementDTO) throws ElementNotFoundException, InsufficientPermissionsException, CategoryDictionaryNotFoundException, UserNotFoundException, InvalidTokenException {
        return this.elementService.update(token, elementDTO);
    }
}
