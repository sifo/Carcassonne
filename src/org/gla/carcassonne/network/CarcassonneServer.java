package org.gla.carcassonne.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * Le serveur écoute pendant 500ms, s'arrête pendant 500ms et réécoute
 * à nouveau. Le but est de régulièrement donner la main aux autres threads.
 * Cela dit, ce comportement pourrait bien changer.
 */
public class CarcassonneServer {
	
	private ServerSocket serverSocket;
	private boolean isListening;
	private List<CarcassonneThreadServer> clients;

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
				} catch (SocketTimeoutException e) {
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
}