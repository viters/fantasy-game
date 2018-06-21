package com.ls.soa.game.fantasy.service.rest.controllers;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.api.server.exceptions.InsufficientPermissionsException;
import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.models.CategoryDictionaryDTO;
import com.ls.soa.game.fantasy.api.server.models.Role;
import com.ls.soa.game.fantasy.api.server.services.ICategoryDictionaryService;
import com.ls.soa.game.fantasy.service.rest.utils.Secured;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("category-dictionaries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryDictionaryController extends Controller {
    @EJB(mappedName = "java:global/server/CategoryDictionaryService!com.ls.soa.game.fantasy.api.server.services.ICategoryDictionaryService")
    private ICategoryDictionaryService categoryDictionaryService;

    @GET
    @Secured
    public Response list(@Context SecurityContext securityContext) throws InvalidTokenException {
        String token = securityContext.getUserPrincipal().getName();

        List<CategoryDictionaryDTO> categoryDictionaries = categoryDictionaryService.getAll(token);

        return Response.ok(categoryDictionaries).build();
    }

    @POST
    @Secured(role = Role.ADMIN)
    public Response create(CategoryDictionaryDTO categoryDictionaryDTO, @Context SecurityContext securityContext) throws InvalidTokenException, InsufficientPermissionsException {
        String token = securityContext.getUserPrincipal().getName();

        CategoryDictionaryDTO category = categoryDictionaryService.create(token, categoryDictionaryDTO);
        return Response.status(Response.Status.CREATED).entity(category).build();
    }

    @PUT
    @Secured(role = Role.ADMIN)
    public Response update(CategoryDictionaryDTO categoryDictionaryDTO, @Context SecurityContext securityContext) throws InvalidTokenException, InsufficientPermissionsException {
        String token = securityContext.getUserPrincipal().getName();

        try {
            CategoryDictionaryDTO category = categoryDictionaryService.update(token, categoryDictionaryDTO);
            return Response.status(Response.Status.CREATED).entity(category).build();
        } catch (CategoryDictionaryNotFoundException e) {
            return buildErrorResponse("invalid-category-dictionary");
        }
    }

    @DELETE
    @Secured(role = Role.ADMIN)
    @Path("{id}")
    public Response update(@PathParam("id") String id, @Context SecurityContext securityContext) throws InvalidTokenException, InsufficientPermissionsException {
        String token = securityContext.getUserPrincipal().getName();

        try {
            categoryDictionaryService.delete(token, Long.parseLong(id));
            return Response.status(Response.Status.OK).build();
        } catch (CategoryDictionaryNotFoundException e) {
            return buildErrorResponse("invalid-category-dictionary");
        }
    }
}
