package org.gla.carcassonne;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class JPanelTile extends JPanel {
	
	private Image imageTile;
	
	public JPanelTile(String pathToImageSource) {
		imageTile = Toolkit.getDefaultToolkit().getImage(pathToImageSource);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imageTile, 0, 0, 100, 100, this);
	}

	public Image getImageTile() {
		return imageTile;
	}
}
