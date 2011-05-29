package org.gla.carcassonne.network.utils;

import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.IOException;

public class MessageInt extends MessageUnit {
    private int value;

    public MessageInt(int v) {
        value = v;
    }

    public MessageInt(long v) {
        if(v > Integer.MAX_VALUE || v < Integer.MIN_VALUE)
            throw new Error("Trop grand !");
        value = (int)v;
    }

    public int getIntValue() { return value; }
    
    public void setIntValue(int value) {
    	this.value = value;
    }

    public void format(OutputStream s) throws IOException {
        s.write(("" + value).getBytes("UTF-8"));
    }

    public static MessageUnit parse(PushbackInputStream s)
        throws ProtocolParseError, IOException {
        int c, n = 0;

        c = s.read();
        if(c < '0' && c > '9')
            throw new ProtocolParseError("Ceci n'est pas un entier");

        do {
            n *= 10;
            n += c - '0';
            c = s.read();
        } while (c >= '0' && c <= '9');

        if(c >= 0)
            s.unread(c);
        return new MessageInt(n);
    }
}
