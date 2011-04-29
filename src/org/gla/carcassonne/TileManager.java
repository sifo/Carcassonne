package org.gla.carcassonne;

import org.gla.carcassonne.Tile;

public class TileManager {
	private int numberOfTileOnBoard;
	private int numberOfTileRemaining;
	private Tile [] tilesOnBoard;
	private Tile [] tilesRemaining = 
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
		numberOfTileRemaining = MAX_TILE_NUMBER;
		numberOfTileOnBoard = 0;
		tilesOnBoard = new Tile[MAX_TILE_NUMBER];
	}

	public void addTileOnBoard(Tile tile) {
		tilesOnBoard[numberOfTileOnBoard++] = tile;
	}

	public void putFirstTileOnBoard() {
		int tilePosition = selectTileRemainingRandomly();
		addTileOnBoard(tilesRemaining[tilePosition]);
		removeTileRemaining(tilePosition);
	}

	public int selectTileRemainingRandomly() {
		int lower = 0;
		int higher = numberOfTileRemaining;
		int tilePosition = (int)(Math.random() * (higher - lower)) + lower;
		return tilePosition;
	}

	public void removeTileRemaining(int tilePosition) {
		int lastTile = numberOfTileRemaining - 1; 
		tilesRemaining[tilePosition] = tilesRemaining[lastTile];
		numberOfTileRemaining--;
	}

	public boolean tilesRemainingContains(Tile tile) {
		for(int i = 0; i < numberOfTileRemaining; i++)
			if(tilesRemaining[i] == tile)
				return true;
		return false;
	}

	public Tile getNextTile() {
		int tilePosition = selectTileRemainingRandomly(); 
		Tile tile = tilesRemaining[tilePosition];
		removeTileRemaining(tilePosition);
		return tile;
	}

	public boolean tilesOnBoardContains(Tile tile) {
		for(int i = 0; i < numberOfTileOnBoard; i++)
			if(tilesOnBoard[i] == tile)
				return true;
		return false;
	}

	public Tile[] getTilesRemaining() {
		return tilesRemaining;
	}

	public Tile[] getTilesOnBoard() {
		return tilesOnBoard;
	}

	public int getNumberOfTileOnBoard() {
		return numberOfTileOnBoard;
	}

	public int getNumberOfTileRemaining() {
		return numberOfTileRemaining;
	}
}
