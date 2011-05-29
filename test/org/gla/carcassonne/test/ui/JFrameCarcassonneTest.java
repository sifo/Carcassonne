package org.gla.carcassonne.test.ui;

import junit.framework.TestCase;

import org.gla.carcassonne.Controller;
import org.gla.carcassonne.Model;
import org.gla.carcassonne.ui.MainFrame;
import org.gla.carcassonne.ui.SwingView;

public class JFrameCarcassonneTest extends TestCase {
	
	private MainFrame jframeCarcassonne;
	private final static String TITLE = "JFrame test";

	protected void setUp() {
		Model model = new Model();
		Controller controller = new Controller(model);
		SwingView view =  new SwingView(controller);
		jframeCarcassonne = new MainFrame(TITLE, view);
	}

	protected void tearDown() {
		jframeCarcassonne.dispose();
	}

	public void testJFrameCarcassonne() {
//		assertNotNull(jframeCarcassonne.getJPanelBoard());
//		assertNotNull(jframeCarcassonne.getJPanelTile());
	}
}