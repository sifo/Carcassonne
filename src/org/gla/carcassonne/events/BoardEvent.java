package org.gla.carcassonne.events;

import java.util.EventObject;

import org.gla.carcassonne.entities.Tile;

public class BoardEvent extends EventObject {
	Tile[][] tiles;

	public BoardEvent(Object source, Tile[][] tiles) {
		super(source);
		this.tiles = tiles;
	}	
	
	public Tile[][] getTiles() {
		return tiles;
	}
}
