package org.gla.carcassonne.test;

import junit.framework.TestCase;
import org.gla.carcassonne.Controller;
import org.gla.carcassonne.Listener;
import org.gla.carcassonne.Model;
import org.gla.carcassonne.ui.SwingView;

public class CarcassonneControllerTest extends TestCase {

	private Controller controller;
	private Model model;

	protected void setUp() {
		model = new Model();
		controller = new Controller(model);	
	}

	protected void tearDown() {
		controller.closeViews();
	}

	public void testCarcassonneController() {
		assertEquals(model, controller.getModel());
		assertNotNull(controller.getView());
		assertTrue(controller.getModel().getListeners()
			.getListenerCount() != 0);
	}

	public void testCloseViews() {
		controller.displayViews();
		controller.closeViews();
		assertFalse(((SwingView)controller.getView())
			.getJFrame().isVisible());
	}

	public void testDisplayViews() {
		controller.displayViews();
		assertTrue(((SwingView)controller.getView())
			.getJFrame().isVisible());
	}

	public void testAddListenersToModel() {
		controller.addListenersToModel();
		assertEquals(controller.getModel().getListeners()
			.getListeners(Listener.class)[0],
			controller.getView());
	}
}