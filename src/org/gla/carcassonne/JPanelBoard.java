package org.gla.carcassonne;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class JPanelBoard extends JPanel {

	private JPanel[] tiles;
	private final static int TOTAL_NUMBER_OF_TILES = 1000;
	private int numberOfTiles;

	public JPanelBoard() {
		// On pourrait le générer aléatoirement avec genre :
		// Tile tile = new RandomGenerator<Tile>();
		Tile firstTile = new Tile(TileType.TILE_C);
		tiles = new JPanel[TOTAL_NUMBER_OF_TILES];
		tiles[0] = new JPanelTile(firstTile.getType().getPath());
		add(tiles[0]);
		numberOfTiles = 1;
	}
}
