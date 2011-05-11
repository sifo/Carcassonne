package org.gla.carcassonne.test.managers;

import junit.framework.TestCase;

import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.managers.PlayerManager;

public class PlayerManagerTest extends TestCase {

	PlayerManager playerManager; 

	protected void setUp() {
		playerManager = new PlayerManager(new CarcassonneModel());
	}

	public void testPlayerManager() {
		assertNotNull(playerManager.getPlayers());
	}
}