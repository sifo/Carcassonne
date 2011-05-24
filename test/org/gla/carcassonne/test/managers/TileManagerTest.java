package org.gla.carcassonne.test.managers;

import junit.framework.TestCase;

import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.managers.TileManager;

public class TileManagerTest extends TestCase {
	TileManager tileManager;

	protected void setUp() {
		tileManager = new TileManager(new CarcassonneModel());
	}

	public void testTileManager() {
		assertNotNull(tileManager.getTiles());
		assertNotNull(tileManager.getBoard());
	}

	public void testPutFirstTileOnBoard() {
		int numberOfTileRemaining = tileManager.getNumberOfTileRemaining();
		int numberOfTileOnBoard = tileManager.getBoard().getTileCount();
		tileManager.putFirstTileOnBoard();
		assertEquals(numberOfTileOnBoard + 1, tileManager.getBoard()
				.getTileCount());
		assertEquals(numberOfTileRemaining - 1,
				tileManager.getNumberOfTileRemaining());
	}

	public void testGetNextTile() {
		int numberOfTileRemaining = tileManager.getNumberOfTileRemaining();
		tileManager.putFirstTileOnBoard();
		assertEquals(numberOfTileRemaining - 2,
				tileManager.getNumberOfTileRemaining());
	}
	
}