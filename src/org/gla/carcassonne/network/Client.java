package org.gla.carcassonne.network;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.gla.carcassonne.network.utils.Message;
import org.gla.carcassonne.network.utils.MessageInt;
import org.gla.carcassonne.network.utils.MessageString;
import org.gla.carcassonne.network.utils.ProtocolError;
import org.gla.carcassonne.network.utils.ProtocolMessages;
import org.gla.carcassonne.network.utils.ProtocolParseError;
import org.gla.carcassonne.utils.*;

public class Client extends Thread {

	private static final int DEFAULT_TOKEN = 0;
	private ClientFactory factory;
	private Socket socket;
	private PushbackInputStream in;
	private BufferedOutputStream out;
	
	private int token;						// Entier à envoyer dans le message MOVE pour pouvoir jouer.
	private boolean hasStarted;				// Indique si la partie à commencé (réponse du serveur)
	private boolean isAccepted;				// Indique si le mouvement effectué a été accepté par les autres clients
	private boolean isFinished;				// Indique si la partie est terminée
	private boolean hasHello;				// Le client s'est présenté au serveur (et veut une bière...)
	private Map<Integer, Boolean> players;	// Liste des autres joueurs
	
	public Client(String address, int port, ClientFactory f) throws UnknownHostException, IOException {
		setSocket(new Socket(address, port));
		setFactory(f);

		in = new PushbackInputStream(socket.getInputStream());
		out = new BufferedOutputStream(socket.getOutputStream());
		players = new HashMap<Integer, Boolean>();
		token = DEFAULT_TOKEN;
		isAccepted = false;
		hasStarted = false;
		hasHello = false;
		isFinished = false;
	}
	
	/*
	 * Démarrage du client et traitement de tous les messages possibles
	 */
	public void run() {
		try {
			while(true) {
				if (!hasHello) {
					sendHello();
					hasHello = true;
				}
				
				Message receive = Message.parse(in);
				String type = receive.getNthValue(0).toString();
				
				System.out.println("CLIENT - received message is "+receive.toString());
				
				if (!ProtocolMessages.getTypesSet().contains(type))
					throw new ProtocolError("Protocole incompatible ou non à jour.");
				
				if (type.equals("CLOSE"))
					removePlayer(receive);

				if (!hasStarted) {
					if (type.equals("HELLO")) {
						addPlayer(receive);
						sendHelloAck();
					}
					
					if (type.equals("HELLOACK"))
						addPlayer(receive);
					
					if (type.equals("HELLONACK"))
						//continue;
						hasStarted = true;

					if (type.equals("START"))
						hasStarted = true;
				}
				else {
					if (type.equals("TOKEN")) {
						token = getToken(receive);
						System.out.println("Token value is "+token);
					}

					if (type.equals("READY"))
						setPlayerReady(receive);
					
					if (type.equals("MOVEACK")) {
						synchronized(this) {
							notifyAll();
							isAccepted = true;
							token = DEFAULT_TOKEN;
						}
					}
					
					if (type.equals("MOVENACK")) {
						synchronized(this) {
							notifyAll();
							isAccepted = false;
							token = DEFAULT_TOKEN;
						}
					}
					
					if (type.equals("MOVE"))
						checkMoveFromEngine(receive);
					
					if (type.equals("FINISH")) {
						isFinished = true;
						disconnect();
					}
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
	public boolean move(Object ot, Object ox, Object oy, Object oo, Object op) {
		MessageInt n = new MessageInt(0);
		MessageString tile = new MessageString(factory.setTile(ot));
		MessageInt x = new MessageInt(factory.setX(ox));
		MessageInt y = new MessageInt(factory.setY(oy));
		MessageString o = new MessageString(factory.setOrientation(oo));
		MessageString piece = new MessageString((op == null) ? "null" : factory.setPiece(op));
		MessageInt t = new MessageInt(token);
		
		Message m = new Message("MOVE", n, tile, x, y, o, piece, t);
		System.out.println("Move is : "+m.toString());
		
		try {
			m.format(out);
			out.flush();
			
			synchronized(this) {
				wait();		// En attente d'un MOVEACK ou MOVENACK
			}
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
			isFinished = true;
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
			return 0;
		}
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
			String o = m.getNthValue(5).getStringValue();
			String piece = m.getNthValue(6).getStringValue();
			
			if (factory.checkMove(playerId, tile, x, y, o, piece))
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
	
	/*
	 * Regarde si la tuile posée a été acceptée
	 */
	public boolean isAccepted() {
		return isAccepted;
	}
	
	/*
	 * Récupère la liste des joueurs courante. Sous-classé par la factory
	 */
	public Object getPlayers() {
		return factory.getPlayers(players.keySet());
	}
	
	/*
	 * Récupère le token pour savoir si c'est à notre tour de jouer
	 */
	public int getToken() {
		return token;
	}
}
