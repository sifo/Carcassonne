package org.gla.carcassonne.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.gla.carcassonne.Controller;
import org.gla.carcassonne.View;
import org.gla.carcassonne.events.AddTileEvent;
import org.gla.carcassonne.events.TellCantAddTileEvent;
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
import org.gla.carcassonne.events.UnlockConfirmButtonEvent;
import org.gla.carcassonne.events.UnlockRotateButtonsEvent;

public class SwingView extends View {

	private MainFrame jframe;
	private final static String TITLE = "Carcassonne";
	private final static int WIDTH = 1000;
	private final static int HEIGHT = 750;

	public SwingView(Controller controller) {
		super(controller);
		jframe = new MainFrame(TITLE, this);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// jframe.setSize(WIDTH, HEIGHT);
		jframe.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public void close() {
		jframe.dispose();
	}

	public void display() {
		jframe.setVisible(true);
	}

	public MainFrame getJFrame() {
		return jframe;
	}
	
	public void remainingTile(RemainingTileEvent event) {
		jframe.remainingTileNumber(event);
	}

	public void drawNextTile(DrawNextTileEvent event) {
		// jframe.getJPanelTile().setTile(event.getNewTile());
		jframe.drawNextTile(event);
	}

	public void configDialog(ConfigDialogEvent event) {
		jframe.showNbPlayer();
	}
	
	public void lobbyDialog(LobbyDialogEvent event) {
		jframe.startMultiplayer();
	}

	public void addTile(AddTileEvent event) {
		jframe.addTile(event);
	}

	public void newGame() {
		// interfaceGui.newGame();
		jframe.setVisible(false);
		jframe = new MainFrame(TITLE, this);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public void drawBoard(DrawBoardEvent event) {
		jframe.drawBoard(event);
	}

	public void tellCantAddTile(TellCantAddTileEvent event) {
		jframe.tellCantAddTile(event);
	}

	public void drawPlayerList(DrawPlayerListEvent event){
		jframe.drawPlayerList(event);
	}

	public void unlockConfirmButton(UnlockConfirmButtonEvent event) {
		jframe.unlockConfirmButton();
	}
	
	public void lockConfirmButton(LockConfirmButtonEvent event) {
		jframe.lockConfirmButton();
	}
	
	public void unlockRotateButtons(UnlockRotateButtonsEvent event) {
		jframe.unlockRotateButtons();
	}
	
	public void lockRotateButtons(LockRotateButtonsEvent event) {
		jframe.lockRotateButtons();
	}
	
	public void cardBack(CardBackEvent event){
		jframe.cardBack(event);
	}

	public void listenerOnCurrentTile(
			ListenerOnCurrentTileEvent listenerOnCurrentTileEvent) {
		jframe.listenerOnCurrentTile(listenerOnCurrentTileEvent);
	}

	public void placePieceOnTile(PlacePieceOnTileEvent event) {
		jframe.placePieceOnTile(event);
	}
}