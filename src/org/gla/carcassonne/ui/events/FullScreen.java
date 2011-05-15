package org.gla.carcassonne.ui.events;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gla.carcassonne.ui.SwingCarcassonneView;

public class FullScreen implements ActionListener {
	private SwingCarcassonneView view;
	
	public FullScreen(SwingCarcassonneView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(!view.getJFrame().isUndecorated()) {
			view.getJFrame().dispose();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = ((int) screenSize.getWidth()) / screenCount();
			int height = (int) screenSize.getHeight();
			view.getJFrame().setUndecorated(true);  
			view.getJFrame().setPreferredSize(new Dimension(width, height));
			view.getController().getCarcassonneModel().fireBoard();
			view.getJFrame().setVisible(true);
			view.getJFrame().getMenuBarCarcassonne().getFullScreen().setEnabled(false);
			view.getJFrame().getMenuBarCarcassonne().getQuitFullScreen().setEnabled(true);
		}
		else {
			view.getJFrame().dispose();
			view.getJFrame().setUndecorated(false);  
			view.getJFrame().setPreferredSize(new Dimension(1000, 750));
			view.getController().getCarcassonneModel().fireBoard();
			view.getJFrame().setVisible(true);
			view.getJFrame().getMenuBarCarcassonne().getFullScreen().setEnabled(true);
			view.getJFrame().getMenuBarCarcassonne().getQuitFullScreen().setEnabled(false);
		}
	}
	
	public int screenCount() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		int numScreens = 1;
		try {
		    GraphicsDevice[] gs = ge.getScreenDevices();
		    numScreens = gs.length;
		} catch (HeadlessException e) {
		}
		return numScreens;
	}

}
