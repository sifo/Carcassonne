package org.gla.carcassonne.test.ui;

import org.gla.carcassonne.CarcassonneController;
import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.ui.SwingCarcassonneView;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import junit.framework.TestCase;

public class SwingCarcassonneViewTest extends TestCase {
	
	private SwingCarcassonneView view;
	private CarcassonneController controller;
	private CarcassonneModel model;

	protected void setUp() {
		model = new CarcassonneModel();
		controller = new CarcassonneController(model);
		view = new SwingCarcassonneView(controller);
	}

	protected void tearDown() {
		view.close();
	}

	public void testSwingCarcassonneView() {
		assertNotNull(view.getJFrame());
		assertEquals(view.getJFrame().getDefaultCloseOperation(),
			JFrame.EXIT_ON_CLOSE);
		assertTrue(view.getJFrame().getWidth() != 0);
		assertTrue(view.getJFrame().getHeight() != 0);
		assertEquals(GridBagLayout.class, view.getJFrame()
			.getContentPane().getLayout().getClass());

	}

	public void testDisplay() {
		view.display();
		assertTrue(view.getJFrame().isVisible());	
	}

	public void testClose() {
		view.display();
		view.close();
		assertFalse(view.getJFrame().isVisible());
	}
}