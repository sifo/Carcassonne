package org.gla.carcassonne;

import org.gla.carcassonne.CarcassonneListener;
import org.gla.carcassonne.CarcassonneController;

public abstract class CarcassonneView implements CarcassonneListener {
	private CarcassonneController controller;

	public CarcassonneView(CarcassonneController controller) {
		this.controller = controller;
	}
	
	public final CarcassonneController getController() {
		return controller;
	}
	
	public abstract void display();
	public abstract void close();
}
