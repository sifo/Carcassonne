package org.gla.carcassonne.network;

import java.net.Socket;
import java.util.Set;

import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.entities.Tile;
import org.gla.carcassonne.entities.TileType;

public class CarcassonneClient extends Socket implements ClientFactory {

	CarcassonneModel model;
	
	public CarcassonneClient(CarcassonneModel model) {
		this.model = model;
	}

	public Object getPlayers(Set<Integer> p) {
		return (Set<Integer>)p;
	}

	public int setX(Object o) {
		/*Tile t = (Tile) o;
		return t.getxOnBoard();*/
		int x = (Integer) o;
		return x;
	}

	public int setY(Object o) {
		/*Tile t = (Tile) o;
		return t.getyOnBoard();*/
		int y = (Integer) o;
		return y;
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
		String tile = tileName.substring(0, tileName.length()-4);
		return tile;
	}

	public String setPiece(Object o) {
		return "";
	}
	
	public TileType parseTileNameToType(String tile) throws IllegalArgumentException {
		String path = "res/drawable/".concat(tile).concat(".png");
		TileType type =  TileType.valueOf(path);
		return type;
	}

	public boolean checkMove(int player, String tile, int x, int y, String o, String piece) {
		TileType type;
		int rotation;
			
		try {
			type = parseTileNameToType(tile);
		}
		catch (IllegalArgumentException e) {
			return false;
		}
		
		if (o.equals("N"))
			rotation = 0;
		else if (o.equals("E"))
			rotation = 1;
		else if (o.equals("S"))
			rotation = 2;
		else if (o.equals("W"))
			rotation = 3;
		else
			return false;
		
		Tile t = new Tile(type);
		t.setRotationCount(rotation);
		
		if (model.getTileManager().getBoard().canPlace(x, y, t)) {
			model.getTileManager().getBoard().add(x, y, t);
			return true;
		}
		
		return false;
	}
}
