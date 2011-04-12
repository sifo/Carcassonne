package org.gla.carcassonne.test;

import org.gla.carcassonne.CarcassonneGame;
import junit.framework.TestCase;

public class CarcassonneGameTest extends TestCase {
	
	private CarcassonneGame carcassonneGame = new CarcassonneGame();

	public CarcassonneGameTest(String s) {
		super(s);
	}

	public void testStart() {
		assertTrue("probleme!", true);
	}
	
	public void testIgnore() {
		System.out.println("ignore!");
	}

}
