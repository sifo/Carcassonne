package org.gla.carcassonne.network.utils;

import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.IOException;

public class MessageList extends MessageUnit {
    private MessageUnit[] value;

    public MessageList(MessageUnit[] v, int n) {
        MessageUnit[] vv = new MessageUnit[n];
        for(int i = 0; i < n; i++)
            vv[i] = v[i];
        value = vv;
    }

    public MessageList(MessageUnit... l) {
        this(l, l.length);
    }

    public int getLength() { return value.length; }

    public MessageUnit getNthValue(int n) throws ProtocolError {
        if(n >= value.length)
            throw new ProtocolError("Pas assez de champs dans un MessageList");
        return value[n];
    }
    
    public void format(OutputStream s) throws IOException {
        s.write('[');
        formatList(s);
        s.write(']');
    }

    public void formatList(OutputStream s) throws IOException{
        for(int i = 0; i < value.length; i++) {
            value[i].format(s);
            if(i < value.length - 1)
                s.write(' ');
        }
    }

    public static MessageUnit parse(PushbackInputStream s)
        throws ProtocolParseError, IOException {
        MessageList l;
        int c;
        c = s.read();
        if(c != '[')
            throw new ProtocolParseError("Ceci n'est pas une liste");

        l = parseList(s);

        skipWhitespace(s);

        c = s.read();
        if(c != ']')
            throw new ProtocolParseError("Liste mal terminée");
        return l;
    }

    public static MessageList parseList(PushbackInputStream s)
        throws ProtocolParseError, IOException {
        int i = 0, c;
        MessageUnit[] v = new MessageUnit[10];

        while(true) {
            skipWhitespace(s);
            c = s.read();
            if(c >= 0)
                s.unread(c);
            if(c < 0 || c == ']' || c == '\n') {
                return new MessageList(v, i);
            }

            MessageUnit u = MessageUnit.parse(s);

            if(i >= v.length) {
                MessageUnit[] vv = new MessageUnit[2 * v.length];
                for(int j = 0; j < i; j++)
                    vv[j] = v[j];
                v = vv;
            }
            v[i++] = u;
        }
    }
}

