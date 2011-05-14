package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.ui.MultiplayerLobbyDialog;
import org.gla.carcassonne.ui.SwingCarcassonneView;

public class StartMultiplayer implements ActionListener {
	private SwingCarcassonneView view;
	
	public StartMultiplayer(SwingCarcassonneView view){
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		new MultiplayerLobbyDialog(view);
		view.getController().notifyStartMultiplayer();
	}
}
