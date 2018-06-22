package com.ls.soa.game.fantasy.service.soap;

import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.models.CategoryDictionaryDTO;
import com.ls.soa.game.fantasy.api.server.services.ICategoryDictionaryService;

import javax.ejb.EJB;
import javax.jws.WebService;

@WebService(endpointInterface = "com.ls.soa.game.fantasy.service.soap.CategoryDictionaryService")
public class CategoryDictionaryServiceImpl implements CategoryDictionaryService {
    @EJB(mappedName = "java:global/server/CategoryDictionaryService!com.ls.soa.game.fantasy.api.server.services.ICategoryDictionaryService")
    private ICategoryDictionaryService categoryDictionaryService;

    @Override
    public CategoryDictionaryDTO create(String token, CategoryDictionaryDTO categoryDictionaryDTO) throws InsufficientPermissionsException, InvalidTokenException {
        return categoryDictionaryService.create(token, categoryDictionaryDTO);
    }
}
