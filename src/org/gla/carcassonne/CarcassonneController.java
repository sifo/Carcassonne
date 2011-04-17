package org.gla.carcassonne;

import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.CarcassonneModel;

public class CarcassonneController {
	private CarcassonneView carcassonneView;
	private CarcassonneModel carcassonneModel;

	public CarcassonneController(CarcassonneModel model) {
		carcassonneModel = model;
		carcassonneView = new SwingCarcassonneView(this);
		addListenersToModel();
	}

	public void addListenersToModel() {
		carcassonneModel.addCarcassonneListener(carcassonneView);
	}

	public void displayViews() {
		carcassonneView.display();
	}

	public void closeViews() {
		carcassonneView.close();
	}

	public CarcassonneView getCarcassonneView(){
		return carcassonneView;
	}

	public CarcassonneModel getCarcassonneModel() {
		return carcassonneModel;
	}
}
