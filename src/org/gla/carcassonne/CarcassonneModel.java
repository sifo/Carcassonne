package org.gla.carcassonne;

import java.io.IOException;

import javax.swing.event.EventListenerList;

import org.gla.carcassonne.entities.Game;
import org.gla.carcassonne.events.AddTileEvent;
import org.gla.carcassonne.events.BoardEvent;
import org.gla.carcassonne.events.CantAddTileEvent;
import org.gla.carcassonne.events.CardBackEvent;
import org.gla.carcassonne.events.ConfigDialogEvent;
import org.gla.carcassonne.events.ListenerOnCurrentTileEvent;
import org.gla.carcassonne.events.LobbyDialogEvent;
import org.gla.carcassonne.events.LockConfirmButtonEvent;
import org.gla.carcassonne.events.LockRotateButtonsEvent;
import org.gla.carcassonne.events.NextTileEvent;
import org.gla.carcassonne.events.PlacePieceOnTileEvent;
import org.gla.carcassonne.events.PlayersEvent;
import org.gla.carcassonne.events.RemainingTileEvent;
import org.gla.carcassonne.events.RotateLeftEvent;
import org.gla.carcassonne.events.RotateRightEvent;
import org.gla.carcassonne.events.UnlockConfirmButtonEvent;
import org.gla.carcassonne.events.UnlockRotateButtonsEvent;
import org.gla.carcassonne.managers.NetworkManager;
import org.gla.carcassonne.managers.PlayerManager;
import org.gla.carcassonne.managers.TileManager;

public class CarcassonneModel implements Game {
	private EventListenerList listeners;
	private TileManager tileManager;
	private PlayerManager playerManager;
	private NetworkManager networkManager;
	private boolean showedResults;

	public CarcassonneModel() {
		tileManager = new TileManager(this);
		playerManager = new PlayerManager(this);
		listeners = new EventListenerList();
		networkManager = null;
		showedResults = false;
	}

	public void addCarcassonneListener(CarcassonneListener listener) {
		listeners.add(CarcassonneListener.class, listener);
	}

	public void removeCarcassonneListener(CarcassonneListener listener) {
		listeners.remove(CarcassonneListener.class, listener);
	}

	public void fireRemainingTile() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.remainingTile(new RemainingTileEvent(this,
					getTileManager().getNumberOfTileRemaining()));
		}
	}

	public void fireAddTile() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.addTile(new AddTileEvent(this, tileManager
					.getCurrentTile()));
		}
	}

	public void fireNextTile() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.nextTile(new NextTileEvent(this, tileManager
					.getCurrentTile()));
		}
	}

	public void firePlacePieceOnTile() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.placePieceOnTile(new PlacePieceOnTileEvent(this, 
					tileManager.getCurrentTile()));
		}
	}

	public void fireRotateLeft() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.rotateLeft(new RotateLeftEvent(this, tileManager
					.getCurrentTile().getRotationCount()));
		}
	}

	public void fireRotateRight() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.rotateRight(new RotateRightEvent(this, tileManager
					.getCurrentTile().getRotationCount()));
		}
	}

	public EventListenerList getListeners() {
		return listeners;
	}

	public void fireConfigDialog() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.configDialog(new ConfigDialogEvent(this));
		}
	}
	
	public void fireLobbyDialog() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.lobbyDialog(new LobbyDialogEvent(this));
		}
	}

	public void fireBoard() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.board(new BoardEvent(this, getTileManager().getBoard()
					.getBoard()));
		}
	}

	public void quit() {

	}

	public void save() {

	}

	public void start() {
		fireConfigDialog();
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

	public void play() {
		fireBoard();
		getTileManager().putFirstTileOnBoard();
	}
	
	public NetworkManager getNetworkManager() {
		return networkManager;
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public TileManager getTileManager() {
		return tileManager;
	}

	public void fireCantAddTile(int x, int y) {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.cantAddTile(new CantAddTileEvent(this, x, y));
		}
	}

	public void firePlayers() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.players(new PlayersEvent(this, playerManager.getPlayers(),
					playerManager.getCurrentPlayer()));
		}
	}

	public void fireCardBack() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.cardBack(new CardBackEvent(this));
		}

	}

	public void fireListenerOnCurrentTile() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.listenerOnCurrentTile(new ListenerOnCurrentTileEvent(this,
					tileManager.getCurrentTile().getxOnBoard(),
					tileManager.getCurrentTile().getyOnBoard()));
		}
	}

	public boolean isShowedResults() {
		return showedResults;
	}

	public void setShowedResults(boolean showedResults) {
		this.showedResults = showedResults;
	}		
	
	public void newGame() {
		tileManager = new TileManager(this);
		playerManager = new PlayerManager(this);
		listeners = new EventListenerList();
		networkManager = null;
		showedResults = false;
		start();
		fireBoard();
		firePlayers();
	}

	public void fireLockConfirmButton() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.lockConfirmButton(new LockConfirmButtonEvent(this));
		}
	}

	public void fireUnlockConfirmButton() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.unlockConfirmButton(new UnlockConfirmButtonEvent(this));
		}
	}
	
	public void fireLockRotateButtons() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.lockRotateButtons(new LockRotateButtonsEvent(this));
		}
	}

	public void fireUnlockRotateButtons() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.unlockRotateButtons(new UnlockRotateButtonsEvent(this));
		}
	}
}