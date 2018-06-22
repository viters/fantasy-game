package com.ls.soa.game.fantasy.service.ws;

import com.ls.soa.game.fantasy.api.server.exceptions.InvalidTokenException;
import com.ls.soa.game.fantasy.api.server.utils.TokenUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.CloseReason;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class SessionHandler {
    private final Set<Session> sessions = new HashSet<>();
    private TokenUtil tokenUtil = new TokenUtil();

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    public void authorizeSession(String token, Session session) throws IOException {
        if (sessions.contains(session)) {
            try {
                tokenUtil.validateToken(token);
            } catch (InvalidTokenException e) {
                this.removeSession(session);
                session.close(new CloseReason(() -> 1008, "Authorization failed"));
            }
        }
    }

    public void sendToAllSessions(String message) throws IOException {
        for (Session session : sessions) {
            session.getBasicRemote().sendText(message);
        }
    }
}
