package org.gla.carcassonne.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.gla.carcassonne.utils.Message;
import org.gla.carcassonne.utils.ProtocolError;

/**
 * Le serveur écoute pendant 500ms, s'arrête pendant 500ms et réécoute
 * à nouveau. Le but est de régulièrement donner la main aux autres threads.
 * Cela dit, ce comportement pourrait bien changer.
 */
public class CarcassonneServer {
	
	private ServerSocket serverSocket;
	private boolean isListening;
	private List<CarcassonneThreadServer> clients;
	
	private int token;		// nombre généré aléatoire pour désigner quel client a la main

	private final static String CONNECTION_ACCEPTED = "Connexion acceptée : ";
	private final static int NUMBER_MAX_OF_CLIENTS = 5;
	private final static int LISTENING_PORT = 54322;
	private final static int SERVER_TIME_OUT = 500;

	public CarcassonneServer() throws IOException, InterruptedException {
		isListening = false;
		serverSocket = new ServerSocket(LISTENING_PORT);
		clients = new ArrayList<CarcassonneThreadServer>();
		serverSocket.setSoTimeout(SERVER_TIME_OUT);
	}

	public void startServer() throws IOException, InterruptedException {
		isListening = true;
		while (isListening) {
			if (clients.size() < NUMBER_MAX_OF_CLIENTS) {
				try {
					Socket clientSocket = serverSocket.accept();
					CarcassonneThreadServer client = 
						new CarcassonneThreadServer(clientSocket, this);
					clients.add(client);
					System.out.println(CONNECTION_ACCEPTED
							+ clientSocket.getInetAddress().getHostName()+ ":"
							+ clientSocket.getPort());
				}
				catch (SocketTimeoutException e) {
				}
				Thread.sleep(500);
			}
		}
		serverSocket.close();
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public boolean isListening() {
		return isListening;
	}

	public void setListening(boolean isListening) {
		this.isListening = isListening;
	}

	public List<CarcassonneThreadServer> getClients() {
		return clients;
	}

	public void setClients(List<CarcassonneThreadServer> clients) {
		this.clients = clients;
	}
	
	public void setToken(int token) {
		this.token = token;
	}

	public int getToken() {
		return token;
	}
	
	/*
	 * Renvoi vrai si le client est retiré de la liste, faux sinon (échec ou non existance)
	 */
	public boolean removeClient(CarcassonneThreadServer client) {
		return clients.remove(client);
	}
	
	/*
	 * Section critique, l'exclusion mutuelle doit être assurée en cas de READY concurrents
	 * La boucle ne doit être regardée que par un Thread à la fois
	 */
	synchronized boolean isAllPlayersReady() {
		for(CarcassonneThreadServer client : clients)
			if (!client.isReady())
				return false;
		return true;
	}
	
	/*
	 * Envoi un message reçu d'un client vers tous les autres clients connectés
	 * Si le message est READY, on vérifie de manière atomique si les autres joueurs sont prêts
	 */
	void sendMessageToClients(Message m, CarcassonneThreadServer from) {
		try {
			String type = m.getNthValue(0).toString();
			
			if (type.equals("READY")) {
				if (isAllPlayersReady())
					isListening = false;
			}
			
			// On transmet le numero du client émetteur dans le message
			if (type.equals("HELLO") || type.equals("HELLOACK") || 
					type.equals("CLOSE") || type.equals("FINISH"))
				m.getNthValue(1).setIntValue(clients.indexOf(from));
			
			for(CarcassonneThreadServer client : clients) {
				if (client.equals(from))	// On exclu l'émetteur de la liste
					continue;
				
				client.sendMessageFromServer(m);
			}
		} catch (ProtocolError e) {
			Message noop = new Message("NOOP");
			from.sendMessageFromServer(noop);
			e.printStackTrace();
		}
	}
}