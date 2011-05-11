package org.gla.carcassonne.test.entities;

import org.gla.carcassonne.entities.Player;
import junit.framework.TestCase;

public class PlayerTest extends TestCase {
	
	private Player player;

	protected void setUp() {
		player = new Player("tom", "Blue");
	}

	public void testPlayer() {
		assertNotNull(player.getName());
	}
}