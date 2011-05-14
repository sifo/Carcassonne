package org.gla.carcassonne.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Set;

import org.gla.carcassonne.entities.Tile;

public class CarcassonneClient extends Socket implements ClientFactory {
	
	public Client client;
	
	public CarcassonneClient(String host, int port)
	throws UnknownHostException, IOException {
		super(host, port);
		
		client = new Client(host, port, this);
		client.start();
	}

	public Object getPlayers(Set<Integer> p) {
		return null;
	}

	public int setX(Object o) {
		Tile t = (Tile) o;
		return t.getxOnBoard();
	}

	public int setY(Object o) {
		Tile t = (Tile) o;
		return t.getyOnBoard();
	}

	public String setOrientation(Object o) {
		return null;
	}

	public String setTile(Object o) {
		return null;
	}

	public String setPiece(Object o) {
		return null;
	}

	public boolean checkMove(int player, String tile, int x, int y, String o,
			String piece) {
		return false;
	}
}
