package com.ls.soa.game.fantasy.service.rest.controllers;

import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.api.server.models.UserDTO;
import com.ls.soa.game.fantasy.api.server.services.IAuthService;
import com.ls.soa.game.fantasy.service.rest.models.AuthCredentials;
import com.ls.soa.game.fantasy.service.rest.models.ErrorType;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController extends Controller {
    @EJB(mappedName = "java:global/server/AuthService!com.ls.soa.game.fantasy.api.server.services.IAuthService")
    private IAuthService authService;

    @POST
    public Response register(AuthCredentials authCredentials, @Context HttpServletRequest request) {
        try {
            UserDTO user = this.authService.register(authCredentials.username, authCredentials.password);

            return Response.status(Response.Status.CREATED).build();
        } catch (UserAlreadyExistsException e) {
            return buildErrorResponse(ErrorType.USERNAME_TAKEN, request.getLocale());
        }
    }
}
