package org.gla.carcassonne;

import org.gla.carcassonne.Carcassonne;
import org.gla.carcassonne.CarcassonneListener;
import javax.swing.event.EventListenerList;

public class CarcassonneModel extends Carcassonne {
	private EventListenerList listeners;

	public CarcassonneModel() {
		listeners = new EventListenerList();
		getTileManager().putFirstTileOnBoard();
	}
	
	public void addCarcassonneListener(CarcassonneListener listener){
		listeners.add(CarcassonneListener.class, listener);
	}

	public void removeCarcassonneListener(CarcassonneListener listener){
		listeners.remove(CarcassonneListener.class, listener);
	}

	public void fireFirstCardPicked() {
		CarcassonneListener[] listenerList = (CarcassonneListener[])listeners
					.getListeners(CarcassonneListener.class);
		for(CarcassonneListener listener : listenerList){
			listener.firstCardPicked(new FirstCardPickedEvent(this, 
				getTileManager().getBoard().getBoard()[1][1]));
		}
	}

	public void fireRemainingTile() {
		CarcassonneListener[] listenerList = (CarcassonneListener[])listeners
					.getListeners(CarcassonneListener.class);
		for(CarcassonneListener listener : listenerList){
			listener.remainingTile(new RemainingTileEvent(this, 
				getTileManager().getNumberOfTileRemaining() + " remaining"));
		}
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
			//listener.RotateLeft(new RotateLeftEvent(this));
		}
	}
	
	public void fireRotateRight() {
		CarcassonneListener[] listenerList = (CarcassonneListener[]) listeners
				.getListeners(CarcassonneListener.class);
		for (CarcassonneListener listener : listenerList) {
			//listener.RotateRight(new RotateRightEvent(this));
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
}
