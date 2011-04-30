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
		assertNotNull(tileManager.getTiles());
		assertNotNull(tileManager.getBoard());
	}

	public void testPutFirstTileOnBoard() {
		int numberOfTileRemaining = tileManager.getNumberOfTileRemaining();
		int numberOfTileOnBoard = tileManager.getBoard().getTileCount();
		tileManager.putFirstTileOnBoard();
		assertEquals(numberOfTileOnBoard + 1, tileManager.getBoard().getTileCount());
		assertEquals(numberOfTileRemaining - 1, tileManager.getNumberOfTileRemaining());
	}

	public void testGetNextTile() {
		int numberOfTileRemaining = tileManager.getNumberOfTileRemaining();
		tileManager.putFirstTileOnBoard();
		assertEquals(numberOfTileRemaining - 1, tileManager.getNumberOfTileRemaining());
	}
}
