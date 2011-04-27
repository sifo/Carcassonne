package org.gla.carcassonne;

import java.util.EventObject;

public class NextTileEvent extends EventObject {
	private Tile newTile;
	
	public NextTileEvent(Object source, Tile newTile) {
		super(source);
		this.newTile = newTile;
	}

	public Tile getNewTile() {
		return newTile;
	}
}
