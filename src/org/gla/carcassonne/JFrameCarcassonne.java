package org.gla.carcassonne;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import org.gla.carcassonne.JPanelBoard;
import org.gla.carcassonne.JPanelTile;

public class JFrameCarcassonne extends JFrame {

	private JPanel jpanelBoard;
	private JPanel jpanelTile;
	private JLabel remainingTileNumberLabel;

	public JFrameCarcassonne(String title) {
		super(title);
		getContentPane().setLayout(new GridBagLayout());
		jpanelBoard = new JPanelBoard();
		jpanelBoard.setLayout(new GridLayout(1, 1));
		jpanelTile = new JPanelTile("res/drawable/tile-x.png");
		remainingTileNumberLabel = new JLabel("10 Remaining");
		addComponents();
	}

	private void addComponents() {
		GridBagConstraints constraints = new GridBagConstraints();
		
		// Plateau de jeu principal : échiquier sur lequel on posera les tuiles
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridheight = 3;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipady = 400;	// Board hauteur
		constraints.ipadx = 435;	// Board largeur
		jpanelBoard.setBorder(BorderFactory.createLineBorder(Color.black));//.setBackground(Color.RED);
		jpanelBoard.setOpaque(true); 
		getContentPane().add(jpanelBoard, constraints);
		
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 0;
		remainingTileNumberLabel.setBackground(Color.WHITE);
		remainingTileNumberLabel.setOpaque(true); 
		getContentPane().add(remainingTileNumberLabel, constraints);

		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.ipady = 90;
		constraints.ipadx = 90;
		jpanelTile.setBackground(Color.WHITE);
		jpanelTile.setOpaque(true); 
		getContentPane().add(jpanelTile, constraints); 
	}

	public JPanel getJPanelTile() {
		return jpanelTile;
	}

	public JPanel getJPanelBoard() {
		return jpanelBoard;
	}
}
