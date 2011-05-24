package org.gla.carcassonne;

import java.awt.event.MouseEvent;
import java.util.List;

import org.gla.carcassonne.entities.Player;
import org.gla.carcassonne.entities.Tile;
import org.gla.carcassonne.managers.NetworkManager;
import org.gla.carcassonne.ui.MultiplayerLobbyDialog;
import org.gla.carcassonne.ui.SwingCarcassonneView;

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
	
	public void notifyAddTile(int x, int y) {
		Tile currentTile = carcassonneModel.getTileManager().getCurrentTile();
		NetworkManager networkManager = carcassonneModel.getNetworkManager();
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
		Player player = carcassonneModel.getPlayerManager().getCurrentPlayer();
		if(carcassonneModel.getPlayerManager().getCurrentPlayerhasPlacedPiece())
			player.setPieceCount(player.getPieceCount() - 1);
		if(carcassonneModel.getTileManager().getCurrentPlayerHasPlacedTile()) {
			carcassonneModel.getTileManager().resolveZoneClose();
			if(carcassonneModel.getTileManager().getNumberOfTileRemaining() == 0)
				carcassonneModel.getTileManager().setGameFinished(true);
			if(!carcassonneModel.getTileManager().isGameFinished()) {
				carcassonneModel.getPlayerManager().setNextPlayer();
				carcassonneModel.getTileManager().getNextTile();
				carcassonneModel.getPlayerManager().setCurrentPlayerhasPlacedPiece(false);
			} else {
				if(!carcassonneModel.isShowedResults()) {
					carcassonneModel.setShowedResults(true);
					carcassonneModel.getTileManager().resolveEndGamePoint();
					carcassonneModel.fireLockConfirmButton();
					carcassonneModel.fireLockRotateButtons();
				}
			}
		}
	}

	public void notifyQuitGame() {
		System.exit(0);
	}
	
	public void notifyStartMultiplayer() {
		carcassonneModel.startMultiplayer();
	}
	
	public void notifyConnexion(MultiplayerLobbyDialog lobby) {
		try {
			String host = lobby.getServerAddress();
			int port = lobby.getServerPort();
			if (carcassonneModel.getNetworkManager().setConnexion(host, port, lobby)) {
				lobby.setEnableConnectButton(false);
				lobby.setEnableReadyButton(true);
				lobby.setConsoleMessage("Connexion réussie au serveur "+host+":"+port);
			} else
				lobby.setConsoleMessage("Connexion échouée : hôte inconnu");
		} catch (NumberFormatException e) {
			lobby.setConsoleMessage("== ERREUR == Mauvais format de données adresse serveur/port");
		}
	}

	public void notifyNewGame() {
		carcassonneModel.newGame();
	}
	
	public void notifyReady() {
		carcassonneModel.getNetworkManager().getClient().ready();
	}

	public void notifyPlay() {
		carcassonneModel.play();
	}
}