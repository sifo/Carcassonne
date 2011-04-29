package org.gla.carcassonne;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

public class JFrameCarcassonne extends JFrame {
	public static final int GRID_LENGTH = 6;
	public static final int GRID_HEIGHT = 6;
	
	private JPanelBoard jpanelBoard;
	private JPanelTile jpanelTile;
	private JMenuBar menuBar;
	private JLabel remainingTileNumberLabel;

	public JFrameCarcassonne(String title) {
		super(title);
		getContentPane().setLayout(new GridBagLayout());
		
		jpanelBoard = new JPanelBoard(GRID_LENGTH, GRID_HEIGHT);
		
		jpanelTile = new JPanelTile(new Tile(TileType.TILE_X));
		remainingTileNumberLabel = new JLabel();
		menuBar = new JPanelMenu();
		addComponents();
	}

	private void addComponents() {
		setJMenuBar(menuBar);
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		// Plateau de jeu principal : échiquier sur lequel on posera les tuiles
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridheight = 3;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipady = 500;	// Board hauteur
		constraints.ipadx = 500;	// Board largeur
		jpanelBoard.setBorder(BorderFactory.createLineBorder(Color.black));
		jpanelBoard.setOpaque(true); 
		getContentPane().add(jpanelBoard, constraints);
		
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 0;
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

	public JPanelTile getJPanelTile() {
		return jpanelTile;
	}

	public JPanelBoard getJPanelBoard() {
		return jpanelBoard;
	}

	public JLabel getRemainingTileNumber() {
		return remainingTileNumberLabel;
	}
}
