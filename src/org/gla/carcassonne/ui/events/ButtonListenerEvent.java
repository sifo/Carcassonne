package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.View;

public class ButtonListenerEvent implements ActionListener {
	int line;
	int column;
	private View view;

	public ButtonListenerEvent(int line, int column, View view) {
		this.line = line;
		this.column = column;
		this.view = view;
	}

	public void actionPerformed(ActionEvent arg0) {
		view.getController().notifyAddTile(line, column);
	}
}
