package org.gla.carcassonne.network.utils;

public class ProtocolParseError extends ProtocolError {
	private static final long serialVersionUID = 955418105562993107L;
	public ProtocolParseError(String s) { super(s); }
    public ProtocolParseError(Throwable e) { super(e); }
    public ProtocolParseError(String s, Throwable e) { super(s, e); }
}
