package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.View;

public class RotateRightEvent implements ActionListener {
	private View view;
	
	public RotateRightEvent(View view){
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		view.getController().notifyRotateRight();
	}

}
