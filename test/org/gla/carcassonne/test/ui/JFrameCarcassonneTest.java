package org.gla.carcassonne.test.ui;

import junit.framework.TestCase;

import org.gla.carcassonne.CarcassonneController;
import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.ui.JFrameCarcassonne;
import org.gla.carcassonne.ui.SwingCarcassonneView;

public class JFrameCarcassonneTest extends TestCase {
	
	private JFrameCarcassonne jframeCarcassonne;
	private final static String TITLE = "JFrame test";

	protected void setUp() {
		CarcassonneModel model = new CarcassonneModel();
		CarcassonneController controller = new CarcassonneController(model);
		SwingCarcassonneView view =  new SwingCarcassonneView(controller);
		jframeCarcassonne = new JFrameCarcassonne(TITLE, view);
	}

	protected void tearDown() {
		jframeCarcassonne.dispose();
	}

	public void testJFrameCarcassonne() {
//		assertNotNull(jframeCarcassonne.getJPanelBoard());
//		assertNotNull(jframeCarcassonne.getJPanelTile());
	}
}