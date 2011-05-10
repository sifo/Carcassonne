package org.gla.carcassonne.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NumberPlayerDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	JComboBox numberPlayer;
	int nbPlayer = 1;
	JTextField[] playerNameField;
	JComboBox [] aiComboxBox;

	boolean clickedOnOk = false;
	
	
	public NumberPlayerDialog (){
		numberPlayer = new JComboBox(new String[]{"1","2","3","4","5","6"});
		numberPlayer.addActionListener(this);
		
		this.setTitle("Nombre de joueur");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setModal(true);
		
		this.setLocationRelativeTo(null);
		this.setContentPane(buildComponent());
		this.setPreferredSize(new Dimension(450, 400));
		this.pack();
		this.setVisible(true);
		
	}

	private JPanel buildComponent() {
		if(numberPlayer.getSelectedIndex() != -1)
			numberPlayer.setSelectedIndex(numberPlayer.getSelectedIndex());

		playerNameField = new JTextField[nbPlayer];
		aiComboxBox = new JComboBox[nbPlayer];
		for(int i = 0 ; i < playerNameField.length; i++){
			playerNameField[i] = new JTextField(15);
			aiComboxBox[i] = new JComboBox(new String[]{"Humain","AI"});
		}
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("<html><h2>Selection des joueurs: </h2></html>", JLabel.CENTER),BorderLayout.NORTH);

		Box vbox = Box.createVerticalBox();
		vbox.add(Box.createVerticalStrut(10));
		
		Box hbox = Box.createHorizontalBox();
		hbox.add(new JLabel("Nombre de joueur: "));
		hbox.add(Box.createHorizontalStrut(10));
		hbox.add(numberPlayer);
		
		vbox.add(hbox);
		vbox.add(Box.createVerticalStrut(25));
		for(int i = 0 ; i < nbPlayer; i ++){
			hbox = Box.createHorizontalBox();
			hbox.add(new JLabel("Nom du joueur "+(i+1)+": "));
			hbox.add(Box.createHorizontalStrut(10));
			hbox.add(playerNameField[i]);
			hbox.add(Box.createHorizontalStrut(10));
			hbox.add(aiComboxBox[i]);
			vbox.add(hbox);
			vbox.add(Box.createVerticalStrut(10));
		}
		
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("ok");
		okButton.addActionListener(this);
		
		JButton cancelButton = new JButton("Annuler");
		cancelButton.setActionCommand("cancel");
		cancelButton.addActionListener(this);
		
		hbox = Box.createHorizontalBox();		
		hbox.add(okButton);
		hbox.add(Box.createHorizontalStrut(10));
		hbox.add(cancelButton);
		vbox.add(hbox);
		
		JPanel panelInside = new JPanel();
		panelInside.add(vbox);
		panel.add(panelInside, BorderLayout.CENTER);
		return panel;
	}

	public int getNbPlayer(){
		return nbPlayer;
	}
	
	public String [] getPlayerName(){
		String [] name = new String[playerNameField.length];
		for(int i = 0 ; i < name.length; i++){
			if(playerNameField[i].getText().isEmpty())
				name[i] = ""+(i+1);
			else
				name[i] = playerNameField[i].getText();
		}
		return name;
	}

	public boolean [] getPlayerStatus(){
		boolean [] tab = new boolean[nbPlayer];
		for(int i = 0; i < nbPlayer; i++){
			if(aiComboxBox[i].getSelectedIndex() == 0)
				tab[i] = false;
			else
				tab[i] = true;
		}
		return tab;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JComboBox){
			JComboBox combo = (JComboBox) e.getSource();
			nbPlayer = combo.getSelectedIndex() + 1;
			this.setContentPane(buildComponent());
			this.pack();
			return;
		}
		if(e.getActionCommand().equals("ok")){
			clickedOnOk = true;
		}
		this.dispose();
	}

}
