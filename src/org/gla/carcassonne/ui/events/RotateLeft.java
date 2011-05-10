package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.CarcassonneView;

public class RotateLeft implements ActionListener {
	private CarcassonneView view;
	
	public RotateLeft(CarcassonneView view){
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		view.getController().notifyRotateLeft();
	}

}
