package com.ls.soa.game.fantasy.service.rest.controllers;

import com.ls.soa.game.fantasy.api.server.exceptions.IncorrectPasswordException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.services.IAuthService;
import com.ls.soa.game.fantasy.service.rest.models.AuthCredentials;
import com.ls.soa.game.fantasy.service.rest.models.ErrorType;
import com.ls.soa.game.fantasy.service.rest.models.TokenCredentials;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sessions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController extends Controller {
    @EJB(mappedName = "java:global/server/AuthService!com.ls.soa.game.fantasy.api.server.services.IAuthService")
    private IAuthService authService;

    @POST
    public Response login(AuthCredentials authCredentials, @Context HttpServletRequest request) {
        try {
            String token = authService.login(authCredentials.username, authCredentials.password);
            TokenCredentials tokenCredentials = new TokenCredentials(token);

            return Response.ok(tokenCredentials).build();
        } catch (UserNotFoundException e) {
            return buildErrorResponse(ErrorType.USERNAME_INVALID, request.getLocale());
        } catch (IncorrectPasswordException e) {
            return buildErrorResponse(ErrorType.PASSWORD_INVALID, request.getLocale());
        }
    }
}
