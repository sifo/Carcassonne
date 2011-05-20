package org.gla.carcassonne.ui.events;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class CloseWindow implements ActionListener {
	private Window window;	
	
	public CloseWindow(Window window){
		this.window = window;
	}

	public void actionPerformed(ActionEvent arg0) {
		window.dispose();
	}
	
}
