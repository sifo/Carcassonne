package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.View;
import org.gla.carcassonne.ui.MultiplayerLobbyDialog;
import org.gla.carcassonne.ui.SwingView;

public class ReadyListenerEvent implements ActionListener {

	private View view;
	private MultiplayerLobbyDialog lobby;
	
	public ReadyListenerEvent(SwingView view, MultiplayerLobbyDialog lobby) {
		this.view = view;
		this.lobby = lobby;
	}
	
	public void actionPerformed(ActionEvent e) {
		lobby.setEnableReadyButton(false);
		view.getController().notifyReady();
	}
}
