package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.View;

public class NewGameEvent implements ActionListener {
	private View view;
	
	public NewGameEvent(View view){
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		view.getController().notifyNewGame();
	}

}