package org.gla.carcassonne;

import java.util.EventListener;

public interface CarcassonneListener extends EventListener {
	public void firstCardPicked(FirstCardPickedEvent event);
	public void remainingTile(RemainingTileEvent event);
	public void nextTile(NextTileEvent event);
}
