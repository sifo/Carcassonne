package org.gla.carcassonne.test;

import java.util.EventListener;

import junit.framework.TestCase;
import org.gla.carcassonne.Controller;
import org.gla.carcassonne.Listener;
import org.gla.carcassonne.Model;
import org.gla.carcassonne.ui.SwingView;

public class CarcassonneModelTest extends TestCase {
	
	private Model model;
	private Controller controller;
	private Listener listener;

	protected void setUp() {
		model = new Model();
		controller = new Controller(model);
		listener = new SwingView(controller);
	}

	public void testCarcassonneModel() {
		assertNotNull(model.getListeners());
	}

	public void testAddCarcassonneListener() {
		model = new Model();
		model.addCarcassonneListener(listener);
		int count = model.getListeners()
			.getListenerCount(Listener.class);
		
		EventListener [] eventListeners = model.getListeners()
				.getListeners(Listener.class);

		Listener lastListener = 
			(Listener) eventListeners[count - 1];

		assertEquals(listener, lastListener);
	}

	public void testRemoveCarcassonneListener() {
		model = new Model();
		model.addCarcassonneListener(listener);
		int count = model.getListeners()
			.getListenerCount(Listener.class);
		assertEquals("count = " + count, 1, count);

		model.removeCarcassonneListener(listener);
		count = model.getListeners()
			.getListenerCount(Listener.class);
		assertEquals("count = " + count, 0, count);
	}
	
	public void testFireCarcassonneChanged() {

	}
}