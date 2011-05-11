package org.gla.carcassonne.events;

import java.util.EventObject;

public class CardBackEvent extends EventObject {

	private static final long serialVersionUID = -2867329674029667293L;

	public CardBackEvent(Object source) {
		super(source);
	}

}