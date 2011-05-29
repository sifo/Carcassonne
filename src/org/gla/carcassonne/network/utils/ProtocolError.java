package org.gla.carcassonne.network.utils;

public class ProtocolError extends Exception {
	private static final long serialVersionUID = -5469238512245653623L;
	public ProtocolError(String s) { super(s); }
    public ProtocolError(Throwable e) { super(e); }
    public ProtocolError(String s, Throwable e) { super(s, e); }
}
