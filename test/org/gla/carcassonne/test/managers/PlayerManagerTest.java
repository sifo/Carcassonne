package org.gla.carcassonne.test.managers;

import junit.framework.TestCase;

import org.gla.carcassonne.Model;
import org.gla.carcassonne.managers.PlayerManager;

public class PlayerManagerTest extends TestCase {

	PlayerManager playerManager; 

	protected void setUp() {
		playerManager = new PlayerManager(new Model());
	}

	public void testPlayerManager() {
		assertNotNull(playerManager.getPlayers());
	}
}