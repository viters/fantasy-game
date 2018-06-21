package com.ls.soa.game.fantasy.service.rest.controllers;

import com.ls.soa.game.fantasy.api.server.exceptions.*;
import com.ls.soa.game.fantasy.api.server.models.ElementDTO;
import com.ls.soa.game.fantasy.api.server.services.IElementService;
import com.ls.soa.game.fantasy.service.rest.utils.Secured;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("elements")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ElementController extends Controller {
    @EJB(mappedName = "java:global/server/ElementService!com.ls.soa.game.fantasy.api.server.services.IElementService")
    private IElementService elementService;

    @GET
    @Secured
    public Response list(@Context SecurityContext securityContext) throws InvalidTokenException, UserNotFoundException {
        String token = securityContext.getUserPrincipal().getName();

        List<ElementDTO> elements = elementService.getAllForUser(token);

        return Response.ok(elements).build();
    }

    @POST
    @Secured
    public Response create(ElementDTO elementDTO, @Context SecurityContext securityContext) throws UserNotFoundException, InvalidTokenException, InsufficientPermissionsException {
        String token = securityContext.getUserPrincipal().getName();

        try {
            ElementDTO element = elementService.create(token, elementDTO);
            return Response.status(Response.Status.CREATED).entity(element).build();
        } catch (CategoryDictionaryNotFoundException e) {
            return buildErrorResponse("invalid-element-dictionary");
        }
    }

    @PUT
    @Secured
    public Response update(ElementDTO elementDTO, @Context SecurityContext securityContext) throws InvalidTokenException, InsufficientPermissionsException, UserNotFoundException, ElementNotFoundException {
        String token = securityContext.getUserPrincipal().getName();

        try {
            ElementDTO element = elementService.update(token, elementDTO);
            return Response.status(Response.Status.CREATED).entity(element).build();
        } catch (CategoryDictionaryNotFoundException e) {
            return buildErrorResponse("invalid-element-dictionary");
        }
    }

    @DELETE
    @Secured
    @Path("{id}")
    public Response update(@PathParam("id") String id, @Context SecurityContext securityContext) throws InvalidTokenException, InsufficientPermissionsException {
        String token = securityContext.getUserPrincipal().getName();

        try {
            elementService.delete(token, Long.parseLong(id));
            return Response.status(Response.Status.OK).build();
        } catch (ElementNotFoundException e) {
            return buildErrorResponse("invalid-element");
        }
    }
}