package org.gla.carcassonne;

import java.awt.event.MouseEvent;
import java.util.List;

import org.gla.carcassonne.entities.Tile;
import org.gla.carcassonne.ui.SwingCarcassonneView;

public class CarcassonneController {
	private CarcassonneView carcassonneView;
	private CarcassonneModel carcassonneModel;

	public CarcassonneController(CarcassonneModel model) {
		carcassonneModel = model;
		carcassonneView = new SwingCarcassonneView(this);
		addListenersToModel();
		model.start();
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
	
	public void notifyAddTile(int x, int y) {
		Tile currentTile = carcassonneModel.getTileManager().getCurrentTile();
		carcassonneModel.getTileManager().getBoard().add(x, y, currentTile);
	}

	public void notifyRotateLeft() {
		carcassonneModel.getTileManager().rotateLeft();
	}
	
	public void notifyRotateRight() {
		carcassonneModel.getTileManager().rotateRight();
	}

	public void notifyPlayerList(List<String> names) {
		carcassonneModel.getPlayerManager().setPlayersFromNames(names);
		
	}

	public void notifyPlacePiece(int x, int y, MouseEvent arg0) {
		carcassonneModel.getTileManager().placePieceOnTile(x, y, arg0);
	}

	public void notifyConfirmAction() {
		if(carcassonneModel.getTileManager().getCurrentPlayerHasPlacedTile()) {
			carcassonneModel.getPlayerManager().setNextPlayer();
			carcassonneModel.getTileManager().getNextTile();
			carcassonneModel.getPlayerManager().setCurrentPlayerhasPlacedPiece(false);
		}
	}

	
}