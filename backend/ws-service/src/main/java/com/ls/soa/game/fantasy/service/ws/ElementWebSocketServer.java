package com.ls.soa.game.fantasy.service.ws;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ApplicationScoped
@ServerEndpoint("/elements")
public class ElementWebSocketServer {
    @Inject
    private SessionHandler sessionHandler;

    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
    }

    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
    }

    @OnError
    public void onError(Throwable error) {
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException {
        sessionHandler.authorizeSession(message, session);
    }
}
