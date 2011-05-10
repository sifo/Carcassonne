package org.gla.carcassonne.events;

import java.util.EventObject;

public class RotateRightEvent extends EventObject {

	private static final long serialVersionUID = -6511099650169849641L;
	int rotateCount;
	
	public int getRotateCount() {
		return rotateCount;
	}

	public RotateRightEvent(Object source, int rotateCount) {
		super(source);
		this.rotateCount = rotateCount;
	}
}
