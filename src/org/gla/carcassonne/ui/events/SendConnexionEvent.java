package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.View;
import org.gla.carcassonne.ui.MultiplayerLobbyDialog;

public class SendConnexionEvent implements ActionListener {

	private View view;
	private MultiplayerLobbyDialog lobby;
	
	public SendConnexionEvent(View view, MultiplayerLobbyDialog lobby) {
		this.view = view;
		this.lobby = lobby;
	}

	public void actionPerformed(ActionEvent e) {
		view.getController().notifyConnexion(lobby);
	}
}
