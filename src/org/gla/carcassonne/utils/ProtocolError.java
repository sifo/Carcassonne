package org.gla.carcassonne.utils;

public class ProtocolError extends Exception {
    public ProtocolError(String s) { super(s); }
    public ProtocolError(Throwable e) { super(e); }
    public ProtocolError(String s, Throwable e) { super(s, e); }
}
