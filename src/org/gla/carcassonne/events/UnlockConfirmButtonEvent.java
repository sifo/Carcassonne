package org.gla.carcassonne.events;

import java.util.EventObject;

public class UnlockConfirmButtonEvent extends EventObject {

	private static final long serialVersionUID = 6103151682995674142L;

	public UnlockConfirmButtonEvent(Object source) {
		super(source);
	}
}
