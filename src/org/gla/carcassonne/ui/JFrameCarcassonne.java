package org.gla.carcassonne.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.gla.carcassonne.entities.Tile;
import org.gla.carcassonne.events.AddTileEvent;
import org.gla.carcassonne.events.BoardEvent;
import org.gla.carcassonne.events.CantAddTileEvent;
import org.gla.carcassonne.events.NextTileEvent;
import org.gla.carcassonne.events.RemainingTileEvent;
import org.gla.carcassonne.events.RotateLeftEvent;
import org.gla.carcassonne.events.RotateRightEvent;
import org.gla.carcassonne.ui.events.ButtonListener;
import org.gla.carcassonne.ui.events.RotateLeft;
import org.gla.carcassonne.ui.events.RotateRight;

public class JFrameCarcassonne extends JFrame {

	private static final long serialVersionUID = 2913853546299057427L;
	private JMenuBar menuBar;
	private JScrollPane jspPlateau;
	private JButton[][] tabTile;
	private ImagePanel imagePanel;
	private JPanel plateau;
	private JLabel labelPlayer;
	private SwingCarcassonneView view;
	private JLabel remainingTileNumber;

	public JFrameCarcassonne(String title, SwingCarcassonneView view) {
		super(title);
		this.view = view;
		getContentPane().setLayout(new GridBagLayout());
		menuBar = new JPanelMenu();
		remainingTileNumber = new JLabel("");
		setJMenuBar(menuBar);
		setPreferredSize(new Dimension(1350, 750));
		pack();
	}

	public void board(BoardEvent event) {
		Tile[][] tiles = event.getTiles();
		int width = tiles.length;
		int height = tiles[0].length;
		tabTile = new JButton[width][height];

		for (int i = 0; i < tabTile.length; i++) {
			for (int j = 0; j < tabTile[0].length; j++) {
				if (tiles[i][j] != null) {
					ImagePanel buffImgPanel = new ImagePanel(tiles[i][j]
							.getType().getPath());
					buffImgPanel.rotate(tiles[i][j].getRotationCount()
							* (Math.PI / 2));
					tabTile[i][j] = new JButton(new ImageIcon(
							buffImgPanel.bufferedImage));
				} else
					tabTile[i][j] = new JButton();
				tabTile[i][j].setBackground(Color.LIGHT_GRAY);
				tabTile[i][j].setVisible(true);

				tabTile[i][j].addActionListener(new ButtonListener(i, j, view));
				// tabTile[i][j].addMouseListener(new MyMouseListener(i, j));
				tabTile[i][j].setMargin(new Insets(-6, -6, -6, -6));
			}
		}
		setContentPane(builContendPane(height, width));
		pack();
	}

	private JPanel builContendPane(int width, int height) {
		JPanel panel = new JPanel(new BorderLayout());

		JPanel plateauTemp = new JPanel(new GridBagLayout());
		plateauTemp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		plateau = new JPanel(new GridLayout(width, height));
		for (int j = tabTile[0].length - 1; j >= 0; j--) {
			for (int i = 0; i < tabTile.length; i++) {
				plateau.add(tabTile[i][j]);
			}
		}

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		plateau.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		plateauTemp.add(plateau, constraints);

		jspPlateau = new JScrollPane(plateauTemp);
		jspPlateau.getVerticalScrollBar().setUnitIncrement(16);

		Box remainingTileNumberBox = Box.createHorizontalBox();
		remainingTileNumberBox.add(remainingTileNumber);

		Box hLabel = Box.createHorizontalBox();

		labelPlayer = new JLabel("<html><h3 style = 'margin-right:25px;margin-left:30px;color:"
				+ "black" + "'>Joueur " + "tom" + "</h3>"
				+ "<h4 style = 'margin-left:10px'>(Pions restants : " + "7"
				+ ")</h4></html>");

		hLabel.add(labelPlayer);

		Box boxImagePanel = Box.createHorizontalBox();
		imagePanel = new ImagePanel();
		boxImagePanel.add(Box.createHorizontalStrut(35));
		boxImagePanel.add(imagePanel);

		Box peonBox = Box.createHorizontalBox();
		JButton peonButton = new JButton();
		peonButton.setText("Deposer un Pion");
		peonButton.setActionCommand("deposite a peon");
		// peonButton.addActionListener(new MyListener());
		peonBox.add(peonButton, Box.CENTER_ALIGNMENT);

		Box rotateButton = Box.createHorizontalBox();

		JButton turnLeftButton = new JButton();
		turnLeftButton.setIcon(new ImageIcon("res/drawable/1305046746_arrow_rotate_anticlockwise.png"));
		turnLeftButton.addActionListener(new RotateLeft(view));
		rotateButton.add(turnLeftButton);

		JButton turnRightButton = new JButton();
		turnRightButton.setIcon(new ImageIcon("res/drawable/1305046774_arrow_rotate_clockwise.png"));
		turnRightButton.addActionListener(new RotateRight(view));
		rotateButton.add(turnRightButton);

		Box leftPanelContent = Box.createVerticalBox();
		leftPanelContent.add(Box.createVerticalStrut(10));
		leftPanelContent.add(remainingTileNumberBox);
		leftPanelContent.add(Box.createVerticalStrut(10));
		leftPanelContent.add(boxImagePanel);
		leftPanelContent.add(Box.createVerticalStrut(10));
		leftPanelContent.add(rotateButton);
		leftPanelContent.add(Box.createVerticalStrut(20));
		//leftPanelContent.add(peonBox);
		leftPanelContent.add(hLabel);
		leftPanelContent.add(Box.createVerticalStrut(443));

		// leftPanelContent.add(hBox);
		// leftPanelContent.add(Box.createVerticalStrut(50));

		panel.add(jspPlateau, BorderLayout.CENTER);
		panel.add(leftPanelContent, BorderLayout.EAST);

		return panel;
	}

