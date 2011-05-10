package org.gla.carcassonne.utils;

import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.EOFException;

public abstract class MassageUnit {
    public abstract void format(OutputStream s) throws IOException;

    public String toString() {
        ByteArrayOutputStream s = new ByteArrayOutputStream();
        try {
            format(s);
            return s.toString("UTF-8");
        } catch(IOException e) {
            throw new Error(e);
        }
    }

    // Toutes ces méthodes sont surchargées par les sous-classes
    // correspondantes.  Mettre des versions ici, plutôt que les rendre
    // abstraites, évite de faire des casts contravariants un peu partout.

    public String getStringValue() throws ProtocolError {
        throw new ProtocolError("Ceci n'est pas une chaîne : " + this);
    }

    public String getTypeValue() throws ProtocolError {
        throw new ProtocolError("Ceci n'est pas un type : " + this);
    }

    public byte[] getStringBytes() throws ProtocolError {
        throw new ProtocolError("Ceci n'est pas une chaîne : " + this);
    }

    public int getIntValue() throws ProtocolError {
        throw new ProtocolError("Ceci n'est pas un entier : " + this);
    }

    public MassageUnit getNthValue(int i) throws ProtocolError {
        throw new ProtocolError("Ceci n'est pas une liste : " + this);
    }

    public int getLength() throws ProtocolError {
        throw new ProtocolError("Ceci n'est pas une liste : " + this);
    }

    public static MassageUnit parse(PushbackInputStream s)
        throws ProtocolParseError, IOException {
        MassageUnit l;
        int c;
        c = s.read();

        if(c < 0)
            throw new EOFException();

        if(c >= 0) s.unread(c);

        if(c == '[') {
            l = MessageList.parse(s);
        } else if(c == '(') {
            l = MessageString.parse(s);
        } else if(c >= '0' && c <= '9') {
            l = MessageInt.parse(s);
        } else if(c == '_' || (c >= 'A' && c <= 'Z')) {
            l = MessageType.parse(s);
        } else {
            throw new ProtocolParseError("Caractère inconnu " + (char)c + 
                                         " (" + (int)c + ")");
        }
        return l;
    }

    // Moralement, c'est une méthode privée.  Je ne peux pas la déclarer
    // protected, car elle est utilisée dans la classe Message, qui n'est
    // pas une sous-classe.

    static void skipWhitespace(PushbackInputStream s) throws IOException {
        int c;
        do {
            c = s.read();
        } while(c == ' ' || c == '\r' || c == '\t');

        if(c >= 0) s.unread(c);
    }
}
