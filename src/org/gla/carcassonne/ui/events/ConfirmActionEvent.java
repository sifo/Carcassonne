package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.View;

public class ConfirmActionEvent implements ActionListener {
	private View view;
	
	public ConfirmActionEvent(View view){
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		view.getController().notifyConfirmAction();
	}

}
