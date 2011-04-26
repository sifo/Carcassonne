package org.gla.carcassonne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelBoard extends JPanel {
	
	private JPanel[] tiles;
	private final static int TOTAL_NUMBER_OF_TILES = 1000;
	private int numberOfTiles;
	public JPanel squarePanel = new JPanel();
	public JLabel square;

	public JPanelBoard(int gridLength, int gridHeight) {

		this.setLayout(new GridLayout(gridLength, gridHeight));

		for (int i = 0; i < gridLength*gridHeight; i++) {
            //JPanel square = new JPanel(new BorderLayout());
            //add(square);
            
    		// On pourrait le générer aléatoirement avec genre :
    		// Tile tile = new RandomGenerator<Tile>();
    		Tile firstTile = new Tile(TileType.TILE_C);
    		tiles = new JPanel[TOTAL_NUMBER_OF_TILES];
    		tiles[0] = new JPanelTile(firstTile.getType().getPath());
    		add(tiles[0]);
    		numberOfTiles = 1;
 
            /*int row = (i / gridLength) % 2;
            if (row == 0)
                square.setBackground(i % 2 == 0 ? Color.black : Color.white);
            else
                square.setBackground(i % 2 == 0 ? Color.white : Color.black);*/
        }
	}
}
