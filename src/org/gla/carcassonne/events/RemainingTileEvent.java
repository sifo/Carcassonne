package org.gla.carcassonne.events;

import java.util.EventObject;

public class RemainingTileEvent extends EventObject {
	private int number;
	private static final long serialVersionUID = -1816818068189927448L;

	public RemainingTileEvent(Object source, int number) {
		super(source);
		this. number = number;
	}

	public int getNumber() {
		return number;
	}
}