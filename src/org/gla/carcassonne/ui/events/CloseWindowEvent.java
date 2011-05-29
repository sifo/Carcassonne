package org.gla.carcassonne.ui.events;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseWindowEvent implements ActionListener {
	private Window window;	
	
	public CloseWindowEvent(Window window){
		this.window = window;
	}

	public void actionPerformed(ActionEvent arg0) {
		window.dispose();
	}
	
}
