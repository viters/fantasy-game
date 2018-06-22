package com.ls.soa.game.fantasy.service.ws;

import com.ls.soa.game.fantasy.api.server.services.IMessageBroker;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.IOException;

@Singleton
@Startup
@Remote(com.ls.soa.game.fantasy.api.server.services.IMessageBroker.class)
public class MessageBroker implements IMessageBroker {
    @Inject
    private SessionHandler sessionHandler;

    @Override
    public void publish(String message) throws IOException {
        sessionHandler.sendToAllSessions(message);
    }
}
