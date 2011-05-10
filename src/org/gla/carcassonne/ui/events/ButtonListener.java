package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.CarcassonneView;

public class ButtonListener implements ActionListener {
	int line;
	int column;
	private CarcassonneView view;

	public ButtonListener(int line, int column, CarcassonneView view) {
		this.line = line;
		this.column = column;
		this.view = view;
	}

	public void actionPerformed(ActionEvent arg0) {
		view.getController().notifyAddTile(line, column);
	}
}
