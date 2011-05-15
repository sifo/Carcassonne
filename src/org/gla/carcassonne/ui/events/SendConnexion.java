package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.ui.MultiplayerLobbyDialog;

public class SendConnexion implements ActionListener {

	private CarcassonneView view;
	private MultiplayerLobbyDialog lobby;
	
	public SendConnexion(CarcassonneView view, MultiplayerLobbyDialog lobby) {
		this.view = view;
		this.lobby = lobby;
	}

	public void actionPerformed(ActionEvent e) {
		view.getController().notifyConnexion(lobby);
	}
}
