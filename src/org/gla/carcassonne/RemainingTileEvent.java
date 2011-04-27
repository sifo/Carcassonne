package org.gla.carcassonne;

import java.util.EventObject;

public class RemainingTileEvent extends EventObject {
	private String newText;

	public RemainingTileEvent(Object source, String newText) {
		super(source);
		this.newText = newText;
	}

	public String getNewText() {
		return newText;
	}
}
