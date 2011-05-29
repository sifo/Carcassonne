package org.gla.carcassonne.network.utils;

public class TrivialMessageFactory implements MessageFactory {
    private static MessageFactory singleton = null;

    public Message newMessage(MessageList v) {
        return new Message(v);
    }

    public static MessageFactory getFactory() {
        if(singleton == null)
            singleton = new TrivialMessageFactory();
        return singleton;
    }
}

