package org.gla.carcassonne.managers;

import java.util.EnumMap;
import java.util.Map;

import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.entities.Board;
import org.gla.carcassonne.entities.Tile;
import org.gla.carcassonne.entities.TileType;
import org.gla.carcassonne.utils.RandomGenerator;

public class TileManager {
	private Board board;
	private Map<TileType, Integer> tiles;
	private int numberOfTileRemaining;
	private final static int MAX_TILE_NUMBER = 72;
	private Tile currentTile;
	private CarcassonneModel model;

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

	public TileManager(CarcassonneModel model) {
		int i = 0;
		numberOfTileRemaining = MAX_TILE_NUMBER;
		currentTile = null;
		board = new Board(model);
		this.model = model;
		tiles = new EnumMap<TileType, Integer>(TileType.class);

		for (TileType t : TileType.values()) {
			tiles.put(t, tilesCount[i++]);
		}
	}

	public void putFirstTileOnBoard() {
		Tile tile = selectTileRandomly();
		remove(tile);
		board.add(2, 2, tile);
		model.fireNextTile();
	}

	public Tile selectTileRandomly() {
		RandomGenerator<EnumMap<TileType, Integer>> generator = 
			new RandomGenerator<EnumMap<TileType, Integer>>(
				TileType.class);
		TileType t = generator.random();
		currentTile = new Tile(t);
		return currentTile;
	}
	
	public void remove(Tile tile) {
		numberOfTileRemaining--;
		model.fireRemainingTile();
		int count = 
			tiles.containsKey(tile.getType()) ? tiles.get(tile.getType()) : 0;
		tiles.put(tile.getType(), count - 1);
	}

	public Tile getNextTile() {
		Tile tile = selectTileRandomly();
		remove(tile);
		return tile;
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
	
	public Tile getCurrentTile() {
		return currentTile;
	}
	
	public void rotateLeft() {
		currentTile.rotateLeft();
		model.fireRotateLeft();
	}
	
	public void rotateRight() {
		currentTile.rotateRight();
		model.fireRotateRight();
	}
}