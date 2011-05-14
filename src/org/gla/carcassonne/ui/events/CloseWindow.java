package org.gla.carcassonne.ui.events;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import org.gla.carcassonne.CarcassonneView;

public class CloseWindow implements WindowListener {
	private CarcassonneView view;	
	
	public CloseWindow(CarcassonneView view){
		this.view = view;
	}

	public void windowClosing(WindowEvent arg0) {
		view.getController().notifyQuitGame();
	}
	
	public void windowActivated(WindowEvent arg0) {	}

	public void windowClosed(WindowEvent arg0) { }

	public void windowDeactivated(WindowEvent arg0) { }

	public void windowDeiconified(WindowEvent arg0) { }

	public void windowIconified(WindowEvent arg0) { }

	public void windowOpened(WindowEvent arg0) { }
}
