package org.gla.carcassonne;
import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.CarcassonneController;

public class Main {
	public static void main(String[]args) {
		CarcassonneModel model = new CarcassonneModel();
		CarcassonneController controller = new CarcassonneController(model);
		controller.displayViews();
		model.fireFirstCardPicked();
		model.fireRemainingTile();
		model.fireNextTile();
	}
}
