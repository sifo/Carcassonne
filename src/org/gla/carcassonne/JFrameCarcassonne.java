package org.gla.carcassonne;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import org.gla.carcassonne.JPanelBoard;
import org.gla.carcassonne.JPanelTile;

public class JFrameCarcassonne extends JFrame {

	private JPanel jpanelBoard;
	private JPanel jpanelTile;
	private JLabel remainingTileNumberLabel;

	public JFrameCarcassonne(String title) {
		super(title);
		jpanelBoard = new JPanelBoard();
		jpanelBoard.setLayout(new GridLayout(1, 1));
		jpanelTile = new JPanelTile("res/drawable/tile-x.png");
		remainingTileNumberLabel = new JLabel("10 Remaining");
		getContentPane().add(jpanelBoard);
		getContentPane().add(remainingTileNumberLabel);
		getContentPane().add(jpanelTile); 
	}

	public JPanel getJPanelTile() {
		return jpanelTile;
	}

	public JPanel getJPanelBoard() {
		return jpanelBoard;
	}
}
