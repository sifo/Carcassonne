package org.gla.carcassonne;
import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.CarcassonneModel;

public class CarcassonneController {
	private CarcassonneView carcassonneView;
	private CarcassonneModel carcassonneModel;

	public CarcassonneController(CarcassonneModel model) {
		carcassonneModel = model;
		carcassonneView = new JFrameCarcassonne(this);
		addListenersToModel();
	}

	private void addListenersToModel() {
		carcassonneModel.addCarcassonneListener(carcassonneView);
	}

	public void displayViews() {
		carcassonneView.display();
	}

	public void closeViews() {
		carcassonneView.close();
	}
}
