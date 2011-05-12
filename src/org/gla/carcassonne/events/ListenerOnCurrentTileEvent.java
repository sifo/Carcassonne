package org.gla.carcassonne.events;

import java.util.EventObject;

public class ListenerOnCurrentTileEvent extends EventObject {
	private static final long serialVersionUID = -683518236864296853L;
	private int x;
	private int y;
	
	public ListenerOnCurrentTileEvent(Object source, int x, int y){
		super(source);
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}	
	
}