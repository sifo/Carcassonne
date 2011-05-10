package org.gla.carcassonne.test.entities;

import junit.framework.TestCase;

import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.entities.Carcassonne;

public class CarcassonneTest extends TestCase {
	
	Carcassonne carcassonne;  

	protected void setUp() {
		carcassonne = new Carcassonne(new CarcassonneModel());
	}
	
	public void testCarcassonne() {
		assertNotNull(carcassonne.getTileManager());
		assertNotNull(carcassonne.getPlayerManager());
	}
}