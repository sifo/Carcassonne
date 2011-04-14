package org.gla.carcassonne.test;

import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.CarcassonneListener;
import org.gla.carcassonne.CarcassonneController;
import org.gla.carcassonne.JFrameCarcassonne;
import junit.framework.TestCase;
import java.util.EventListener;
import javax.swing.event.EventListenerList;

public class CarcassonneModelTest extends TestCase {
	
	private CarcassonneModel carcassonneModel;
	private CarcassonneController carcassonneController;
	private CarcassonneListener carcassonneListener;

	protected void setUp() {
		carcassonneModel = new CarcassonneModel();
		carcassonneController = new CarcassonneController(carcassonneModel);
		carcassonneListener = new JFrameCarcassonne(carcassonneController);
	}

	public void CarcassonneModelTest() {

	}

	public void testAddCarcassonneListener() {
		carcassonneModel = new CarcassonneModel();
		carcassonneModel.addCarcassonneListener(carcassonneListener);
		int count = carcassonneModel.getListeners()
			.getListenerCount(CarcassonneListener.class);
		
		EventListener [] eventListeners = carcassonneModel.getListeners()
				.getListeners(CarcassonneListener.class);

		CarcassonneListener lastListener = 
			(CarcassonneListener) eventListeners[count - 1];

		assertEquals(carcassonneListener, lastListener);
	}

	public void testRemoveCarcassonneListener() {
		carcassonneModel = new CarcassonneModel();
		carcassonneModel.addCarcassonneListener(carcassonneListener);
		int count = carcassonneModel.getListeners()
			.getListenerCount(CarcassonneListener.class);
		assertEquals("count = " + count, 1, count);

		carcassonneModel.removeCarcassonneListener(carcassonneListener);
		count = carcassonneModel.getListeners()
			.getListenerCount(CarcassonneListener.class);
		assertEquals("count = " + count, 0, count);
	}

	public void testFireCarcassonneChanged() {

	}
}
