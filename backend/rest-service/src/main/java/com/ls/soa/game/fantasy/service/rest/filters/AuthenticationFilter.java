package com.ls.soa.game.fantasy.service.rest.filters;

import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.models.Role;
import com.ls.soa.game.fantasy.api.server.models.TokenMetadataDTO;
import com.ls.soa.game.fantasy.api.server.services.IAuthService;
import com.ls.soa.game.fantasy.service.rest.models.ErrorResponse;
import com.ls.soa.game.fantasy.service.rest.utils.Secured;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

@Provider
@Secured
public class AuthenticationFilter implements ContainerRequestFilter {
    @EJB(mappedName = "java:global/server/AuthService!com.ls.soa.game.fantasy.api.server.services.IAuthService")
    private IAuthService authService;

    @Context
    private ResourceInfo resourceInfo;

    @Context
    private UriInfo uriInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authHeader = containerRequestContext.getHeaderString("Authorization");

        if (authHeader != null) {
            String token = authHeader.substring("Bearer ".length()).trim();
            try {
                TokenMetadataDTO metadata = authService.validateToken(token);

                Secured secured = resourceInfo.getResourceMethod().getAnnotation(Secured.class);
                if (secured.role().equals(Role.ADMIN) && !metadata.isAdmin()) {
                    Response unauthorized = Response.status(Response.Status.UNAUTHORIZED)
                            .entity(new ErrorResponse("unauthorized", "Unauthorized to perform action"))
                            .build();
                    containerRequestContext.abortWith(unauthorized);
                }

                containerRequestContext.setSecurityContext(new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return () -> token;
                    }

                    @Override
                    public boolean isUserInRole(String s) {
                        return metadata.getRole().equals(s);
                    }

                    @Override
                    public boolean isSecure() {
                        return uriInfo.getAbsolutePath().toString().startsWith("https");
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        return "JWT";
                    }
                });
            } catch (InvalidTokenException e) {
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } else {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
