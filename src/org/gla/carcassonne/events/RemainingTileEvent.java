package org.gla.carcassonne.events;

import java.util.EventObject;

public class RemainingTileEvent extends EventObject {
	private String newText;
	private static final long serialVersionUID = -1816818068189927448L;

	public RemainingTileEvent(Object source, String newText) {
		super(source);
		this.newText = newText;
	}

	public String getNewText() {
		return newText;
	}
}