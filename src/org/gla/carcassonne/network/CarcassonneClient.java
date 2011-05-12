package org.gla.carcassonne.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class CarcassonneClient extends Socket {

	
	public CarcassonneClient(String host, int port)
			throws UnknownHostException, IOException {
		super(host, port);
	}
}
