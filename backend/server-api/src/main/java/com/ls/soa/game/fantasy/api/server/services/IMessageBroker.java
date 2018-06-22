package com.ls.soa.game.fantasy.api.server.services;

import java.io.IOException;

public interface IMessageBroker {
    void publish(String message) throws IOException;
}
