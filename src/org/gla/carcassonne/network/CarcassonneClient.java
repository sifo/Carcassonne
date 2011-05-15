package org.gla.carcassonne.network;

import java.net.Socket;
import java.util.Set;

import org.gla.carcassonne.entities.Tile;

public class CarcassonneClient extends Socket implements ClientFactory {

	public CarcassonneClient() {
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
