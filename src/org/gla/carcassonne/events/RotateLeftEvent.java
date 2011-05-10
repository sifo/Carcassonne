package org.gla.carcassonne.events;

import java.util.EventObject;

public class RotateLeftEvent extends EventObject {
	int rotateCount;
	
	public int getRotateCount() {
		return rotateCount;
	}

	private static final long serialVersionUID = 1447421352709361050L;

	public RotateLeftEvent(Object source, int rotateCount) {
		super(source);
		this.rotateCount = rotateCount;
	}
}
