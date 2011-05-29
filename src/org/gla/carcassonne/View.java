package org.gla.carcassonne;

import org.gla.carcassonne.Listener;
import org.gla.carcassonne.Controller;

public abstract class View implements Listener {
	private Controller controller;

	public View(Controller controller) {
		this.controller = controller;
	}
	
	public final Controller getController() {
		return controller;
	}
	
	public abstract void display();
	public abstract void close();
}
