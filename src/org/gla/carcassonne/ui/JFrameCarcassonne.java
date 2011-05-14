package org.gla.carcassonne.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Graphics2D;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.gla.carcassonne.entities.Player;
import org.gla.carcassonne.entities.Status;
import org.gla.carcassonne.entities.Tile;
import org.gla.carcassonne.events.AddTileEvent;
import org.gla.carcassonne.events.BoardEvent;
import org.gla.carcassonne.events.CantAddTileEvent;
import org.gla.carcassonne.events.CardBackEvent;
import org.gla.carcassonne.events.ListenerOnCurrentTileEvent;
import org.gla.carcassonne.events.NextTileEvent;
import org.gla.carcassonne.events.PlacePieceOnTileEvent;
import org.gla.carcassonne.events.PlayersEvent;
import org.gla.carcassonne.events.RemainingTileEvent;
import org.gla.carcassonne.events.RotateLeftEvent;
import org.gla.carcassonne.events.RotateRightEvent;
import org.gla.carcassonne.ui.events.ButtonListener;
import org.gla.carcassonne.ui.events.ConfirmAction;
import org.gla.carcassonne.ui.events.MouseListener;
import org.gla.carcassonne.ui.events.RotateLeft;
import org.gla.carcassonne.ui.events.RotateRight;

public class JFrameCarcassonne extends JFrame {
	// TODO Un clic droit pour rotate
	// TODO sur le numberplayerdialog, le cancel ne quitte pas le jeu
	// TODO S'assurer que faire 2 fois selectTileRandomly, ne supprime pas la carte
	private static final long serialVersionUID = 2913853546299057427L;
	private JMenuBar menuBar;
	private JScrollPane jspPlateau;
	private JButton[][] tabTile;
	private JPanelImage imagePanel;
	private JPanel plateau;
	List<JLabel> jlabelPlayers;
	private SwingCarcassonneView view;
	private JLabel remainingTileNumber;
	private JButton confirmButton;
	private static final int HEIGHT_MIN = 600;
	private static final int WIDTH_MIN = 485;

	public JFrameCarcassonne(String title, SwingCarcassonneView view) {
		super(title);
		this.view = view;
		confirmButton = new JButton();
		confirmButton.setEnabled(false);
		getContentPane().setLayout(new GridBagLayout());
		menuBar = new JPanelMenu();
		remainingTileNumber = new JLabel("");
		imagePanel = new JPanelImage();
		jlabelPlayers = new ArrayList<JLabel>();
		setJMenuBar(menuBar);
		setPreferredSize(new Dimension(1000, 750));
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
				tabTile[i][j] = new JButton();
				if (tiles[i][j] != null) {
					drawTileOnButton(tiles[i][j]);
				}
				tabTile[i][j].setBackground(Color.LIGHT_GRAY);
				tabTile[i][j].setVisible(true);
				tabTile[i][j].addActionListener(new ButtonListener(i, j, view));
				//tabTile[i][j].addMouseListener(new RotateOnRightClick(view));
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
				"res/drawable/rotate_anticlockwise.png"));
		turnLeftButton.addActionListener(new RotateLeft(view));
		rotateButton.add(turnLeftButton);

		JButton turnRightButton = new JButton();
		turnRightButton.setIcon(new ImageIcon(
				"res/drawable/rotate_clockwise.png"));
		turnRightButton.addActionListener(new RotateRight(view));
		rotateButton.add(turnRightButton);
		
		confirmButton.setIcon(new ImageIcon(
				"res/drawable/accept.png"));
		confirmButton.addActionListener(new ConfirmAction(view));
		Box confirmBox = Box.createHorizontalBox();
		confirmBox.add(confirmButton);

		JPanel leftPanelContent = new JPanel();
		leftPanelContent.setLayout(new BoxLayout(leftPanelContent,
				BoxLayout.Y_AXIS));
		leftPanelContent.add(Box.createVerticalStrut(10));
		leftPanelContent.add(remainingTileNumberBox, Box.CENTER_ALIGNMENT);
		leftPanelContent.add(Box.createVerticalStrut(10));
		leftPanelContent.add(boxImagePanel, Box.CENTER_ALIGNMENT);
		leftPanelContent.add(Box.createVerticalStrut(10));
		leftPanelContent.add(rotateButton, Box.CENTER_ALIGNMENT);
		leftPanelContent.add(Box.createVerticalStrut(10));
		leftPanelContent.add(confirmBox, Box.CENTER_ALIGNMENT);
		
		// leftPanelContent.add(peonBox);
		vLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPanelContent.add(vLabel);

		leftPanelContent.add(Box.createVerticalStrut(10000));

		panel.add(jspPlateau, BorderLayout.CENTER);

		panel.add(leftPanelContent, BorderLayout.EAST);

		return panel;
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
		//tabTile[x][y].addMouseListener(new MouseListener(x, y, view));
	}

	public void cantAddTile(CantAddTileEvent event) {
		
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
					+ players.get(i).getColorName() + "'>" + firstLetterName
					+ "</span>" + nameWithoutFirstLetter
					+ "</p><p>&nbsp;&nbsp;&nbsp;&nbsp;"
					+ players.get(i).getPoints() + " | "
					+ players.get(i).getPieceCount() + "</p><html>";
			if (addLabel) {
				jlabelPlayers.add(new JLabel(text));
			} else {
				jlabelPlayers.get(i).setText(text);
			}
		}
	}

	public void unlockConfirmButton() {
		confirmButton.setEnabled(true);
	}

	public void lockConfirmButton() {
		confirmButton.setEnabled(false);
	}

	public void cardBack(CardBackEvent event) {
		imagePanel.setImage("res/drawable/card-back.png");
	}

	public void listenerOnCurrentTile(
			ListenerOnCurrentTileEvent listenerOnCurrentTileEvent) {
		int x = listenerOnCurrentTileEvent.getX();
		int y = listenerOnCurrentTileEvent.getY();
		tabTile[x][y].addMouseListener(new MouseListener(x, y, view));
	}

	public void placePieceOnTile(PlacePieceOnTileEvent event) {
		drawTileOnButton(event.getTile());
	}
	
	public void drawTileOnButton(Tile tile) {
		JPanelImage imgPanel = new JPanelImage(tile.getType().getPath());
		imgPanel.rotate(tile.getRotationCount() * Math.PI / 2);
		if(tile.getxOnClick() != -1){
			Graphics2D g = imgPanel.bufferedImage.createGraphics();
			g.setColor(tile.getPlayer().getColor());
			g.fillOval(tile.getxOnClick() - 5, tile.getyOnClick() - 5, 16, 17);
			g.setColor(Color.WHITE);
			String letter = "";
			if(tile.getStatus() == Status.THIEF) letter = "T";
			if(tile.getStatus() == Status.KNIGHT) letter = "K";
			if(tile.getStatus() == Status.FARMER) letter = "F";
			if(tile.getStatus() == Status.MONK) {
				letter = "M";
				//La lettre M est plus épaisse que les autres, on le décale un peu
				g.drawString(letter, tile.getxOnClick() - 1, tile.getyOnClick() + 9);
			}
			else {
				g.drawString(letter, tile.getxOnClick(), tile.getyOnClick() + 9);
			}
		}
		tabTile[tile.getxOnBoard()][tile.getyOnBoard()].setIcon(
				new ImageIcon(imgPanel.bufferedImage));
	}

}