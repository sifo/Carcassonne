package org.gla.carcassonne;

import java.util.EnumMap;
import java.util.Map;
import org.gla.carcassonne.Tile;

public class TileManager {
	private Board board;
	private Map<TileType, Integer> tiles;
	private int numberOfTileRemaining;
	private final static int MAX_TILE_NUMBER = 72;
	
	private static final int[] tilesCount = new int[] {
			//f, m, n o, q, s sont des tuiles d'extensions 
			// inutiles pour l'instant
			2, // Tuile A
			4, // Tuile B
			1, // Tuile C
			4, // Tuile D
			5, // Tuile E
			3, // Tuile G
			3, // Tuile H
			2, // Tuile I
			3, // Tuile J
			3, // Tuile K
			3, // Tuile L
			5, // Tuile N
			5, // Tuile P
			4, // Tuile R
			3, // Tuile T
			8, // Tuile U
			9, // Tuile V
			4, // Tuile W
			1 // Tuile X
	};

	public TileManager() {
		int i = 0;
		numberOfTileRemaining = MAX_TILE_NUMBER;
		board = new Board();
		tiles = new EnumMap<TileType, Integer>(TileType.class);

		for (TileType t : TileType.values()) {
			tiles.put(t, tilesCount[i++]);
		}
	}

	public void putFirstTileOnBoard() {
		Tile tile = selectTileRandomly();
		board.add(0, 0, tile);
		remove(tile);
	}

	public Tile selectTileRandomly() {
		RandomGenerator<EnumMap<TileType, Integer>> generator = new RandomGenerator<EnumMap<TileType, Integer>>(
				TileType.class);
		TileType t = generator.random();
		return new Tile(t);
	}
	
	public void remove(Tile tile) {
		numberOfTileRemaining--;
		int count = tiles.containsKey(tile.getType()) ? tiles.get(tile.getType()) : 0;
		tiles.put(tile.getType(), count - 1);
	}

	public Tile getNextTile() {
		return selectTileRandomly(); 
	}

	public Board getBoard() {
		return board;
	}
	
	public Map<TileType, Integer> getTiles() {
		return tiles;
	}
	
	public int getNumberOfTileRemaining() {
		return numberOfTileRemaining;
	}
}