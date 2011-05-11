package org.gla.carcassonne.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.gla.carcassonne.CarcassonneController;
import org.gla.carcassonne.CarcassonneView;
import org.gla.carcassonne.events.AddTileEvent;
import org.gla.carcassonne.events.BoardEvent;
import org.gla.carcassonne.events.CantAddTileEvent;
import org.gla.carcassonne.events.CardBackEvent;
import org.gla.carcassonne.events.ConfigDialogEvent;
import org.gla.carcassonne.events.LockConfirmButtonEvent;
import org.gla.carcassonne.events.NextTileEvent;
import org.gla.carcassonne.events.PlayersEvent;
import org.gla.carcassonne.events.RemainingTileEvent;
import org.gla.carcassonne.events.RotateLeftEvent;
import org.gla.carcassonne.events.RotateRightEvent;
import org.gla.carcassonne.events.UnlockConfirmButtonEvent;

public class SwingCarcassonneView extends CarcassonneView {

	private JFrameCarcassonne jframe;
	private final static String TITLE = "Carcassonne";
	private final static int WIDTH = 1000;
	private final static int HEIGHT = 750;

	public SwingCarcassonneView(CarcassonneController controller) {
		super(controller);
		jframe = new JFrameCarcassonne(TITLE, this);
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

	public JFrame getJFrame() {
		return jframe;
	}
	
	public void remainingTile(RemainingTileEvent event) {
		jframe.remainingTileNumber(event);
	}

	public void nextTile(NextTileEvent event) {
		// jframe.getJPanelTile().setTile(event.getNewTile());
		jframe.pick(event);
	}

	public void configDialog(ConfigDialogEvent event) {
		jframe.showNbPlayer();
	}

	public void addTile(AddTileEvent event) {
		jframe.addTile(event);
	}

	public void newGame() {
		// interfaceGui.newGame();
		jframe.setVisible(false);
		jframe = new JFrameCarcassonne(TITLE, this);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public void board(BoardEvent event) {
		jframe.board(event);
	}

	public void cantAddTile(CantAddTileEvent event) {
		jframe.cantAddTile(event);
	}

	public void rotateLeft(RotateLeftEvent event) {
		jframe.rotateLeft(event);
	}

	public void rotateRight(RotateRightEvent event) {
		jframe.rotateRight(event);
	}
	

	public void players(PlayersEvent event){
		jframe.players(event);
	}

	public void unlockConfirmButton(UnlockConfirmButtonEvent event) {
		jframe.unlockConfirmButton();
	}
	
	public void lockConfirmButton(LockConfirmButtonEvent event) {
		jframe.lockConfirmButton();
	}
	
	public void cardBack(CardBackEvent event){
		jframe.cardBack(event);
	}

}