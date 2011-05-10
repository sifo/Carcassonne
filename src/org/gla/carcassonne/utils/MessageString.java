package org.gla.carcassonne.utils;

import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.UnsupportedEncodingException;

public class MessageString extends MassageUnit {
    private byte[] value;

    public MessageString(byte[] v, int offset, int length) {
        value = new byte[length];
        System.arraycopy(v, offset, value, 0, length);
    }

    public MessageString(byte[] v, int length) {
        this(v, 0, length);
    }

    public MessageString(byte[] v) {
        this(v, 0, v.length);
    }

    private static byte[] jeDetesteJava(String s) {
        try {
            return s.getBytes("UTF-8");
        } catch(UnsupportedEncodingException e) {
            throw new Error(e);
        }
    }

    public MessageString(String s) {
        this(jeDetesteJava(s));
    }

    public String getStringValue() throws ProtocolError {
        try {
            return new String(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }
    }

    public byte[] getStringBytes() throws ProtocolError {
        return value;
    }
        
    public void format(OutputStream s) throws IOException {
        s.write('(');
        for(int i = 0; i < value.length; i++) {
            byte c = value[i];
            if((c >= 0 && c < 0x20) || c == '(' || c == ')' || c == '\\')
                s.write('\\');
            s.write(c);
        }
        s.write(')');
    }

    public static MassageUnit parse(PushbackInputStream s)
        throws ProtocolParseError, IOException {
        byte[] v;
        int len = 0;

        if(s.read() != '(')
            throw new ProtocolParseError("Ceci n'est pas une chaîne");
        
        v = new byte[20];

        while(true) {
            int c = s.read();
            if(c == ')')
                return new MessageString(v, len);
            if(c == '\\')
                c = s.read();
            if(c < 0)
                throw new EOFException();

            if(len >= v.length) {
                byte[] newv;
                newv = new byte[v.length * 2];
                System.arraycopy(v, 0, newv, 0, len);
                v = newv;
            }

            v[len++] = (byte)c;
        }
    }
}
