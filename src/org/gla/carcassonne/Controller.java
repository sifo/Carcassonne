package org.gla.carcassonne;

import java.awt.event.MouseEvent;
import java.util.List;

import org.gla.carcassonne.entities.Player;
import org.gla.carcassonne.entities.Tile;
import org.gla.carcassonne.managers.NetworkManager;
import org.gla.carcassonne.ui.MultiplayerLobbyDialog;
import org.gla.carcassonne.ui.SwingView;

public class Controller {
	private View view;
	private Model model;

	public Controller(Model model) {
		this.model = model;
		this.view = new SwingView(this);
		addListenersToModel();
	}

	public void addListenersToModel() {
		model.addCarcassonneListener(view);
	}

	public void displayViews() {
		view.display();
	}

	public void closeViews() {
		view.close();
	}

	public View getView(){
		return view;
	}

	public Model getModel() {
		return model;
	}
	
	public void notifyRotateLeft() {
		model.getTileManager().rotateLeft();
	}
	
	public void notifyRotateRight() {
		model.getTileManager().rotateRight();
	}

	public void notifyPlayerList(List<String> names) {
		model.getPlayerManager().setPlayersFromNames(names);
		
	}

	public void notifyPlacePiece(int x, int y, MouseEvent arg0) {
		model.getTileManager().placePieceOnTile(x, y, arg0);
	}

	public void notifyQuitGame() {
		System.exit(0);
	}
	
	public void notifyStartMultiplayer() {
		model.startMultiplayer();
	}

	public void notifyNewGame() {
		model.newGame();
	}
	
	public void notifyReady() {
		model.getNetworkManager().getClient().ready();
	}

	public void notifyPlay() {
		model.play();
	}
	
	public void notifyAddTile(int x, int y) {
		Tile currentTile = model.getTileManager().getCurrentTile();
		NetworkManager networkManager = model.getNetworkManager();
		if (networkManager != null) {
			if (networkManager.getClient().getToken() > 0) {
				synchronized(this) {
					networkManager.getClient().move(currentTile, currentTile, currentTile, currentTile, currentTile);
					notifyAll();
				}
			}
			else {
				System.out.println("Ce n'est pas votre tour de jouer !");
				return;
			}
		}
		model.getTileManager().getBoard().add(x, y, currentTile);
	}
	
	public void notifyConnexion(MultiplayerLobbyDialog lobby) {
		try {
			String host = lobby.getServerAddress();
			int port = lobby.getServerPort();
			if (model.getNetworkManager().setConnexion(host, port, lobby)) {
				lobby.setEnableConnectButton(false);
				lobby.setEnableReadyButton(true);
				lobby.setConsoleMessage("Connexion réussie au serveur "+host+":"+port);
			} else
				lobby.setConsoleMessage("Connexion échouée : hôte inconnu");
		} catch (NumberFormatException e) {
			lobby.setConsoleMessage("== ERREUR == Mauvais format de données adresse serveur/port");
		}
	}

	public void notifyConfirmAction() {
		Player player = model.getPlayerManager().getCurrentPlayer();
		if(model.getPlayerManager().getCurrentPlayerhasPlacedPiece())
			player.setPieceCount(player.getPieceCount() - 1);
		if(model.getTileManager().getCurrentPlayerHasPlacedTile()) {
			model.getTileManager().resolveZoneClose();
			if(model.getTileManager().getNumberOfTileRemaining() == 0)
				model.getTileManager().setGameFinished(true);
			if(!model.getTileManager().isGameFinished()) {
				model.getPlayerManager().setNextPlayer();
				model.getTileManager().getNextTile();
				model.getPlayerManager().setCurrentPlayerhasPlacedPiece(false);
			} else {
				if(!model.isShowedResults()) {
					model.setShowedResults(true);
					model.getTileManager().resolveEndGamePoint();
					model.fireLockConfirmButton();
					model.fireLockRotateButtons();
				}
			}
		}
	}
}