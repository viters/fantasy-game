package com.ls.soa.game.fantasy.service.rest.controllers;

import com.ls.soa.game.fantasy.api.server.exceptions.InvalidElementParamException;
import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.models.ElementDTO;
import com.ls.soa.game.fantasy.api.server.services.IElementService;
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

@Path("rankings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RankingController extends Controller {
    @EJB(mappedName = "java:global/server/ElementService!com.ls.soa.game.fantasy.api.server.services.IElementService")
    private IElementService elementService;

    @GET
    @Secured
    @Path("elements/{paramName}")
    public Response list(@PathParam("paramName") String paramName, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws InvalidTokenException {
        String token = securityContext.getUserPrincipal().getName();

        try {
            List<ElementDTO> elements = elementService.getTopForParamByCategoryDictionary(token, paramName, 3);
            return Response.ok(elements).build();
        } catch (InvalidElementParamException e) {
            return buildErrorResponse(ErrorType.NO_SUCH_ELEMENT_PARAM, request.getLocale());
        }

    }
}
