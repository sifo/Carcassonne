package org.gla.carcassonne.test.entities;

import junit.framework.TestCase;
import org.gla.carcassonne.entities.Tile;
import org.gla.carcassonne.entities.TileType;

public class TileTest extends TestCase {
	public Tile tile;
	
	protected void setUp() {
		tile = new Tile(TileType.TILE_A);
	}
	
	public void testTile() {
		assertNotNull(tile.getType());
		assertNotNull(tile.getSideValues());
		assertNull(tile.getPlayer());
		assertEquals(-1, tile.getX());
		assertEquals(-1, tile.getY());
		assertNotNull(tile.getZones());
		assertEquals(tile.getType().getClass(), TileType.class);
	}
}