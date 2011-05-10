package org.gla.carcassonne.utils;

public class ProtocolParseError extends ProtocolError {
    public ProtocolParseError(String s) { super(s); }
    public ProtocolParseError(Throwable e) { super(e); }
    public ProtocolParseError(String s, Throwable e) { super(s, e); }
}
