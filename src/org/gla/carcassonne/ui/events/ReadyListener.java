package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.ui.MultiplayerLobbyDialog;
import org.gla.carcassonne.ui.SwingCarcassonneView;

public class ReadyListener implements ActionListener {

	private CarcassonneView view;
	private MultiplayerLobbyDialog lobby;
	
	public ReadyListener(SwingCarcassonneView view, MultiplayerLobbyDialog lobby) {
		this.view = view;
		this.lobby = lobby;
	}
	
	public void actionPerformed(ActionEvent e) {
		lobby.setEnableReadyButton(false);
		view.getController().notifyReady();
	}
}
