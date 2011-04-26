package org.gla.carcassonne.test;

import org.gla.carcassonne.PlayerManager;
import junit.framework.TestCase;

public class PlayerManagerTest extends TestCase {

	PlayerManager playerManager; 

	protected void setUp() {
		playerManager = new PlayerManager();
	}

	public void testPlayerManager() {
		assertNotNull(playerManager.getPlayers());
		assertFalse(playerManager.getPlayerNumber() == 0);
	}

	public void testAddPlayer() {
		int playerNumber = playerManager.getPlayerNumber();
		playerManager.addPlayer();
		assertEquals(playerNumber + 1, playerManager.getPlayerNumber());
	}

	public void testRemovePlayer() {
		int playerNumber = playerManager.getPlayerNumber();
		playerManager.removePlayer();
		assertEquals(playerNumber - 1, playerManager.getPlayerNumber());
	}
}
