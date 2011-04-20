package org.gla.carcassonne;

public class Tile {

	private TileType type;
	
	public Tile(TileType type) {
		this.setType(type);
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public TileType getType() {
		return type;
	}
}
