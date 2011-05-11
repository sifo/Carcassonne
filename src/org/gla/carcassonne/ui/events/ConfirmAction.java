package org.gla.carcassonne.ui.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.CarcassonneView;

public class ConfirmAction implements ActionListener {
	private CarcassonneView view;
	
	public ConfirmAction(CarcassonneView view){
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		view.getController().notifyConfirmAction();
	}

}
