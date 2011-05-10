package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.CarcassonneView;

public class RotateRight implements ActionListener {
	private CarcassonneView view;
	
	public RotateRight(CarcassonneView view){
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		view.getController().notifyRotateRight();
	}

}
