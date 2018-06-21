package com.ls.soa.game.fantasy.service.rest.controllers;

import com.ls.soa.game.fantasy.api.server.exceptions.UserAlreadyExistsException;
import com.ls.soa.game.fantasy.api.server.models.UserDTO;
import com.ls.soa.game.fantasy.api.server.services.IAuthService;
import com.ls.soa.game.fantasy.service.rest.models.AuthCredentials;
import com.ls.soa.game.fantasy.service.rest.services.ErrorManager;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController extends Controller {
    @EJB(mappedName = "java:global/server/AuthService!com.ls.soa.game.fantasy.api.server.services.IAuthService")
    private IAuthService authService;

    @POST
    public Response register(AuthCredentials authCredentials) {
        try {
            UserDTO user = this.authService.register(authCredentials.username, authCredentials.password);

            System.out.println("Created user with ID: " + user.getId());

            return Response.status(Response.Status.CREATED).build();
        } catch (UserAlreadyExistsException e) {
            return buildErrorResponse("username-taken");
        }
    }
}
