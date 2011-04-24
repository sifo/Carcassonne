package org.gla.carcassonne.test;

import org.gla.carcassonne.Carcassonne;
import junit.framework.TestCase;

public class CarcassonneTest extends TestCase {
	
	Carcassonne carcassonne;  

	protected void setUp() {
		carcassonne = new Carcassonne();
	}
	
	public void testCarcassonne() {
		assertNotNull(carcassonne.getPlayers());
		assertNotNull(carcassonne.getTiles());
		assertNotNull(carcassonne.getTilesOnBoard());
		assertFalse(carcassonne.getPlayerNumber() == 0);
	}

	public void testAddPlayer() {
		int playerNumber = carcassonne.getPlayerNumber();
		carcassonne.addPlayer();
		assertEquals(playerNumber + 1, carcassonne.getPlayerNumber());
	}
}
