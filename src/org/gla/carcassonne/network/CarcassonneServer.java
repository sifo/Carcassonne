package org.gla.carcassonne.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.gla.carcassonne.utils.*;

/**
 * Le serveur écoute pendant 500ms, s'arrête pendant 500ms et réécoute
 * à nouveau. Le but est de régulièrement donner la main aux autres threads.
 * Cela dit, ce comportement pourrait bien changer.
 */
public class CarcassonneServer {
	
	private ServerSocket serverSocket;
	private List<CarcassonneThreadServer> clients;
	
	private boolean hasStarted;		// indique si la partie a commencé
	private boolean isFinished;	// indique si la partie s'est terminée
	
	private int token;				// nombre généré aléatoire pour désigner quel client a la main
	private int nbAckMessages;		// nombre de messages ack reçu pour un MOVE

	private final static String CONNECTION_ACCEPTED = "Connexion acceptée : ";
	private final static int NUMBER_MAX_OF_CLIENTS = 5;
	private final static int LISTENING_PORT = 54322;
	private final static int SERVER_TIME_OUT = 500;

	public CarcassonneServer() throws IOException, InterruptedException {
		hasStarted = false;
		isFinished = false;
		serverSocket = new ServerSocket(LISTENING_PORT);
		clients = new ArrayList<CarcassonneThreadServer>();
		serverSocket.setSoTimeout(SERVER_TIME_OUT);
	}

	public void startServer() throws IOException, InterruptedException {
		hasStarted = false;
		isFinished = false;
		
		// En attente de connexions : la partie n'a pas encore démarrée
		while (!hasStarted) {
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
			else
				wait();
		}
		
		// La partie a démarré et le serveur donne la main aux clients à tour de rôle
		for(CarcassonneThreadServer client : clients) {
			Message m = new Message("START");
			client.sendMessageFromServer(m);
		}
		
		runGame();
		
		// La partie a terminé et le serveur s'arrête
		serverSocket.close();
	}
	
	public int generateRandomInt() {
		Random generator = new Random();
		return generator.nextInt(511)+1;	// entre 1 et 512
	}
	
	private void runGame() {
		while (!isFinished) {
			token = generateRandomInt();
			nbAckMessages = 0;
			
			for(CarcassonneThreadServer client : clients) {
				Message m = new Message("TOKEN", new MessageInt(token));
				client.sendMessageFromServer(m);
				client.setHasToken(true);
				
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	/*
	 * Regarde si la partie a commencé
	 */
	public boolean hasStarted() {
		return hasStarted;
	}
	
	/*
	 * Regarde si la partie s'est terminée
	 */
	public boolean isFinished() {
		return isFinished;
	}

	public List<CarcassonneThreadServer> getClients() {
		return clients;
	}

	public int getToken() {
		return token;
	}
	
	/*
	 * Renvoi vrai si le client est retiré de la liste, faux sinon (échec ou non existance)
	 */
	protected boolean removeClient(CarcassonneThreadServer client) {
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
	protected void sendMessageFromClient(Message m, CarcassonneThreadServer from) {
		try {
			String type = m.getNthValue(0).toString();
			
			if (type.equals("READY")) {
				if (isAllPlayersReady()) {
					hasStarted = true;
					notify();	// notification en cas de wait sur nombre max de joueurs atteint
				}
			}
			
			if (type.equals("MOVEACK")) {
				nbAckMessages++;
				if (nbAckMessages == clients.size()-1) {	// on exclu celui qui a MOVE
					nbAckMessages = 0;
					notify();
				}
				else
					return;		// Tant que nous n'avons pas que des ACK, on ne fait rien
			}
			
			if (type.equals("FINISH"))
				isFinished = true;
			
			// On réinitialise le token avant d'envoyer le message aux autres clients
			if (type.equals("MOVE")) {
				token = 0;
				from.setHasToken(false);
				m.getNthValue(1).setIntValue(clients.indexOf(from));
			}
			
			// On transmet le numero du client émetteur dans le message
			if (type.equals("HELLO") || type.equals("HELLOACK") || 
					type.equals("CLOSE") || type.equals("FINISH") || type.equals("READY"))
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