package org.gla.carcassonne.test;

import org.gla.carcassonne.TileManager;
import org.gla.carcassonne.Tile;
import org.gla.carcassonne.TileType;
import junit.framework.TestCase;

public class TileManagerTest extends TestCase {
	TileManager tileManager;

	protected void setUp() {
		tileManager = new TileManager();
	}

	public void testTileManager() {
		assertNotNull(tileManager.getTilesRemaining());
		assertNotNull(tileManager.getTilesOnBoard());
	}

	public void testAddTileOnBoard() {
		Tile tile = new Tile(TileType.TILE_A);
		tileManager.addTileOnBoard(tile);
		assertTrue(tileManager.tilesOnBoardContains(tile));
	}
	
	public void testPutFirstTileOnBoard() {
		int numberOfTileRemaining = tileManager.getNumberOfTileRemaining();
		int numberOfTileOnBoard = tileManager.getNumberOfTileOnBoard();
		tileManager.putFirstTileOnBoard();
		assertEquals(numberOfTileOnBoard + 1, tileManager.getNumberOfTileOnBoard());
		assertEquals(numberOfTileRemaining - 1, tileManager.getNumberOfTileRemaining());
	}

	public void testSelectTileRemainingRandomly() {
		int min = 0;
		int max = tileManager.getNumberOfTileRemaining();

		int tilePosition = tileManager.selectTileRemainingRandomly();
		assertTrue(tilePosition >= 0 && tilePosition < max);

		tilePosition = tileManager.selectTileRemainingRandomly();
		assertTrue(tilePosition >= 0 && tilePosition < max);

		tilePosition = tileManager.selectTileRemainingRandomly();
		assertTrue(tilePosition >= 0 && tilePosition < max);
	}

	public void testRemoveTileRemaining() {
		Tile removedTile = tileManager.getTilesRemaining()[0];
		tileManager.removeTileRemaining(0);
		assertFalse(tileManager.tilesRemainingContains(removedTile));
	}

	public void testTilesRemainingContains() {
		Tile tile = tileManager.getTilesRemaining()[0];
		Tile randomTile = new Tile(TileType.TILE_X);
		assertTrue(tileManager.tilesRemainingContains(tile));
		assertFalse(tileManager.tilesRemainingContains(randomTile));
	}

	public void testGetNextTile() {
		int numberOfTileRemaining = tileManager.getNumberOfTileRemaining();
		tileManager.putFirstTileOnBoard();
		assertEquals(numberOfTileRemaining - 1, tileManager.getNumberOfTileRemaining());
	}

	public void testTilesOnBoardContains() {
		Tile tile = new Tile(TileType.TILE_A);
		tileManager.addTileOnBoard(tile);
		Tile randomTile = new Tile(TileType.TILE_X);
		assertTrue(tileManager.tilesOnBoardContains(tile));
		assertFalse(tileManager.tilesOnBoardContains(randomTile));
	}
}
