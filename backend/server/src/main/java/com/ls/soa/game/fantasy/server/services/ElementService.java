package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.ElementNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.models.ElementDTO;
import com.ls.soa.game.fantasy.api.server.services.IElementService;
import com.ls.soa.game.fantasy.server.daos.ElementDAO;
import com.ls.soa.game.fantasy.server.models.Element;
import com.ls.soa.game.fantasy.server.models.TokenMetadata;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Remote(com.ls.soa.game.fantasy.api.server.services.IElementService.class)
public class ElementService extends Service implements IElementService {
    @Inject
    private ElementDAO elementDAO;

    @Override
    public ElementDTO create(String param1, int param2, int param3, int param4, int categoryId, String token) throws UserNotFoundException, CategoryDictionaryNotFoundException {
        TokenMetadata metadata = tokenUtil.validateToken(token);

        Element element = new Element(param1, param2, param3, param4);
        elementDAO.create(element, categoryId, metadata.getUserId());

        return map(element, ElementDTO.class);
    }

    @Override
    public List<ElementDTO> getAllForUser(String token) throws UserNotFoundException {
        TokenMetadata metadata = tokenUtil.validateToken(token);
        List<Element> elements = elementDAO.getAllForUser(metadata.getUserId());

        return elements.stream().map(c -> map(c, ElementDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ElementDTO> getAll(String token) throws InsufficientPermissionsException {
        if (!tokenUtil.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        List<Element> elements = elementDAO.getAll();

        return elements.stream().map(c -> map(c, ElementDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ElementDTO update(String token, ElementDTO elementDTO) throws InsufficientPermissionsException, ElementNotFoundException {
        TokenMetadata metadata = tokenUtil.validateToken(token);

        if (!(metadata.isAdmin() || elementDTO.getAuthor().getId() == metadata.getUserId())) {
            throw new InsufficientPermissionsException();
        }

        Element element = map(elementDTO, Element.class);
        elementDAO.update(element);

        return map(element, ElementDTO.class);
    }

    @Override
    public void delete(String token, long elementId) throws InsufficientPermissionsException, ElementNotFoundException {
        TokenMetadata metadata = tokenUtil.validateToken(token);

        Optional<Element> element = elementDAO.findById(elementId);

        if (!element.isPresent()) {
            throw new ElementNotFoundException();
        }

        if (!(metadata.isAdmin() || element.get().getAuthor().getId() == metadata.getUserId())) {
            throw new InsufficientPermissionsException();
        }

        elementDAO.delete(element.get());
    }
}
