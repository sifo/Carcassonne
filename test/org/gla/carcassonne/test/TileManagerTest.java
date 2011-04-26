package org.gla.carcassonne.test;

import org.gla.carcassonne.TileManager;
import junit.framework.TestCase;

public class TileManagerTest extends TestCase {
	TileManager tileManager;

	protected void setUp() {
		tileManager = new TileManager();
	}

	public void testTileManager() {
		assertNotNull(tileManager.getTiles());
		assertNotNull(tileManager.getTilesOnBoard());
	}

}
