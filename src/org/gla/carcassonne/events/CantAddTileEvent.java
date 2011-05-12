package org.gla.carcassonne.events;

import java.util.EventObject;

public class CantAddTileEvent extends EventObject {

	private static final long serialVersionUID = 5856242167825216950L;
	private int x;
	private int y;
	
	public CantAddTileEvent(Object source, int x, int y) {
		super(source);
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
