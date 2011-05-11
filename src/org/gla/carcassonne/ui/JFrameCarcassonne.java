package org.gla.carcassonne.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.gla.carcassonne.entities.Player;
import org.gla.carcassonne.entities.Tile;
import org.gla.carcassonne.events.AddTileEvent;
import org.gla.carcassonne.events.BoardEvent;
import org.gla.carcassonne.events.CantAddTileEvent;
import org.gla.carcassonne.events.NextTileEvent;
import org.gla.carcassonne.events.PlayersEvent;
import org.gla.carcassonne.events.RemainingTileEvent;
import org.gla.carcassonne.events.RotateLeftEvent;
import org.gla.carcassonne.events.RotateRightEvent;
import org.gla.carcassonne.ui.events.ButtonListener;
import org.gla.carcassonne.ui.events.RotateLeft;
import org.gla.carcassonne.ui.events.RotateRight;

public class JFrameCarcassonne extends JFrame {
	// TODO Donner une autre tuile s'il est impossible de poser tuile
	// TODO limiter la taille du text des noms des joueurs
	// TODO Un clic droit pour rotate
	// TODO sur le numberplayerdialog, le cancel ne quitte pas le jeu
	private static final long serialVersionUID = 2913853546299057427L;
	private JMenuBar menuBar;
	private JScrollPane jspPlateau;
	private JButton[][] tabTile;
	private JPanelImage imagePanel;
	private JPanel plateau;
	List<JLabel> jlabelPlayers;
	private SwingCarcassonneView view;
	private JLabel remainingTileNumber;

	private static final int HEIGHT_MIN = 600;
	private static final int WIDTH_MIN = 485;

	public JFrameCarcassonne(String title, SwingCarcassonneView view) {
		super(title);
		this.view = view;
		getContentPane().setLayout(new GridBagLayout());
		menuBar = new JPanelMenu();
		remainingTileNumber = new JLabel("");
		jlabelPlayers = new ArrayList<JLabel>();
		setJMenuBar(menuBar);
		setPreferredSize(new Dimension(1350, 750));
		setMinimumSize(new Dimension(HEIGHT_MIN, WIDTH_MIN));
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
					JPanelImage buffImgPanel = new JPanelImage(tiles[i][j]
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

		Box vLabel = Box.createVerticalBox();

		for (JLabel labelPlayer : jlabelPlayers) {
			vLabel.add(Box.createVerticalStrut(10));
			vLabel.add(labelPlayer);
		}

		Box boxImagePanel = Box.createHorizontalBox();
		boxImagePanel.setMinimumSize(new Dimension(80, 80));
		imagePanel = new JPanelImage();
		boxImagePanel.add(Box.createHorizontalStrut(30));
		boxImagePanel.add(imagePanel);

		Box peonBox = Box.createHorizontalBox();
		JButton peonButton = new JButton();
		peonButton.setText("Deposer un Pion");
		peonButton.setActionCommand("deposite a peon");
		// peonButton.addActionListener(new MyListener());
		peonBox.add(peonButton, Box.CENTER_ALIGNMENT);

		Box rotateButton = Box.createHorizontalBox();

		JButton turnLeftButton = new JButton();
		turnLeftButton.setIcon(new ImageIcon(
				"res/drawable/1305046746_arrow_rotate_anticlockwise.png"));
		turnLeftButton.addActionListener(new RotateLeft(view));
		rotateButton.add(turnLeftButton);

		JButton turnRightButton = new JButton();
		turnRightButton.setIcon(new ImageIcon(
				"res/drawable/1305046774_arrow_rotate_clockwise.png"));
		turnRightButton.addActionListener(new RotateRight(view));
		rotateButton.add(turnRightButton);

		JPanel leftPanelContent = new JPanel();
		leftPanelContent.setLayout(new BoxLayout(leftPanelContent,
				BoxLayout.Y_AXIS));
		leftPanelContent.add(Box.createVerticalStrut(10));
		leftPanelContent.add(remainingTileNumberBox, Box.CENTER_ALIGNMENT);
		leftPanelContent.add(Box.createVerticalStrut(10));
		leftPanelContent.add(boxImagePanel, Box.CENTER_ALIGNMENT);
		leftPanelContent.add(Box.createVerticalStrut(10));
		leftPanelContent.add(rotateButton, Box.CENTER_ALIGNMENT);
		// leftPanelContent.add(peonBox);
		vLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPanelContent.add(vLabel);
		leftPanelContent.add(Box.createVerticalStrut(463));

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
		new NumberPlayerDialog(view);
	}

	public void addTile(AddTileEvent event) {
		Tile tile = event.getTile();
		int x = tile.getxOnBoard();
		int y = tile.getyOnBoard();
		JPanelImage buffImgPanel = new JPanelImage(tile.getType().getPath());
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

	public void players(PlayersEvent event) {
		String turn;
		String text;
		boolean addLabel = false;
		List<Player> players = event.getPlayers();

		if (jlabelPlayers.isEmpty())
			addLabel = true;
		for (int i = 0; i < players.size(); i++) {
			if (event.getCurrentPlayer() == players.get(i))
				turn = "$&nbsp;&nbsp;";
			else
				turn = "&nbsp;&nbsp;&nbsp;&nbsp;";
			String firstLetterName = "" + players.get(i).getName().charAt(0);
			String nameWithoutFirstLetter = players.get(i).getName()
					.substring(1);
			text = "<html><p>" + turn
					+ "<span style='font-size:1.375em;font-weight:bold;color:"
					+ players.get(i).getColor() + "'>" + firstLetterName
					+ "</span>" + nameWithoutFirstLetter
					+ "</p><p>&nbsp;&nbsp;&nbsp;&nbsp;"
					+ players.get(i).getPoints() + " | "
					+ players.get(i).getPieceCount() + "</p><html>";
			System.out.println("couleur : " + players.get(i).getColor());
			if (addLabel) {
				jlabelPlayers.add(new JLabel(text));
			} else {
				jlabelPlayers.get(i).setText(text);
			}
		}
	}
}