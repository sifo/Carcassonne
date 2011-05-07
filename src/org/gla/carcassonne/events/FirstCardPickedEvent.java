package org.gla.carcassonne.events;

import java.util.EventObject;
import org.gla.carcassonne.entities.Tile;

public class FirstCardPickedEvent extends EventObject {
	private Tile newTile;
	private static final long serialVersionUID = 5661293201897297708L;

	public FirstCardPickedEvent(Object source, Tile newTile) {
		super(source);
		this.newTile = newTile;
	}

	public Tile getNewTile() {
		return newTile;
	}
}