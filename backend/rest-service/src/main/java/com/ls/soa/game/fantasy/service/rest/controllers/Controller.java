package com.ls.soa.game.fantasy.service.rest.controllers;

import com.ls.soa.game.fantasy.service.rest.models.ErrorType;
import com.ls.soa.game.fantasy.service.rest.services.ErrorManager;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Locale;

abstract class Controller {
    @Inject
    private ErrorManager errorManager;

    Response buildErrorResponse(ErrorType error) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorManager.getErrorsForLang("en").get(error))
                .build();
    }

    Response buildErrorResponse(ErrorType error, String lang) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorManager.getErrorsForLang(lang).get(error))
                .build();
    }

    Response buildErrorResponse(ErrorType error, Locale locale) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorManager.getErrorsForLang(locale.getLanguage()).get(error))
                .build();
    }

    Response buildErrorResponse(ErrorType error, String lang, Response.Status status) {
        return Response.status(status)
                .entity(errorManager.getErrorsForLang(lang).get(error))
                .build();
    }
}
