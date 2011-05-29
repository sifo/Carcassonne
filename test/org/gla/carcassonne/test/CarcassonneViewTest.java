package org.gla.carcassonne.test;

import junit.framework.TestCase;
import org.gla.carcassonne.Controller;
import org.gla.carcassonne.Model;
import org.gla.carcassonne.View;
import org.gla.carcassonne.ui.SwingView;

public class CarcassonneViewTest extends TestCase {
	
	View view;
	Controller controller;
	Model model;

	protected void setUp() {
		model = new Model();
		controller = new Controller(model);
		view = new SwingView(controller);	
	}

	public void testCarcassonneView() {
		assertNotNull(view.getController());	
	}
}