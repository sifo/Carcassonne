package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.gla.carcassonne.View;
import org.gla.carcassonne.ui.NumberPlayerDialog;

public class PlayerListEvent implements ActionListener {
	private View view;
	private NumberPlayerDialog numberPlayerDialog;

	public PlayerListEvent(View view,
			NumberPlayerDialog numberPlayerDialog) {
		this.view = view;
		this.numberPlayerDialog = numberPlayerDialog;
	}

	public void actionPerformed(ActionEvent arg0) {
		view.getController().notifyPlay();
		view.getController().notifyPlayerList(
				numberPlayerDialog.getPlayersNames());
		numberPlayerDialog.dispose();
	}
}
