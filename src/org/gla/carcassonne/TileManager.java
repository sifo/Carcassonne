package org.gla.carcassonne;

import org.gla.carcassonne.Tile;

public class TileManager {
	private int numberOfTileOnBoard;
	private Tile [] tilesOnBoard;
	private Tile [] tiles = 
		{ new Tile(TileType.TILE_A), new Tile(TileType.TILE_A), 

		new Tile(TileType.TILE_B), new Tile(TileType.TILE_B), 
		new Tile(TileType.TILE_B), new Tile(TileType.TILE_B), 

		new Tile(TileType.TILE_C),

		new Tile(TileType.TILE_D), new Tile(TileType.TILE_D), 
		new Tile(TileType.TILE_D), new Tile(TileType.TILE_D), 

		new Tile(TileType.TILE_E), new Tile(TileType.TILE_E), 
		new Tile(TileType.TILE_E), 	new Tile(TileType.TILE_E), 
		new Tile(TileType.TILE_E),

		new Tile(TileType.TILE_G), new Tile(TileType.TILE_G), 
		new Tile(TileType.TILE_G), 

		new Tile(TileType.TILE_H), new Tile(TileType.TILE_H), 
		new Tile(TileType.TILE_H), 

		new Tile(TileType.TILE_I), new Tile(TileType.TILE_I), 

		new Tile(TileType.TILE_J), new Tile(TileType.TILE_J), 
		new Tile(TileType.TILE_J), 
	
		new Tile(TileType.TILE_K), new Tile(TileType.TILE_K), 
		new Tile(TileType.TILE_K),
	
		new Tile(TileType.TILE_L), new Tile(TileType.TILE_L), 
		new Tile(TileType.TILE_L),

		new Tile(TileType.TILE_N), new Tile(TileType.TILE_N), 
		new Tile(TileType.TILE_N), new Tile(TileType.TILE_N), 
		new Tile(TileType.TILE_N),

		new Tile(TileType.TILE_P), new Tile(TileType.TILE_P), 
		new Tile(TileType.TILE_P), new Tile(TileType.TILE_P), 
		new Tile(TileType.TILE_P),

		new Tile(TileType.TILE_R), new Tile(TileType.TILE_R), 
		new Tile(TileType.TILE_R), new Tile(TileType.TILE_R), 

		new Tile(TileType.TILE_T), new Tile(TileType.TILE_T), 
		new Tile(TileType.TILE_T), 
		
		new Tile(TileType.TILE_U), new Tile(TileType.TILE_U), 
		new Tile(TileType.TILE_U), new Tile(TileType.TILE_U), 
		new Tile(TileType.TILE_U), new Tile(TileType.TILE_U), 
		new Tile(TileType.TILE_U), new Tile(TileType.TILE_U), 

		new Tile(TileType.TILE_V), new Tile(TileType.TILE_V), 
		new Tile(TileType.TILE_V), new Tile(TileType.TILE_V), 
		new Tile(TileType.TILE_V), new Tile(TileType.TILE_V), 
		new Tile(TileType.TILE_V), new Tile(TileType.TILE_V), 
		new Tile(TileType.TILE_V),


		new Tile(TileType.TILE_W), new Tile(TileType.TILE_W), 
		new Tile(TileType.TILE_W), new Tile(TileType.TILE_W), 

		new Tile(TileType.TILE_X) 
	};

	private final static int MAX_TILE_NUMBER = 72;

	public TileManager() {
		numberOfTileOnBoard = 0;
		tilesOnBoard = new Tile[MAX_TILE_NUMBER];
	}

	public void addTileOnBoard(Tile t) {
		tilesOnBoard[numberOfTileOnBoard++] = t;
	}

	public void putFirstTileOnBoard() {
		addTileOnBoard(selectTileRandomly());
	}

	public Tile selectTileRandomly() {
		int lower = 0;
		int higher = MAX_TILE_NUMBER - numberOfTileOnBoard;
		int random = (int)(Math.random() * (higher - lower)) + lower;
		Tile res = tiles[random];
		tiles[random] = tiles[higher - 1]; 
		// Permet d'effacer l'élément, pour ne pas être resélectionné
		return res;
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public Tile[] getTilesOnBoard() {
		return tilesOnBoard;
	}

	public int getNumberOfTileOnBoard() {
		return numberOfTileOnBoard;
	}

	public int getNumberOfTileRemaining() {
		return MAX_TILE_NUMBER - numberOfTileOnBoard;
	}

	public Tile getNextTile() {
		return selectTileRandomly(); 
	}

}
