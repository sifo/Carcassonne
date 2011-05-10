package org.gla.carcassonne;

import javax.swing.event.EventListenerList;

import org.gla.carcassonne.entities.Game;
import org.gla.carcassonne.events.AddTileEvent;
import org.gla.carcassonne.events.BoardEvent;
import org.gla.carcassonne.events.CantAddTileEvent;
import org.gla.carcassonne.events.ConfigDialogEvent;
import org.gla.carcassonne.events.NextTileEvent;
import org.gla.carcassonne.events.RemainingTileEvent;
import org.gla.carcassonne.events.RotateLeftEvent;
import org.gla.carcassonne.events.RotateRightEvent;
import org.gla.carcassonne.managers.PlayerManager;
import org.gla.carcassonne.managers.TileManager;

public class CarcassonneModel implements Game {
	private EventListenerList listeners;
	private TileManager tileManager;
	private PlayerManager playerManager;
	
	public CarcassonneModel() {
		tileManager = new TileManager(this);
		playerManager = new PlayerManager();
		listeners = new EventListenerList();
	}
	
	public void addCarcassonneListener(CarcassonneListener listener){
		listeners.add(CarcassonneListener.class, listener);
	}

	public void removeCarcassonneListener(CarcassonneListener listener){
		listeners.remove(CarcassonneListener.class, listener);
	}

	public void fireRemainingTile() {
		CarcassonneListener[] listenerList = (CarcassonneListener[])listeners
					.getListeners(CarcassonneListener.class);
		for(CarcassonneListener listener : listenerList){
			listener.remainingTile(new RemainingTileEvent(this, 
				getTileManager().getNumberOfTileRemaining()));
		}
	}
	
	public void fireAddTile() {
		CarcassonneListener[] listenerList = (CarcassonneListener[])listeners
					.getListeners(CarcassonneListener.class);
		for(CarcassonneListener listener : listenerList){
			listener.addTile(new AddTileEvent(this, tileManager.getCurrentTile()));
		}
		fireNextTile();
	}

	public void fireNextTile() {
		CarcassonneListener[] listenerList = (CarcassonneListener[])listeners
					.getListeners(CarcassonneListener.class);
		for(CarcassonneListener listener : listenerList){
			listener.nextTile(new NextTileEvent(this, 
				getTileManager().getNextTile()));
		}
	}
	
	public void fireExitGame() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			//listener.exitGame(new ExitGameEvent(this));
		}
	}
	
	public void fireNextPlayer() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			//listener.NextPlayer(new NextPlayerEvent(this));
		}
	}
	
	public void firePickTile() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			//listener.PickTile(new PickTileEvent(this));
		}
	}
	
	public void firePlacePieceOnTile() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			//listener.PlacePieceOnTile(new PlacePieceOnTileEvent(this));
		}
	}
	
	public void fireRotateLeft() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.rotateLeft(new RotateLeftEvent(this, 
					tileManager.getCurrentTile().getRotationCount()));
		}
	}
	
	public void fireRotateRight() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.rotateRight(new RotateRightEvent(this, 
					tileManager.getCurrentTile().getRotationCount()));
		}
	}
	
	public void fireAddPlayer() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			//listener.AddPlayer(new AddPlayerEvent(this));
		}
	}
	
	public void fireRemovePlayer() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			//listener.RemovePlayer(new RemovePlayerEvent(this));
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
	
	public void fireBoard() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			 listener.board(new BoardEvent(this, getTileManager().getBoard().getBoard()));
		}
	}
	
	public void quit() {

	}

	public void save() {

	}

	public void start() {
		//model.fireConfigDialog();
		fireBoard();
		getTileManager().putFirstTileOnBoard();
	}

	public void play() {

	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public TileManager getTileManager() {
		return tileManager;
	}

	public void fireCantAddTile() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			listener.cantAddTile(new CantAddTileEvent(this));
		}
	}
}