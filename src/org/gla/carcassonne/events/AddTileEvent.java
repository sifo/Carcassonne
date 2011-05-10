package org.gla.carcassonne.events;

import java.util.EventObject;

import org.gla.carcassonne.entities.Tile;

public class AddTileEvent extends EventObject {

	private Tile tile;
	private static final long serialVersionUID = -3435690443200344172L;

	public AddTileEvent(Object source, Tile tile) {
		super(source);
		this.tile = tile;
	}

	public Tile getTile() {
		return tile;
	}	
}
