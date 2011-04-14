package org.gla.carcassonne;

import org.gla.carcassonne.Carcassonne;
import org.gla.carcassonne.CarcassonneListener;
import javax.swing.event.EventListenerList;

public class CarcassonneModel extends Carcassonne {
	private EventListenerList listeners;

	public CarcassonneModel() {
		listeners = new EventListenerList();
	}
	
	public void addCarcassonneListener(CarcassonneListener listener){
		listeners.add(CarcassonneListener.class, listener);
	}

	public void removeCarcassonneListener(CarcassonneListener listener){
		listeners.remove(CarcassonneListener.class, listener);
	}

	public void fireCarcassonneChanged() {
	}

	public EventListenerList getListeners() {
		return listeners;
	}
}
