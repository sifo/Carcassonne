package org.gla.carcassonne.test.ui;

import org.gla.carcassonne.Controller;
import org.gla.carcassonne.Model;
import org.gla.carcassonne.ui.SwingView;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import junit.framework.TestCase;

public class SwingCarcassonneViewTest extends TestCase {
	
	private SwingView view;
	private Controller controller;
	private Model model;

	protected void setUp() {
		model = new Model();
		controller = new Controller(model);
		view = new SwingView(controller);
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