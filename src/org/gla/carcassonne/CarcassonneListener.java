package org.gla.carcassonne;

import java.util.EventListener;

import org.gla.carcassonne.events.FirstCardPickedEvent;
import org.gla.carcassonne.events.NextTileEvent;
import org.gla.carcassonne.events.RemainingTileEvent;

public interface CarcassonneListener extends EventListener {
	public void firstCardPicked(FirstCardPickedEvent event);
	public void remainingTile(RemainingTileEvent event);
	public void nextTile(NextTileEvent event);
}