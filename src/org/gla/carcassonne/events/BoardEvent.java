package org.gla.carcassonne.events;

import java.util.EventObject;

import org.gla.carcassonne.entities.Tile;

public class BoardEvent extends EventObject {

	private static final long serialVersionUID = -7076273071527598299L;
	Tile[][] tiles;

	public BoardEvent(Object source, Tile[][] tiles) {
		super(source);
		this.tiles = tiles;
	}	
	
	public Tile[][] getTiles() {
		return tiles;
	}
}
