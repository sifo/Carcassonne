package org.gla.carcassonne;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
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
	private JLabel test;
	private JLabel test2;

	public JFrameCarcassonne(String title) {
		super(title);
		getContentPane().setLayout(new GridBagLayout());
		jpanelBoard = new JPanelBoard();
		jpanelBoard.setLayout(new GridLayout(1, 1));
		jpanelTile = new JPanelTile("res/drawable/tile-x.png");
		remainingTileNumberLabel = new JLabel("10 Remaining");
		test = new JLabel("test");
		test2 = new JLabel("test2");
		addComponents();
	}

	private void addComponents() {
		GridBagConstraints constraints = new GridBagConstraints();

		/*
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipady = 450;
		jpanelBoard.setBackground(Color.RED);
		jpanelBoard.setOpaque(true); 
		getContentPane().add(jpanelBoard, constraints);
		*/

		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 0;
		remainingTileNumberLabel.setBackground(Color.WHITE);
		remainingTileNumberLabel.setOpaque(true); 
		getContentPane().add(remainingTileNumberLabel, constraints);

		/*
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.ipady = 50;
		jpanelTile.setBackground(Color.RED);
		jpanelTile.setOpaque(true); 
		getContentPane().add(jpanelTile, constraints); 
		*/

		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridheight = 2;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipady = 50;
		test.setBackground(Color.RED);
		test.setOpaque(true); 
		getContentPane().add(test, constraints);

		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 1;
		test2.setBackground(Color.BLACK);
		test2.setOpaque(true); 
		getContentPane().add(test2, constraints);
	}

	public JPanel getJPanelTile() {
		return jpanelTile;
	}

	public JPanel getJPanelBoard() {
		return jpanelBoard;
	}
}
