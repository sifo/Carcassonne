package org.gla.carcassonne.test;

import org.gla.carcassonne.JFrameCarcassonne;
import junit.framework.TestCase;

public class JFrameCarcassonneTest extends TestCase {
	
	private JFrameCarcassonne jframeCarcassonne;
	private final static String TITLE = "JFrame test";

	protected void setUp() {
		jframeCarcassonne = new JFrameCarcassonne(TITLE);
	}

	protected void tearDown() {
		jframeCarcassonne.dispose();
	}

	public void testJFrameCarcassonne() {
		assertNotNull(jframeCarcassonne.getJPanelBoard());
		assertNotNull(jframeCarcassonne.getJPanelTile());
	}
}
