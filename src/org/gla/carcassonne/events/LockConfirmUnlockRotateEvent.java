package org.gla.carcassonne.events;

import java.util.EventObject;

public class LockConfirmUnlockRotateEvent extends EventObject {

	private static final long serialVersionUID = 8131692057726361919L;

	public LockConfirmUnlockRotateEvent(Object source) {
		super(source);
	}
}