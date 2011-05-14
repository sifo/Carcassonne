package org.gla.carcassonne.network;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.net.Socket;
import java.net.SocketException;

import org.gla.carcassonne.utils.*;

public class CarcassonneThreadServer extends Thread {

	private Socket socket;
	private PushbackInputStream in;
	private BufferedOutputStream out;
	private int port;
	private CarcassonneServer server;
	
	private boolean isClosed;	// indique si la connexion en cours doit se fermer (quitter une partie)
	private boolean isReady;	// indique si le joueur est prêt ou non à démarrer une partie
	private boolean hasToken;	// indique si le joueur possède actuellement le token

	private final static String CONNECTION_RESET = "connection reset";

	public CarcassonneThreadServer(Socket socket, CarcassonneServer server)
	throws IOException {
		this.socket = socket;
		this.server = server;
		in = new PushbackInputStream(socket.getInputStream());
		out = new BufferedOutputStream(socket.getOutputStream());
		port = socket.getPort();
		isClosed = false;
		isReady = false;
		hasToken = false;
	}
	
	public void run() {
		try {
			while (!isClosed) {
				waitingResponse(in);
			}
			
			in.close();
			out.close();
			socket.close();
			server.getClients().remove(this);
		} catch (SocketException e) {
			System.out.println(CONNECTION_RESET);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void waitingResponse(PushbackInputStream in) {
		try {
			Message receive = Message.parse(in);
			String type = receive.getNthValue(0).toString();
			
			if (!ProtocolMessages.getTypesSet().contains(type))
				throw new ProtocolError(type);
				
			if (type.equals("CLOSE")) {
				server.sendMessageFromClient(receive, this);
				
				// Si c'est à son tour de jouer, alors passer son tour
				if (hasToken)
					notify();
				
				server.removeClient(this);
				in.close();
				out.close();
				socket.close();
				return;
			}
			else if (type.equals("HELLO")) {
				if (server.getClients().isEmpty()) {
					Message m = new Message("HELLONACK");
					sendMessageFromServer(m);
					return;
				}
			}
			else if (type.equals("READY")) {
				if (!isReady())
					setReady(true);
				else
					throw new ProtocolError("Joueur déjà prêt");
			}
			else if (type.equals("MOVE")) {
				int token = server.getToken();
				if (receive.getNthValue(7).getIntValue() != token)
					throw new ProtocolError("Token invalide");
			}

			server.sendMessageFromClient(receive, this);
		} catch (ProtocolParseError e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ProtocolError e) {
			Message m = new Message("NOOP");
			sendMessageFromServer(m);
			e.printStackTrace();
		}
	}
	

	/*
	 * Envoi d'un message vers le client du thread depuis le serveur
	 */
	public void sendMessageFromServer(Message m) {
		try {
			m.format(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public void setClosed(boolean isClosed) {
		this.isReady = isClosed;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setHasToken(boolean hasToken) {
		this.hasToken = hasToken;
	}

	public boolean isHasToken() {
		return hasToken;
	}
}