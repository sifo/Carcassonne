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

	public EventListenerList getListeners() {
		return listeners;
	}
}
