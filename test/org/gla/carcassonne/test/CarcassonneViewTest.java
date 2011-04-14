package org.gla.carcassonne.test;

import junit.framework.TestCase;
import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.JFrameCarcassonne;
import org.gla.carcassonne.CarcassonneController;

public class CarcassonneViewTest extends TestCase {
	
	CarcassonneView view;
	CarcassonneController controller;
	CarcassonneModel model;

	protected void setUp() {
		model = new CarcassonneModel();
		controller = new CarcassonneController(model);
		view = new JFrameCarcassonne(controller);	
	}

	public void testCarcassonneView() {
		assertNotNull(view.getController());	
	}
}
