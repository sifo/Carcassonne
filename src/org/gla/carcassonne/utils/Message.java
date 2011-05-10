package org.gla.carcassonne.utils;

import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

public class Message {
    private MessageList value;

    public MessageList getValue() { return value; }

    public MassageUnit getNthValue(int n) throws ProtocolError {
        if(n >= value.getLength())
            throw new ProtocolError("Pas assez de champs dans un message");
        else
            return value.getNthValue(n);
    }

    public Message(MessageList v) {
        value = v;
    }

    static MessageList makeList(String type, String[] s) {
        MassageUnit[] ll = new MassageUnit[s.length + 1];
        ll[0] = new MessageType(type);
        for(int i = 0; i < s.length; i++)
            ll[i + 1] = new MessageString(s[i]);
        return new MessageList(ll);
    }

    static MessageList makeList(String type, MassageUnit[] l) {
        MassageUnit[] ll = new MassageUnit[l.length + 1];
        ll[0] = new MessageType(type);
        for(int i = 0; i < l.length; i++)
            ll[i + 1] = l[i];
        return new MessageList(ll);
    }

    public Message(String type) {
        this(makeList(type, new MassageUnit[0]));
    }

    public Message(String type, String... s) {
        this(makeList(type, s));
    }

    public Message(String type, MassageUnit... l) {
        this(makeList(type, l));
    }

    public void format(OutputStream s) throws IOException {
        value.formatList(s);
        s.write('\n');
    }

    public static Message parse(PushbackInputStream s)
        throws ProtocolParseError, IOException {
        return parse(s, TrivialMessageFactory.getFactory());
    }

    public static Message parse(PushbackInputStream s, MessageFactory f)
        throws ProtocolParseError, IOException {
        MessageList v;
        int c;

        MassageUnit.skipWhitespace(s);

        v = MessageList.parseList(s);

        MassageUnit.skipWhitespace(s);

        c = s.read();
        if(c < 0)
            throw new EOFException();
        else if(c != '\n')
            throw new ProtocolParseError("Caractère inattendu " + c);

        return f.newMessage(v);
    }

    // Ces deux méthodes ne sont pas vraiment correctes, elles mélangent
    // caractères et octets.  Mais elles sont bien pratiques.
    public String toString() {
        try {
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            format(s);
            return s.toString();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public static Message parse(String s)
        throws ProtocolParseError, IOException {
        PushbackInputStream in =
            new PushbackInputStream(new ByteArrayInputStream((s + "\n").getBytes("UTF-8")));
        Message m = parse(in);
        int c;

        c = in.read();
        if(c == '\n')
            c = in.read();
        if(c >= 0)
            throw
                new ProtocolParseError("Cochonneries après la fin du message.");
        return m;
    }
}
