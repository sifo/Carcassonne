package org.gla.carcassonne.test;

import java.util.EventListener;

import junit.framework.TestCase;
import org.gla.carcassonne.CarcassonneController;
import org.gla.carcassonne.CarcassonneListener;
import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.ui.SwingCarcassonneView;

public class CarcassonneModelTest extends TestCase {
	
	private CarcassonneModel model;
	private CarcassonneController controller;
	private CarcassonneListener listener;

	protected void setUp() {
		model = new CarcassonneModel();
		controller = new CarcassonneController(model);
		listener = new SwingCarcassonneView(controller);
	}

	public void testCarcassonneModel() {
		assertNotNull(model.getListeners());
	}

	public void testAddCarcassonneListener() {
		model = new CarcassonneModel();
		model.addCarcassonneListener(listener);
		int count = model.getListeners()
			.getListenerCount(CarcassonneListener.class);
		
		EventListener [] eventListeners = model.getListeners()
				.getListeners(CarcassonneListener.class);

		CarcassonneListener lastListener = 
			(CarcassonneListener) eventListeners[count - 1];

		assertEquals(listener, lastListener);
	}

	public void testRemoveCarcassonneListener() {
		model = new CarcassonneModel();
		model.addCarcassonneListener(listener);
		int count = model.getListeners()
			.getListenerCount(CarcassonneListener.class);
		assertEquals("count = " + count, 1, count);

		model.removeCarcassonneListener(listener);
		count = model.getListeners()
			.getListenerCount(CarcassonneListener.class);
		assertEquals("count = " + count, 0, count);
	}

	public void testFireCarcassonneChanged() {

	}
}