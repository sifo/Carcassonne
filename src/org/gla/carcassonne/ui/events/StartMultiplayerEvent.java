package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.ui.SwingView;

public class StartMultiplayerEvent implements ActionListener {
	private SwingView view;
	
	public StartMultiplayerEvent(SwingView view){
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		view.getController().notifyStartMultiplayer();
	}
}
