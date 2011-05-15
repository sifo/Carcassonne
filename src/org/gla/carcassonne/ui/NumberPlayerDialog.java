package org.gla.carcassonne.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.gla.carcassonne.ui.events.CloseWindow;
import org.gla.carcassonne.ui.events.QuitGame;
import org.gla.carcassonne.ui.events.SendPlayerListListener;

public class NumberPlayerDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final int MAX_LENGTH_NAME = 12;
	JComboBox numberPlayer;
	int nbPlayer = 1;
	JTextField[] playerNameField;
	JComboBox[] aiComboxBox;
	List<String> names;
	SwingCarcassonneView view;

	boolean clickedOnOk = false;

	public NumberPlayerDialog(SwingCarcassonneView view) {
		numberPlayer = new JComboBox(new String[] { "1", "2", "3", "4", "5",
				"6" });
		numberPlayer.setSelectedIndex(1);
		this.view = view;
		numberPlayer.addActionListener(this);
		addWindowListener(new CloseWindow(view));
		this.setResizable(false);
		this.setTitle("Nombre de joueur");
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
		if (numberPlayer.getSelectedIndex() != -1)
			numberPlayer.setSelectedIndex(numberPlayer.getSelectedIndex());

		playerNameField = new JTextField[nbPlayer];
		aiComboxBox = new JComboBox[nbPlayer];
		for (int i = 0; i < playerNameField.length; i++) {
			playerNameField[i] = new JTextField(15);
			aiComboxBox[i] = new JComboBox(new String[] { "Humain", "AI" });
		}

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("<html><h2>Sélection des joueurs : </h2></html>",
				JLabel.CENTER), BorderLayout.NORTH);

		Box vbox = Box.createVerticalBox();
		vbox.add(Box.createVerticalStrut(10));

		Box hbox = Box.createHorizontalBox();
		hbox.add(Box.createHorizontalStrut(10));
		hbox.add(new JLabel("Nombre de joueur : "));
		hbox.add(Box.createHorizontalStrut(10));
		hbox.add(numberPlayer);
		hbox.add(Box.createHorizontalStrut(10));

		vbox.add(hbox);
		vbox.add(Box.createVerticalStrut(25));
		for (int i = 0; i < nbPlayer; i++) {
			hbox = Box.createHorizontalBox();
			hbox.add(Box.createHorizontalStrut(10));
			hbox.add(new JLabel("Nom du joueur " + (i + 1) + " : "));
			hbox.add(Box.createHorizontalStrut(10));
			hbox.add(playerNameField[i]);
			hbox.add(Box.createHorizontalStrut(10));
			hbox.add(aiComboxBox[i]);
			hbox.add(Box.createHorizontalStrut(10));
			vbox.add(hbox);
			vbox.add(Box.createVerticalStrut(10));
		}

		JButton okButton = new JButton("Jouer");
		okButton.addActionListener(new SendPlayerListListener(view, this));

		JButton cancelButton = new JButton("Quitter");
		cancelButton.addActionListener(new QuitGame(view));

		hbox = Box.createHorizontalBox();
		hbox.add(okButton);
		hbox.add(Box.createHorizontalStrut(10));
		hbox.add(cancelButton);
		vbox.add(hbox);
		vbox.add(Box.createVerticalStrut(10));
		JPanel panelInside = new JPanel();
		panelInside.add(vbox);
		panel.add(panelInside, BorderLayout.CENTER);
		return panel;
	}

	public int getNbPlayer() {
		return nbPlayer;
	}

	public List<String> getPlayersNames() {
		names = new ArrayList<String>();
		String name = "";
		for (int i = 0; i < playerNameField.length; i++) {
			name = playerNameField[i].getText().trim();
			if (name.isEmpty())
				names.add("Joueur" + (i + 1));
			else if (name.length() > MAX_LENGTH_NAME)
				names.add(name.substring(0, MAX_LENGTH_NAME));
			else names.add(name);
		}
		return names;
	}

	public boolean[] getPlayerStatus() {
		boolean[] tab = new boolean[nbPlayer];
		for (int i = 0; i < nbPlayer; i++) {
			if (aiComboxBox[i].getSelectedIndex() == 0)
				tab[i] = false;
			else
				tab[i] = true;
		}
		return tab;
	}

	public List<String> getNames() {
		return names;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox) {
			JComboBox combo = (JComboBox) e.getSource();
			nbPlayer = combo.getSelectedIndex() + 1;
			this.setContentPane(buildComponent());
			this.pack();
			return;
		}
		if (e.getActionCommand().equals("ok")) {
			clickedOnOk = true;
		}
		this.dispose();
	}
}
