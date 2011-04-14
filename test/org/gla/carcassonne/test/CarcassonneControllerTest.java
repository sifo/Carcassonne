package org.gla.carcassonne.test;

import org.gla.carcassonne.CarcassonneController;
import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.CarcassonneListener;
import org.gla.carcassonne.JFrameCarcassonne;
import junit.framework.TestCase;

public class CarcassonneControllerTest extends TestCase {

	private CarcassonneController controller;
	private CarcassonneModel model;
	private CarcassonneView view;

	protected void setUp() {
		model = new CarcassonneModel();
		controller = new CarcassonneController(model);	
	}

	protected void tearDown() {
		controller.closeViews();
	}

	public void testCarcassonneController() {
		assertEquals(model, controller.getCarcassonneModel());
		assertNotNull(controller.getCarcassonneView());
		assertTrue(controller.getCarcassonneModel().getListeners()
			.getListenerCount() != 0);
	}

	public void testCloseViews() {
		controller.displayViews();
		controller.closeViews();
		assertFalse(((JFrameCarcassonne)controller.getCarcassonneView())
			.getJFrame().isVisible());
	}

	public void testDisplayViews() {
		controller.displayViews();
		assertTrue(((JFrameCarcassonne)controller.getCarcassonneView())
			.getJFrame().isVisible());
	}

	public void testAddListenersToModel() {
		controller.addListenersToModel();
		assertEquals(controller.getCarcassonneModel().getListeners()
			.getListeners(CarcassonneListener.class)[0],
			controller.getCarcassonneView());
	}
}
