package com.ls.soa.game.fantasy.service.rest.controllers;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.models.CategoryDTO;
import com.ls.soa.game.fantasy.api.server.models.CategoryDictionaryDTO;
import com.ls.soa.game.fantasy.api.server.services.ICategoryDictionaryService;
import com.ls.soa.game.fantasy.api.server.services.ICategoryService;
import com.ls.soa.game.fantasy.service.rest.models.CategoryForm;
import com.ls.soa.game.fantasy.service.rest.utils.Secured;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("category-dictionary")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryDictionaryController {
    @EJB(mappedName = "java:global/server/CategoryDictionaryService!com.ls.soa.game.fantasy.api.server.services.ICategoryDictionaryService")
    private ICategoryDictionaryService categoryDictionaryService;

    @GET
    @Secured
    public Response list(@Context SecurityContext securityContext) throws InvalidTokenException {
        String token = securityContext.getUserPrincipal().getName();

        List<CategoryDictionaryDTO> categoryDictionaries = categoryDictionaryService.getAll(token);

        return Response.ok(categoryDictionaries).build();
    }
}
