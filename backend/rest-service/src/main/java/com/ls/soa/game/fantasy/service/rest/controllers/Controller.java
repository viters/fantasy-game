package com.ls.soa.game.fantasy.service.rest.controllers;

import com.ls.soa.game.fantasy.service.rest.services.ErrorManager;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public abstract class Controller {
    @Inject
    ErrorManager errorManager;

    Response buildErrorResponse(String error) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorManager.getErrors().get(error))
                .build();
    }

    Response buildErrorResponse(String error, Response.Status status) {
        return Response.status(status)
                .entity(errorManager.getErrors().get(error))
                .build();
    }
}
