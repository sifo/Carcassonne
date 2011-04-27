package org.gla.carcassonne;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class JPanelMain extends JPanel {
	
	private Image imageInitial;
	private final static String cheminImageInitial = "res/drawable/tile-x.jpg";
	
	public JPanelMain() {
		imageInitial = Toolkit.getDefaultToolkit().getImage(cheminImageInitial);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(imageInitial, 0, 0, this);
	}
}
