package org.gla.carcassonne.network;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.gla.carcassonne.utils.*;

public class Client extends Thread {

	private ClientFactory factory;
	private Socket socket;
	private PushbackInputStream in;
	private BufferedOutputStream out;
	
	private int token;						// Entier à envoyer dans le message MOVE pour pouvoir jouer.
	private boolean hasStarted;				// Indique si la partie à commencé (réponse du serveur)
	private boolean isAccepted;				// Indique si le mouvement effectué a été accepté par les autres clients
	private boolean hasHello;				// Le client s'est présenté au serveur (et veut une bière...)
	private Map<Integer, Boolean> players;	// Liste des autres joueurs
	
	public Client(String address, int port, ClientFactory f) {
		try {
			setSocket(new Socket(address, port));
			setFactory(f);
			
			in = new PushbackInputStream(socket.getInputStream());
			out = new BufferedOutputStream(socket.getOutputStream());
			players = new HashMap<Integer, Boolean>();
			token = 0;
			isAccepted = false;
			hasStarted = false;
			hasHello = false;
		} catch (UnknownHostException e) {
			System.out.println("Connection impossible à "+address+":"+port);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Démarrage du client et traitement de tous les messages possibles
	 * TODO : Meilleure factorisation ?
	 */
	public void run() {
		try {
			while(true) {
				Message receive = Message.parse(in);
				String type = receive.getNthValue(0).toString();
				
				if (!ProtocolMessages.getTypesSet().contains(type))
					throw new ProtocolError("Protocole incompatible ou non à jour.");
				
				if (type.equals("CLOSE"))
					removePlayer(receive);
				
				if (!hasHello) {
					sendHello();
					hasHello = true;
				}
				
				if (!hasStarted) {
					if (type.equals("HELLO")) {
						addPlayer(receive);
						sendHelloAck();
					}
					
					if (type.equals("HELLOACK"))
						addPlayer(receive);
					
					if (type.equals("HELLONACK"))
						continue;

					if (type.equals("START"))
						hasStarted = true;
				}
				else {
					if (type.equals("TOKEN"))
						token = getToken(receive);

					if (type.equals("READY"))
						setPlayerReady(receive);
					
					if (type.equals("MOVEACK")) {
						notify();
						isAccepted = true;
					}
					
					if (type.equals("MOVENACK")) {
						notify();
						isAccepted = false;
					}
					
					if (type.equals("MOVE"))
						checkMoveFromEngine(receive);
					
					if (type.equals("FINISH"))
						disconnect();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ProtocolParseError e) {
			e.printStackTrace();
		} catch (ProtocolError e) {
			System.out.println("Problème serveur.");
			e.printStackTrace();
		}
	}
	
	public void ready() {
		Message m = new Message("READY", new MessageInt(0));
		try {
			m.format(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Envoyer un message MOVE, prenant en paramètres :
	 * Object représentant une tuile
	 * Object représentant une coordonnée x
	 * Object représentant une coordonnée y
	 * Object représentant une pièce (pion)
	 * Renvoi vrai si un MOVEACK est reçu
	 */
	public boolean move(Object ot, Object ox, Object oy, Object op) {
		MessageInt n = new MessageInt(0);
		MessageString tile = new MessageString(factory.setTile(ot));
		MessageInt x = new MessageInt(factory.setX(ox));
		MessageInt y = new MessageInt(factory.setY(oy));
		MessageString piece = new MessageString((op == null) ? "null" : factory.setPiece(op));
		MessageInt t = new MessageInt(token);
		
		Message m = new Message("MOVE", n, tile, x, y, piece, t);
		
		try {
			m.format(out);
			out.flush();
			wait();			// En attente d'un MOVEACK ou MOVENACK
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		return isAccepted;
	}
	
	/*
	 * Pour quitter une partie
	 */
	public void disconnect() {
		Message m = new Message("CLOSE", new MessageInt(0));
		try {
			m.format(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Pour terminer une partie
	 */
	public void finish() {
		Message m = new Message("FINISH", new MessageInt(0));
		try {
			m.format(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Informe le serveur que le MOVE reçu est valide (respect des règles)
	 */
	public void validMove() {
		Message m = new Message("MOVEACK");
		try {
			m.format(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Informe le serveur que le MOVE reçu est invalide (règles non respectées)
	 */
	public void invalidMove() {
		Message m = new Message("MOVENACK");
		try {
			m.format(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendHello() {
		Message m = new Message("HELLO", new MessageInt(0));
		try {
			m.format(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendHelloAck() {
		Message m = new Message("HELLOACK", new MessageInt(0));
		try {
			m.format(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMoveAck() {
		Message m = new Message("MOVEACK");
		try {
			m.format(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMoveNack() {
		Message m = new Message("MOVENACK");
		try {
			m.format(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addPlayer(Message m) {
		try {
			int playerId = m.getNthValue(1).getIntValue();
			players.put(playerId, false);
		} catch (ProtocolError e) {
			System.out.println("Message HELLO malformaté");
			e.printStackTrace();
		}
	}
	
	private void removePlayer(Message m) {
		try {
			int playerId = m.getNthValue(1).getIntValue();
			players.remove(playerId);
		} catch (ProtocolError e) {
			System.out.println("Message HELLO malformaté");
			e.printStackTrace();
		}
	}
	
	private int getToken(Message m) {
		try {
			return m.getNthValue(1).getIntValue();
		} catch (ProtocolError e) {
			System.out.println("Message TOKEN malformaté");
			e.printStackTrace();
		}
		return 0;
	}
	
	/*
	 * Met le joueur ayant envoyé un READY à prêt
	 */
	private void setPlayerReady(Message m) {
		try {
			int playerId = m.getNthValue(1).getIntValue();
			if (!players.containsKey(playerId))
				return;
			
			players.put(playerId, true);
		} catch (ProtocolError e) {
			e.printStackTrace();
		}
	}
	
	private void checkMoveFromEngine(Message m) {
		try {
			int playerId = m.getNthValue(1).getIntValue();
			String tile = m.getNthValue(2).getStringValue();
			int x = m.getNthValue(3).getIntValue();
			int y = m.getNthValue(4).getIntValue();
			String piece = m.getNthValue(5).getStringValue();
			
			if (factory.checkMove(playerId, tile, x, y, piece))
				sendMoveAck();
			else
				sendMoveNack();
		} catch (ProtocolError e) {
			e.printStackTrace();
		}
	}

 	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setFactory(ClientFactory factory) {
		this.factory = factory;
	}

	public ClientFactory getFactory() {
		return factory;
	}
	
	/*
	 * Récupère la liste des joueurs courante. Sous-classé par la factory
	 */
	public Object getPlayers() {
		return factory.getPlayers(players.keySet());
	}
}