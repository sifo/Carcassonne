package org.gla.carcassonne;

import java.util.EventListener;

import org.gla.carcassonne.events.AddTileEvent;
import org.gla.carcassonne.events.BoardEvent;
import org.gla.carcassonne.events.CantAddTileEvent;
import org.gla.carcassonne.events.ConfigDialogEvent;
import org.gla.carcassonne.events.NextTileEvent;
import org.gla.carcassonne.events.RemainingTileEvent;
import org.gla.carcassonne.events.RotateLeftEvent;
import org.gla.carcassonne.events.RotateRightEvent;

public interface CarcassonneListener extends EventListener {
	public void remainingTile(RemainingTileEvent event);
	public void nextTile(NextTileEvent event);
	public void configDialog(ConfigDialogEvent event);
	public void board(BoardEvent event);
	public void addTile(AddTileEvent event);
	public void cantAddTile(CantAddTileEvent event);
	public void rotateLeft(RotateLeftEvent event);
	public void rotateRight(RotateRightEvent event);
}