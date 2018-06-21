package com.ls.soa.game.fantasy.service.rest.controllers;

import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.models.CategoryDictionaryDTO;
import com.ls.soa.game.fantasy.api.server.services.ICategoryDictionaryService;
import com.ls.soa.game.fantasy.service.rest.utils.Secured;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("category-dictionaries")
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
