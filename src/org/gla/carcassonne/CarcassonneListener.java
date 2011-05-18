package org.gla.carcassonne;

import java.util.EventListener;

import org.gla.carcassonne.events.*;

public interface CarcassonneListener extends EventListener {
	public void remainingTile(RemainingTileEvent event);
	public void nextTile(NextTileEvent event);
	public void configDialog(ConfigDialogEvent event);
	public void lobbyDialog(LobbyDialogEvent event);
	public void board(BoardEvent event);
	public void addTile(AddTileEvent event);
	public void cantAddTile(CantAddTileEvent event);
	public void rotateLeft(RotateLeftEvent event);
	public void rotateRight(RotateRightEvent event);
	public void players(PlayersEvent event);
	public void unlockConfirmLockRotate(UnlockConfirmLockRotateEvent event);
	public void lockConfirmUnlockRotate(LockConfirmUnlockRotateEvent lockConfirmButtonEvent);
	public void cardBack(CardBackEvent cardBackEvent);
	public void listenerOnCurrentTile(
			ListenerOnCurrentTileEvent listenerOnCurrentTileEvent);
	public void placePieceOnTile(PlacePieceOnTileEvent placePieceOnTileEvent);
}