package org.gla.carcassonne;

import java.io.IOException;

import javax.swing.event.EventListenerList;

import org.gla.carcassonne.entities.Game;
import org.gla.carcassonne.events.AddTileEvent;
import org.gla.carcassonne.events.TellCantAddTileEvent;
import org.gla.carcassonne.events.CardBackEvent;
import org.gla.carcassonne.events.ConfigDialogEvent;
import org.gla.carcassonne.events.DrawBoardEvent;
import org.gla.carcassonne.events.DrawNextTileEvent;
import org.gla.carcassonne.events.ListenerOnCurrentTileEvent;
import org.gla.carcassonne.events.LobbyDialogEvent;
import org.gla.carcassonne.events.LockConfirmButtonEvent;
import org.gla.carcassonne.events.LockRotateButtonsEvent;
import org.gla.carcassonne.events.PlacePieceOnTileEvent;
import org.gla.carcassonne.events.DrawPlayerListEvent;
import org.gla.carcassonne.events.RemainingTileEvent;
import org.gla.carcassonne.events.UnlockConfirmButtonEvent;
import org.gla.carcassonne.events.UnlockRotateButtonsEvent;
import org.gla.carcassonne.managers.NetworkManager;
import org.gla.carcassonne.managers.PlayerManager;
import org.gla.carcassonne.managers.TileManager;

public class Model implements Game {
	private EventListenerList listeners;
	private TileManager tileManager;
	private PlayerManager playerManager;
	private NetworkManager networkManager;
	private boolean showedResults;
	private Listener [] listenerList;

	public Model() {
		tileManager = new TileManager(this);
		playerManager = new PlayerManager(this);
		listeners = new EventListenerList();
		networkManager = null;
		showedResults = false;
	}

	public void addCarcassonneListener(Listener listener) {
		listeners.add(Listener.class, listener);
		listenerList = (Listener[]) listeners
				.getListeners(Listener.class);
	}

	public void removeCarcassonneListener(Listener listener) {
		listeners.remove(Listener.class, listener);
		listenerList = (Listener[]) listeners
				.getListeners(Listener.class);
	}

	public void fireRemainingTile() {
		for (Listener listener : listenerList) {
			listener.remainingTile(new RemainingTileEvent(this,
					getTileManager().getNumberOfTileRemaining()));
		}
	}

	public void fireAddTile() {
		for (Listener listener : listenerList) {
			listener.addTile(new AddTileEvent(this, tileManager
					.getCurrentTile()));
		}
	}

	public void fireDrawNextTile() {
		for (Listener listener : listenerList) {
			listener.drawNextTile(new DrawNextTileEvent(this, tileManager
					.getCurrentTile()));
		}
	}

	public void firePlacePieceOnTile() {
		for (Listener listener : listenerList) {
			listener.placePieceOnTile(new PlacePieceOnTileEvent(this, 
					tileManager.getCurrentTile()));
		}
	}

	public void fireConfigDialog() {
		for (Listener listener : listenerList) {
			listener.configDialog(new ConfigDialogEvent(this));
		}
	}
	
	public void fireLobbyDialog() {
		for (Listener listener : listenerList) {
			listener.lobbyDialog(new LobbyDialogEvent(this));
		}
	}

	public void fireBoard() {
		for (Listener listener : listenerList) {
			listener.drawBoard(new DrawBoardEvent(this, getTileManager().getBoard()
					.getBoard()));
		}
	}

	public void fireCantAddTile(int x, int y) {
		for (Listener listener : listenerList) {
			listener.tellCantAddTile(new TellCantAddTileEvent(this, x, y));
		}
	}

	public void fireDrawPlayerList() {
		for (Listener listener : listenerList) {
			listener.drawPlayerList(new DrawPlayerListEvent(this, playerManager.getPlayers(),
					playerManager.getCurrentPlayer()));
		}
	}

	public void fireCardBack() {
		for (Listener listener : listenerList) {
			listener.cardBack(new CardBackEvent(this));
		}

	}

	public void fireListenerOnCurrentTile() {
		for (Listener listener : listenerList) {
			listener.listenerOnCurrentTile(new ListenerOnCurrentTileEvent(this,
					tileManager.getCurrentTile().getxOnBoard(),
					tileManager.getCurrentTile().getyOnBoard()));
		}
	}

	public void fireLockConfirmButton() {
		for (Listener listener : listenerList) {
			listener.lockConfirmButton(new LockConfirmButtonEvent(this));
		}
	}

	public void fireUnlockConfirmButton() {
		for (Listener listener : listenerList) {
			listener.unlockConfirmButton(new UnlockConfirmButtonEvent(this));
		}
	}
	
	public void fireLockRotateButtons() {
		for (Listener listener : listenerList) {
			listener.lockRotateButtons(new LockRotateButtonsEvent(this));
		}
	}

	public void fireUnlockRotateButtons() {
		for (Listener listener : listenerList) {
			listener.unlockRotateButtons(new UnlockRotateButtonsEvent(this));
		}
	}

	public void quit() {

	}

	public void save() {

	}

	public void start() {
		fireConfigDialog();
	}
	
	public void newGame() {
		start();
		//fireBoard();
		//firePlayers();
	}
	
	public void play() {
		tileManager = new TileManager(this);
		playerManager = new PlayerManager(this);
		networkManager = null;
		showedResults = false;
		fireBoard();
		//firePlayers();
		getTileManager().putFirstTileOnBoard();
	}
	
	public void startMultiplayer() {
		try {
			networkManager = new NetworkManager(this);
			fireLobbyDialog();
			fireBoard();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public NetworkManager getNetworkManager() {
		return networkManager;
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}
	
	public EventListenerList getListeners() {
		return listeners;
	}
	
	public TileManager getTileManager() {
		return tileManager;
	}
	
	public boolean isShowedResults() {
		return showedResults;
	}

	public void setShowedResults(boolean showedResults) {
		this.showedResults = showedResults;
	}		
}