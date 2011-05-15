package org.gla.carcassonne.managers;

import java.io.IOException;
import java.net.UnknownHostException;

import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.network.CarcassonneClient;
import org.gla.carcassonne.network.CarcassonneServer;
import org.gla.carcassonne.network.Client;
import org.gla.carcassonne.ui.MultiplayerLobbyDialog;

public class NetworkManager extends Thread {

	private CarcassonneModel model;
	private Client client;
	private MultiplayerLobbyDialog lobby;
	
	// Servant à titre de tests
	private CarcassonneServer server;
	
	public NetworkManager(CarcassonneModel model) throws IOException, InterruptedException {
		this.model = model;
		server = new CarcassonneServer();
		server.start();
	}
	
	public boolean setConnexion(String host, int port, MultiplayerLobbyDialog lobby) {
		try {
			this.lobby = lobby;
			client = new Client(host, port, new CarcassonneClient());
			client.start();
			this.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void run() {
		while(!client.hasStarted()) {
			System.out.println("test");
		}
		
		lobby.dispose();
	}
	
	public Client getClient() {
		return client;
	}
}
