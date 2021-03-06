package com.ls.soa.game.fantasy.service.rest.controllers;

import com.ls.soa.game.fantasy.api.server.exceptions.*;
import com.ls.soa.game.fantasy.api.server.models.CategoryDTO;
import com.ls.soa.game.fantasy.api.server.services.ICategoryService;
import com.ls.soa.game.fantasy.service.rest.models.ErrorType;
import com.ls.soa.game.fantasy.service.rest.utils.Secured;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryController extends Controller {
    @EJB(mappedName = "java:global/server/CategoryService!com.ls.soa.game.fantasy.api.server.services.ICategoryService")
    private ICategoryService categoryService;

    @GET
    @Secured
    public Response list(@Context SecurityContext securityContext) throws InvalidTokenException, UserNotFoundException {
        String token = securityContext.getUserPrincipal().getName();

        List<CategoryDTO> categories = categoryService.getAllForUser(token);

        return Response.ok(categories).build();
    }

    @GET
    @Secured
    @Path("{id}")
    public Response get(@PathParam("id") String id, @Context SecurityContext securityContext) throws InvalidTokenException, InsufficientPermissionsException, CategoryNotFoundException {
        String token = securityContext.getUserPrincipal().getName();

        CategoryDTO categoryDTO = categoryService.get(token, Long.parseLong(id));

        return Response.ok(categoryDTO).build();
    }

    @GET
    @Secured
    @Path("{id}/elements")
    public Response elementsByCategory(@PathParam("id") String id, @Context SecurityContext securityContext) throws InvalidTokenException, InsufficientPermissionsException, CategoryNotFoundException {
        String token = securityContext.getUserPrincipal().getName();

        CategoryDTO categoryDTO = categoryService.get(token, Long.parseLong(id));

        return Response.ok(categoryDTO.getElementDTOList()).build();
    }

    @POST
    @Secured
    public Response create(CategoryDTO categoryDTO, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws UserNotFoundException, InvalidTokenException, InsufficientPermissionsException {
        String token = securityContext.getUserPrincipal().getName();

        try {
            CategoryDTO category = categoryService.create(token, categoryDTO);
            return Response.status(Response.Status.CREATED).entity(category).build();
        } catch (CategoryDictionaryNotFoundException e) {
            return buildErrorResponse(ErrorType.NO_SUCH_CATEGORY_DICTIONARY, request.getLocale());
        }
    }

    @PUT
    @Secured
    public Response update(CategoryDTO categoryDTO, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws InvalidTokenException, CategoryNotFoundException, InsufficientPermissionsException, UserNotFoundException {
        String token = securityContext.getUserPrincipal().getName();

        try {
            CategoryDTO category = categoryService.update(token, categoryDTO);
            return Response.status(Response.Status.CREATED).entity(category).build();
        } catch (CategoryDictionaryNotFoundException e) {
            return buildErrorResponse(ErrorType.NO_SUCH_CATEGORY_DICTIONARY, request.getLocale());
        }
    }

    @DELETE
    @Secured
    @Path("{id}")
    public Response update(@PathParam("id") String id, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws InvalidTokenException, CategoryNotFoundException, InsufficientPermissionsException {
        String token = securityContext.getUserPrincipal().getName();

        try {
            categoryService.delete(token, Long.parseLong(id));
            return Response.status(Response.Status.OK).build();
        } catch (CategoryNotFoundException e) {
            return buildErrorResponse(ErrorType.NO_SUCH_CATEGORY, request.getLocale());
        }
    }
}
