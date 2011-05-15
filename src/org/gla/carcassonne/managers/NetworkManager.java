package org.gla.carcassonne.managers;

import java.awt.Color;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Set;

import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.entities.Player;
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
			// IP distant : 78.232.249.59
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
		int clientCounter = 0;
		
		while(!client.hasStarted()) {
			Set<Integer> set = (Set<Integer>) client.getPlayers();

			if (set.size() > clientCounter) {
				clientCounter++;
				lobby.getPlayersList()[clientCounter-1].setText("Joueur "+clientCounter);
				
				Player p = new Player("Joueur"+clientCounter, "blue", Color.blue);
				model.getPlayerManager().getPlayers().add(p);
			}
		}
		
		lobby.dispose();
		
		while(!client.isFinished()) {
			if (client.getToken() > 0) {
				System.out.println("TOKEN : "+client.getToken());
				client.move(null, null, null, null, null);
			}
		}
	}
	
	public Client getClient() {
		return client;
	}
}
