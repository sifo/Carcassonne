package org.gla.carcassonne.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class CarcassonneClient extends Socket {

	ClientFactory c;
	
	public CarcassonneClient(String host, int port, ClientFactory c)
	throws UnknownHostException, IOException {
		super(host, port);
		
		this.c = c;
	}
}
