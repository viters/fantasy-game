package com.ls.soa.game.fantasy.server.services;

import com.ls.soa.game.fantasy.api.server.exceptions.*;
import com.ls.soa.game.fantasy.api.server.models.*;
import com.ls.soa.game.fantasy.api.server.services.IElementService;
import com.ls.soa.game.fantasy.server.daos.CategoryDictionaryDAO;
import com.ls.soa.game.fantasy.server.daos.ElementDAO;
import com.ls.soa.game.fantasy.server.models.CategoryDictionary;
import com.ls.soa.game.fantasy.server.models.Element;
import org.hibernate.Session;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Remote(com.ls.soa.game.fantasy.api.server.services.IElementService.class)
public class ElementService extends Service implements IElementService {
    @Override
    public ElementDTO create(String token, ElementDTO elementDTO) throws UserNotFoundException, CategoryDictionaryNotFoundException, InvalidTokenException, InsufficientPermissionsException {
        TokenMetadataDTO metadata = tokenUtil.validateToken(token);

        Element element = map(elementDTO, Element.class);
        if (element.getAuthorId() != null && element.getAuthorId() != 0
                && element.getAuthorId() != metadata.getUserId() && !metadata.isAdmin()) {
            throw new InsufficientPermissionsException();
        } else {
            element.setAuthorId(metadata.getUserId());
        }

        Session session = dbConnectionUtil.createSession();
        ElementDAO elementDAO = new ElementDAO(session);

        elementDAO.createOrUpdate(element);

        ElementDTO result = map(element, ElementDTO.class);
        session.close();
        return result;
    }

    @Override
    public List<ElementDTO> getAllForUser(String token) throws InvalidTokenException, UserNotFoundException {
        TokenMetadataDTO metadata = tokenUtil.validateToken(token);
        Session session = dbConnectionUtil.createSession();
        ElementDAO elementDAO = new ElementDAO(session);

        List<Element> elements = elementDAO.getAllForUser(metadata.getUserId());

        List<ElementDTO> elementDTOS = elements.stream().map(c -> map(c, ElementDTO.class)).collect(Collectors.toList());
        session.close();
        return elementDTOS;
    }

    @Override
    public List<ElementDTO> getAll(String token) throws InsufficientPermissionsException, InvalidTokenException {
        if (!tokenUtil.validateToken(token).isAdmin()) {
            throw new InsufficientPermissionsException();
        }

        Session session = dbConnectionUtil.createSession();
        ElementDAO elementDAO = new ElementDAO(session);

        List<Element> elements = elementDAO.getAll();

        List<ElementDTO> elementDTOS = elements.stream().map(c -> map(c, ElementDTO.class)).collect(Collectors.toList());
        session.close();
        return elementDTOS;
    }

    @Override
    public List<ElementDTO> getTopForParamByCategoryDictionary(String token, String paramName, int limit) throws InvalidTokenException, InvalidElementParamException {
        tokenUtil.validateToken(token);

        Session session = dbConnectionUtil.createSession();
        ElementDAO elementDAO = new ElementDAO(session);
        CategoryDictionaryDAO categoryDictionaryDAO = new CategoryDictionaryDAO(session);

        List<CategoryDictionary> categoryDictionaries = categoryDictionaryDAO.getAll();
        List<Element> elements = new ArrayList<>();

        for (CategoryDictionary categoryDictionary : categoryDictionaries) {
            elements.addAll(elementDAO.getTopForParamByCategoryDictionary(paramName, categoryDictionary.getId(), limit));
        }

        List<ElementDTO> elementDTOS = elements.stream().map(c -> map(c, ElementDTO.class)).collect(Collectors.toList());

        for (int i = 0; i < elements.size(); i++) {
            elementDTOS.get(i).setAuthorDTO(map(elements.get(i).getAuthor(), UserDTO.class));
            elementDTOS.get(i).setCategoryDictionaryDTO(map(elements.get(i).getCategoryDictionary(), CategoryDictionaryDTO.class));
            elementDTOS.get(i).setCategoryDTO(map(elements.get(i).getCategory(), CategoryDTO.class));
        }

        session.close();
        return elementDTOS;
    }

    @Override
    public ElementDTO update(String token, ElementDTO elementDTO) throws InsufficientPermissionsException, ElementNotFoundException, InvalidTokenException, UserNotFoundException, CategoryDictionaryNotFoundException {
        TokenMetadataDTO metadata = tokenUtil.validateToken(token);

        Session session = dbConnectionUtil.createSession();
        ElementDAO elementDAO = new ElementDAO(session);

        Element newElement = map(elementDTO, Element.class);

        Optional<Element> oldElement = elementDAO.findById(newElement.getId());

        if (!oldElement.isPresent()) {
            session.close();
            throw new ElementNotFoundException();
        }

        Element entity = oldElement.get();

        if (!(metadata.isAdmin() || entity.getAuthorId() == metadata.getUserId())) {
            throw new InsufficientPermissionsException();
        }

        entity.merge(newElement);

        elementDAO.createOrUpdate(entity);

        ElementDTO dto = map(entity, ElementDTO.class);
        session.close();
        return dto;
    }

    @Override
    public void delete(String token, long elementId) throws InsufficientPermissionsException, ElementNotFoundException, InvalidTokenException {
        TokenMetadataDTO metadata = tokenUtil.validateToken(token);

        Session session = dbConnectionUtil.createSession();
        ElementDAO elementDAO = new ElementDAO(session);

        Optional<Element> element = elementDAO.findById(elementId);

        if (!element.isPresent()) {
            session.close();
            throw new ElementNotFoundException();
        }

        if (!(metadata.isAdmin() || element.get().getAuthor().getId() == metadata.getUserId())) {
            session.close();
            throw new InsufficientPermissionsException();
        }

        elementDAO.delete(element.get());
        session.close();
    }
}
