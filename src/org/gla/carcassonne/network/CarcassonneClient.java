package org.gla.carcassonne.network;

import java.net.Socket;
import java.util.Set;

import org.gla.carcassonne.entities.Tile;

public class CarcassonneClient extends Socket implements ClientFactory {

	public CarcassonneClient() {
	}

	public Object getPlayers(Set<Integer> p) {
		return (Set<Integer>)p;
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
		Tile t = (Tile) o;
		String orientation = "";
		switch(t.getRotationCount()) {
			case 0:
				orientation = "N";
				break;
			case 1:
				orientation = "E";
				break;
			case 2:
				orientation = "S";
				break;
			case 3:
				orientation = "W";
				break;
		}
		
		return orientation;
	}

	public String setTile(Object o) {
		Tile t = (Tile) o;
		String[] path = t.getType().getPath().split("/");
		String tileName = path[path.length-1];
		String[] splited = tileName.split(".");
		return splited[0];
	}

	public String setPiece(Object o) {
		return "";
	}

	public boolean checkMove(int player, String tile, int x, int y, String o, String piece) {
		
		return true;
	}
}
