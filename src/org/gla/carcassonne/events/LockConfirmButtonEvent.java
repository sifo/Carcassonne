package org.gla.carcassonne.events;

import java.util.EventObject;

public class LockConfirmButtonEvent extends EventObject {

	private static final long serialVersionUID = 8131692057726361919L;

	public LockConfirmButtonEvent(Object source) {
		super(source);
	}
}