package org.gla.carcassonne.test;

import org.gla.carcassonne.Player;
import junit.framework.TestCase;

public class PlayerTest extends TestCase {
	
	private Player player;

	protected void setUp() {
		player = new Player("Blue");
	}

	public void testPlayer() {
		assertNotNull(player.getName());
	}
}