	public static Position getPositionFromTuile(int x, int y, int width,
			int height) {

		double coeffDiagonalTopLeftToBottomRight = height / width;

		if ((y <= (height / 3)) && (x <= (width / 3))
				&& (x * coeffDiagonalTopLeftToBottomRight <= y))
			return Position.verticalTopLeft;
		if ((y <= (height / 3)) && (x <= (width / 3))
				&& (x * coeffDiagonalTopLeftToBottomRight > y))
			return Position.horizontalTopLeft;

		if ((y <= height / 3) && (x <= 2 * width / 3))
			return Position.topMiddle;

		if ((y <= (height / 3)) && (x <= width)
				&& ((-x * coeffDiagonalTopLeftToBottomRight + height) <= y))
			return Position.verticalTopRight;

		if ((y <= (height / 3)) && (x <= width)
				&& ((-x * coeffDiagonalTopLeftToBottomRight + height) > y))
			return Position.horizontalTopRight;

		if (y <= 2 * height / 3 && x <= width / 3)
			return Position.centerLeft;

		if (y <= 2 * height / 3 && x <= 2 * width / 3)
			return Position.centerMiddle;

		if (y <= 2 * height / 3 && x <= width)
			return Position.centerRight;

		if ((y <= height) && (x <= (width / 3))
				&& (-x * coeffDiagonalTopLeftToBottomRight + height <= y))
			return Position.horizontalBottomLeft;

		if ((y <= height) && (x <= (width / 3))
				&& (-x * coeffDiagonalTopLeftToBottomRight + height > y))
			return Position.verticalBottomLeft;

		if ((y <= height) && (x <= 2 * width / 3))
			return Position.bottomMiddle;

		if ((y <= height) && (x <= width)
				&& (x * coeffDiagonalTopLeftToBottomRight <= y))
			return Position.horizontalBottomRight;

		return Position.verticalBottomRight;
	}

	public void showNbPlayer() {
		NumberPlayerDialog dialog = new NumberPlayerDialog();
		if (!dialog.clickedOnOk)
			System.exit(0);
		// interfaceGui.initPlayer(dialog.getNbPlayer(), dialog.getPlayerName(),
		// dialog.getPlayerStatus());
	}

	public void addTile(AddTileEvent event) {
		Tile tile = event.getTile();
		int x = tile.getxOnBoard();
		int y = tile.getyOnBoard();
		ImagePanel buffImgPanel = new ImagePanel(tile.getType().getPath());
		buffImgPanel.rotate(tile.getRotationCount() * (Math.PI / 2));
		tabTile[x][y].setIcon(new ImageIcon(buffImgPanel.bufferedImage));
	}

	public void cantAddTile(CantAddTileEvent event) {
		JOptionPane.showMessageDialog(null, "Mauvais placement de tuile.");
	}

	public void rotateLeft(RotateLeftEvent event) {
		imagePanel.setImage(imagePanel.fileString);
		imagePanel.rotate(event.getRotateCount() * (Math.PI / 2));
	}

	public void rotateRight(RotateRightEvent event) {
		imagePanel.setImage(imagePanel.fileString);
		imagePanel.rotate(event.getRotateCount() * (Math.PI / 2));
	}

	public void pick(NextTileEvent event) {
		Tile tile = event.getNewTile();
		imagePanel.setImage(tile.getType().getPath());
		imagePanel.rotate(tile.getRotationCount() * (Math.PI / 2));
	}

	public void remainingTileNumber(RemainingTileEvent event) {
		remainingTileNumber.setText(event.getNumber() + " Restants");
	}
}