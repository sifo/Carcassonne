package org.gla.carcassonne.utils;

import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.IOException;
import java.io.EOFException;

public class MessageType extends MassageUnit {
    private String value;

    public String getTypeValue() { return value; }

    public MessageType(String v) {
        value = v;
    }

    public void format(OutputStream s) throws IOException {
        s.write(value.getBytes("UTF-8"));
    }

    public static MassageUnit parse(PushbackInputStream s)
        throws ProtocolParseError, IOException {

        int c;
        StringBuffer sb = new StringBuffer();

        c = s.read();
        if (c < 0)
            throw new EOFException();

        if(c != '_' && (c < 'A' || c > 'Z'))
            throw new ProtocolParseError("Ceci n'est pas un type");

        while(true) {
            sb.append((char)c);
            c = s.read();
            if (c < 0)
                throw new EOFException();
            if (c != '_' && (c < '0' || c > '9') && (c < 'A' || c > 'Z')) {
                s.unread(c);
                return new MessageType(sb.toString());
            }
        }
    }
}
