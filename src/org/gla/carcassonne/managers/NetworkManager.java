package org.gla.carcassonne.managers;

import java.io.IOException;
import java.net.UnknownHostException;

import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.network.CarcassonneClient;
import org.gla.carcassonne.network.CarcassonneServer;

public class NetworkManager {

	private CarcassonneModel model;
	private CarcassonneClient client;
	
	// Servant à titre de tests
	private CarcassonneServer server;
	
	public NetworkManager(CarcassonneModel model) throws IOException, InterruptedException {
		this.model = model;
		server = new CarcassonneServer();
	}
	
	public boolean setConnexion(String host, int port) {
		try {
			client = new CarcassonneClient(host, port, model);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
