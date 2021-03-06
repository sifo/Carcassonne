package org.gla.carcassonne.events;

import java.util.EventObject;
import org.gla.carcassonne.entities.Tile;

public class DrawNextTileEvent extends EventObject {
	private Tile newTile;
	private static final long serialVersionUID = -3588074956097041159L;
	
	public DrawNextTileEvent(Object source, Tile newTile) {
		super(source);
		this.newTile = newTile;
	}

	public Tile getNewTile() {
		return newTile;
	}
}