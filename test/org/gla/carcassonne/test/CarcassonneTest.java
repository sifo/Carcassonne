package org.gla.carcassonne.test;

import org.gla.carcassonne.Carcassonne;
import junit.framework.TestCase;

public class CarcassonneTest extends TestCase {
	
	Carcassonne carcassonne;  

	protected void setUp() {
		carcassonne = new Carcassonne();
	}
	
	public void testCarcassonne() {
		assertNotNull(carcassonne.getTileManager());
		assertNotNull(carcassonne.getPlayerManager());
	}
}
