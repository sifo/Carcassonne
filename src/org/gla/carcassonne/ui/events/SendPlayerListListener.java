package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.ui.NumberPlayerDialog;

public class SendPlayerListListener implements ActionListener {
	private CarcassonneView view;
	private NumberPlayerDialog numberPlayerDialog;

	public SendPlayerListListener(CarcassonneView view,
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
