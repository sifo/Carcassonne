package org.gla.carcassonne.test;

import junit.framework.TestCase;
import org.gla.carcassonne.CarcassonneController;
import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.ui.SwingCarcassonneView;

public class CarcassonneViewTest extends TestCase {
	
	CarcassonneView view;
	CarcassonneController controller;
	CarcassonneModel model;

	protected void setUp() {
		model = new CarcassonneModel();
		controller = new CarcassonneController(model);
		view = new SwingCarcassonneView(controller);	
	}

	public void testCarcassonneView() {
		assertNotNull(view.getController());	
	}
}