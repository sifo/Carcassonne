package org.gla.carcassonne.events;

import java.util.EventObject;
import org.gla.carcassonne.entities.Tile;

public class PlacePieceOnTileEvent extends EventObject {
	private static final long serialVersionUID = -6586745314221190952L;
	private Tile tile;
	
	public PlacePieceOnTileEvent(Object source, Tile tile) {
		super(source);
		this.tile = tile;
	}

	public Tile getTile() {
		return tile;
	}
}