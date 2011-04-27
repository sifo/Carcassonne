package org.gla.carcassonne;

import java.util.EventObject;
import org.gla.carcassonne.Tile;

public class FirstCardPickedEvent extends EventObject {
	private Tile newTile;

	public FirstCardPickedEvent(Object source, Tile newTile) {
		super(source);
		this.newTile = newTile;
	}

	public Tile getNewTile() {
		return newTile;
	}
}
