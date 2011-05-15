package org.gla.carcassonne.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.gla.carcassonne.ui.events.CloseWindow;
import org.gla.carcassonne.ui.events.QuitGame;

public class MultiplayerLobbyDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final int MAX_PLAYER_NUMBER = 5;
	private static final String CONNECT 	= "Connexion";
	private static final String QUIT 		= "Quitter";
	private static final String READY 		= "Prêt";

	JTextField serverAddressField;
	JTextField serverPortField;
	JLabel[] playersList;
	SwingCarcassonneView view;

	boolean clickedOnOk = false;

	public MultiplayerLobbyDialog(SwingCarcassonneView view) {
		this.view = view;
		addWindowListener(new CloseWindow(view));
		this.setResizable(false);
		this.setTitle("Lobby multijoueur");
		this.setModal(true);
		this.setContentPane(buildComponent());
		this.pack();
		this.setDialogLocation(view.getJFrame());
		this.setVisible(true);
	}

	private void setDialogLocation(Frame f) {
		Rectangle r = f.getBounds();
		int x = r.x + (r.width - getSize().width)/2;
		int y = r.y + (r.height - getSize().height)/2;
		setLocation(x, y);
	}

	private JPanel buildComponent() {
		serverAddressField = new JTextField(20);
		serverPortField = new JTextField(6);
		playersList = new JLabel[MAX_PLAYER_NUMBER];
		
		for (int i = 0; i < MAX_PLAYER_NUMBER; i++)
			playersList[i] = new JLabel("--");
		
		JButton connectButton = new JButton(CONNECT);
		//connectButton.addActionListener(new SendPlayerListListener(view, this));
		
		JButton playButton = new JButton(READY);
		//playButton.addActionListener(new SendPlayerListListener(view, this));

		JButton quitButton = new JButton(QUIT);
		quitButton.addActionListener(new QuitGame(view));

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("<html><h2>Bienvenue dans le Lobby multijoueur</h2></html>",
				JLabel.CENTER), BorderLayout.NORTH);

		Box vbox = Box.createVerticalBox();
		vbox.add(Box.createVerticalStrut(10));

		// Champs pour entrer le nom du serveur et son port
		Box hbox = Box.createHorizontalBox();
		hbox.add(Box.createHorizontalStrut(10));
		hbox.add(new JLabel("Addresse du serveur : "));
		hbox.add(Box.createHorizontalStrut(10));
		hbox.add(serverAddressField);
		hbox.add(Box.createHorizontalStrut(50));
		vbox.add(hbox);
		
		hbox = Box.createHorizontalBox();
		hbox.add(Box.createHorizontalStrut(10));
		hbox.add(new JLabel("Port : "));
		hbox.add(Box.createHorizontalStrut(105));
		hbox.add(serverPortField);
		hbox.add(Box.createHorizontalStrut(250));
		vbox.add(hbox);
		vbox.add(Box.createVerticalStrut(15));
		
		// Bouton de connexion
		hbox = Box.createHorizontalBox();
		hbox.add(connectButton);
		vbox.add(hbox);
		vbox.add(Box.createVerticalStrut(25));
		
		hbox = Box.createHorizontalBox();
		hbox.add(new JLabel("Liste des joueurs connectés :"));
		vbox.add(hbox);
		vbox.add(Box.createVerticalStrut(25));
		
		for (int i = 0; i < MAX_PLAYER_NUMBER; i++) {
			hbox = Box.createHorizontalBox();
			hbox.add(Box.createHorizontalStrut(10));
			hbox.add(playersList[i]);
			hbox.add(Box.createHorizontalStrut(10));
			vbox.add(hbox);
			vbox.add(Box.createVerticalStrut(10));
		}

		vbox.add(Box.createVerticalStrut(25));
		hbox = Box.createHorizontalBox();
		hbox.add(playButton);
		hbox.add(Box.createHorizontalStrut(10));
		hbox.add(quitButton);
		vbox.add(hbox);
		vbox.add(Box.createVerticalStrut(10));
		
		JPanel panelInside = new JPanel();
		panelInside.add(vbox);
		panel.add(panelInside, BorderLayout.CENTER);
		return panel;
	}

	public void actionPerformed(ActionEvent e) {
	}
}