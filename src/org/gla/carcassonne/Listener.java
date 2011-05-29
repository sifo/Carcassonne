package org.gla.carcassonne;

import java.util.EventListener;

import org.gla.carcassonne.events.AddTileEvent;
import org.gla.carcassonne.events.CardBackEvent;
import org.gla.carcassonne.events.ConfigDialogEvent;
import org.gla.carcassonne.events.DrawBoardEvent;
import org.gla.carcassonne.events.DrawNextTileEvent;
import org.gla.carcassonne.events.DrawPlayerListEvent;
import org.gla.carcassonne.events.ListenerOnCurrentTileEvent;
import org.gla.carcassonne.events.LobbyDialogEvent;
import org.gla.carcassonne.events.LockConfirmButtonEvent;
import org.gla.carcassonne.events.LockRotateButtonsEvent;
import org.gla.carcassonne.events.PlacePieceOnTileEvent;
import org.gla.carcassonne.events.RemainingTileEvent;
import org.gla.carcassonne.events.TellCantAddTileEvent;
import org.gla.carcassonne.events.UnlockConfirmButtonEvent;
import org.gla.carcassonne.events.UnlockRotateButtonsEvent;

public interface Listener extends EventListener {
	public void remainingTile(RemainingTileEvent event);
	public void drawNextTile(DrawNextTileEvent event);
	public void configDialog(ConfigDialogEvent event);
	public void lobbyDialog(LobbyDialogEvent event);
	public void drawBoard(DrawBoardEvent event);
	public void addTile(AddTileEvent event);
	public void tellCantAddTile(TellCantAddTileEvent event);
	public void drawPlayerList(DrawPlayerListEvent event);
	public void cardBack(CardBackEvent cardBackEvent);
	public void listenerOnCurrentTile(
			ListenerOnCurrentTileEvent event);
	public void placePieceOnTile(PlacePieceOnTileEvent event);
	public void unlockConfirmButton(UnlockConfirmButtonEvent event);
	public void lockConfirmButton(LockConfirmButtonEvent event);
	public void unlockRotateButtons(UnlockRotateButtonsEvent event);
	public void lockRotateButtons(LockRotateButtonsEvent event);
}
