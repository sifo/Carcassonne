package org.gla.carcassonne.events;

import java.util.EventObject;

public class CantAddTileEvent extends EventObject {

	private static final long serialVersionUID = 5856242167825216950L;

	public CantAddTileEvent(Object source) {
		super(source);
	}
}
