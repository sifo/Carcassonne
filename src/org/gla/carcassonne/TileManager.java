package org.gla.carcassonne;

import java.util.EnumMap;
import java.util.Map;

import org.gla.carcassonne.Tile;

public class TileManager {
	private int numberOfTileOnBoard;
	private Tile [] tilesOnBoard;
	
	private Map<TileType, Integer> tiles;
	private static final int[] tilesCount = new int[] {
		2,	// Tuile A
		2,	// Tuile B
		2,	// Tuile C
		2,	// Tuile D
		2,	// Tuile E
		2,	// Tuile F
		2,	// Tuile G
		2,	// Tuile H
		2,	// Tuile I
		2,	// Tuile J
		2,	// Tuile K
		2,	// Tuile L
		2,	// Tuile M
		2,	// Tuile N
		2,	// Tuile O
		2,	// Tuile P
		2,	// Tuile Q
		2,	// Tuile R
		2,	// Tuile S
		2,	// Tuile T
		2,	// Tuile U
		2,	// Tuile V
		2,	// Tuile W
		2	// Tuile X
	};

	private final static int MAX_TILE_NUMBER = 72;

	public TileManager() {
		int i = 0;	// numéro de tuile
		
		numberOfTileOnBoard = 0;
		tilesOnBoard = new Tile[MAX_TILE_NUMBER];
		tiles = new EnumMap<TileType, Integer>(TileType.class);
		
		for (TileType t : TileType.values()) {
			tiles.put(t, tilesCount[i++]);
		}
	}

	public void addTileOnBoard(Tile t) {
		tilesOnBoard[numberOfTileOnBoard++] = t;
	}

	public void putFirstTileOnBoard() {
		addTileOnBoard(selectTileRandomly());
	}
	
	public Tile selectTileRandomly() {
		RandomGenerator<EnumMap<TileType, Integer>> generator = new RandomGenerator<EnumMap<TileType, Integer>>(TileType.class);
		TileType t = generator.random();
		return new Tile(t);
	}

	public Map<TileType, Integer> getTiles() {
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
