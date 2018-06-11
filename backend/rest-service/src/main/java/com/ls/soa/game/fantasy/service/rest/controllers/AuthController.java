package com.ls.soa.game.fantasy.service.rest.controllers;

import com.ls.soa.game.fantasy.api.server.exceptions.IncorrectPasswordException;
import com.ls.soa.game.fantasy.api.server.exceptions.UserNotFoundException;
import com.ls.soa.game.fantasy.api.server.services.IAuthService;
import com.ls.soa.game.fantasy.service.rest.models.AuthCredentials;
import com.ls.soa.game.fantasy.service.rest.models.TokenCredentials;
import com.ls.soa.game.fantasy.service.rest.services.ErrorManager;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("session")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {
    @EJB(mappedName = "java:global/server/AuthService!com.ls.soa.game.fantasy.api.server.services.IAuthService")
    private IAuthService authService;

    @Inject
    private ErrorManager errorManager;

    @POST
    public Response login(AuthCredentials authCredentials) {
        try {
            String token = authService.login(authCredentials.getUsername(), authCredentials.getPassword());
            TokenCredentials tokenCredentials = new TokenCredentials(token);

            return Response.ok(tokenCredentials).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorManager.getErrors().get("username-invalid"))
                    .build();
        } catch (IncorrectPasswordException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorManager.getErrors().get("password-invalid"))
                    .build();
        }
    }
}
