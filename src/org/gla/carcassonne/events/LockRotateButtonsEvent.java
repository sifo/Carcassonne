package org.gla.carcassonne.events;

import java.util.EventObject;

public class LockRotateButtonsEvent extends EventObject {

	private static final long serialVersionUID = 1618056546757523694L;

	public LockRotateButtonsEvent(Object source) {
		super(source);
	}
}