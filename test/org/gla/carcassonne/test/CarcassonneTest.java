package org.gla.carcassonne.test;

import org.gla.carcassonne.Carcassonne;
import junit.framework.TestCase;

public class CarcassonneTest extends TestCase {
	
	private Carcassonne carcassonne = new Carcassonne();

	public CarcassonneTest(String s) {
		super(s);
	}

	public void testStart() {
		assertTrue("probleme!", true);
	}
	
	public void testIgnore() {
		System.out.println("ignore!");
	}

}
