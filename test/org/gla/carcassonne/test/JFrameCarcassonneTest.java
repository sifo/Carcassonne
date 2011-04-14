package org.gla.carcassonne.test;

import org.gla.carcassonne.JFrameCarcassonne;
import org.gla.carcassonne.CarcassonneController;
import org.gla.carcassonne.CarcassonneModel;
import javax.swing.JFrame;
import junit.framework.TestCase;

public class JFrameCarcassonneTest extends TestCase{
	
	private JFrameCarcassonne jframeCarcassonne;
	private CarcassonneController controller;
	private CarcassonneModel model;

	protected void setUp() {
		model = new CarcassonneModel();
		controller = new CarcassonneController(model);
		jframeCarcassonne = new JFrameCarcassonne(controller);
	}

	protected void tearDown() {
		jframeCarcassonne.close();
	}

	public void testJFrameCarcassonne() {
		assertNotNull(jframeCarcassonne.getJFrame());
		assertNotNull(jframeCarcassonne.getJPanel());
		assertEquals(jframeCarcassonne.getJFrame().getDefaultCloseOperation(),
			JFrame.EXIT_ON_CLOSE);
		assertTrue(jframeCarcassonne.getJFrame().getWidth() != 0);
		assertTrue(jframeCarcassonne.getJFrame().getHeight() != 0);
	}

	public void testDisplay() {
		jframeCarcassonne.display();
		assertTrue(jframeCarcassonne.getJFrame().isVisible());	
	}

	public void testClose() {
		jframeCarcassonne.display();
		jframeCarcassonne.close();
		assertFalse(jframeCarcassonne.getJFrame().isVisible());
	}
}
