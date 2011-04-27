package org.gla.carcassonne;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class JPanelTile extends JPanel {
	
	private Tile tile;
	
	public JPanelTile(Tile tile) {
		this.tile = tile;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image imageTile = Toolkit.getDefaultToolkit().getImage(tile.getType().getPath());
		g.drawImage(imageTile, 0, 0, 100, 100, this);
	}
	
	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
}
