package org.gla.carcassonne;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class JPanelBoard extends JPanel {

	private JPanel [] tiles;
	private final static int TOTAL_NUMBER_OF_TILES = 1000;
	private int numberOfTiles;
	private final static String FIRST_IMAGE = "res/drawable/tile-a.png";

	public JPanelBoard() {
		tiles = new JPanel[TOTAL_NUMBER_OF_TILES];
		tiles[0] = new JPanelTile(FIRST_IMAGE);
		add(tiles[0]);
		numberOfTiles = 1;
	}
}
