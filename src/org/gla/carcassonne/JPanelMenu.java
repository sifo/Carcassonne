package org.gla.carcassonne;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import org.gla.carcassonne.JPanelTile;

public class JPanelMenu extends JMenuBar {

	private JMenu help;
	private JMenu game;
	private JMenu parameters;
	private JMenuItem newGame;
	private JMenuItem quit;
	private JMenuItem about;
	private JMenuItem helpContents;
	private JMenuItem save;
	private JMenuItem saveAs;
	private JMenuItem open;
	private JMenuItem online;
	private JMenuItem preferences;
	private JMenuItem fullScreen;
	private JMenuItem quitFullScreen;

	private final static String HELP = "Aide";
	private final static String GAME = "Jeu";
	private final static String PARAMETERS = "Paramètres";
	private final static String NEW_GAME = "Nouveau";
	private final static String QUIT = "Quitter";
	private final static String ABOUT = "À propos";
	private final static String HELP_CONTENTS = "Sommaire";
	private final static String SAVE = "Enregistrer";
	private final static String SAVE_AS = "Enregistrer sous...";
	private final static String OPEN = "Ouvrir...";
	private final static String ONLINE = "Partie en réseau...";
	private final static String PREFERENCES = "Préférences";
	private final static String FULL_SCREEN = "Plein écran";
	private final static String QUIT_FULL_SCREEN = "Quitter le plein écran";

	public JPanelMenu() {
		super();
		help = new JMenu(HELP);
		game = new JMenu(GAME);
		parameters = new JMenu(PARAMETERS);
		newGame = new JMenuItem(NEW_GAME);
		quit = new JMenuItem(QUIT);
		about = new JMenuItem(ABOUT);
		helpContents = new JMenuItem(HELP_CONTENTS);
		save = new JMenuItem(SAVE);
		saveAs = new JMenuItem(SAVE_AS);
		open = new JMenuItem(OPEN);
		online = new JMenuItem(ONLINE);
		online = new JMenuItem(ONLINE);
		preferences = new JMenuItem(PREFERENCES);
		fullScreen = new JMenuItem(FULL_SCREEN);
		quitFullScreen = new JMenuItem(QUIT_FULL_SCREEN);
		quitFullScreen.setEnabled(false);
		addComponents();
		setAccelerators();
		setMnemonics();
	}

	private void addComponents() {
		add(game);
		add(parameters);
		add(help);
		game.add(newGame);
		game.add(open);
		game.add(save);
		game.add(saveAs);
		game.addSeparator();
		game.add(online);
		game.addSeparator();
		game.add(quit);
		parameters.add(fullScreen);
		parameters.add(quitFullScreen);
		parameters.add(preferences);
		help.add(helpContents);
		help.add(about);
	}

	private void setMnemonics() {
		game.setMnemonic(KeyEvent.VK_J);
		parameters.setMnemonic(KeyEvent.VK_P);
		help.setMnemonic(KeyEvent.VK_E);
		newGame.setMnemonic(KeyEvent.VK_N);
		open.setMnemonic(KeyEvent.VK_O);
		save.setMnemonic(KeyEvent.VK_E);
		saveAs.setMnemonic(KeyEvent.VK_S);
		online.setMnemonic(KeyEvent.VK_P);
		quit.setMnemonic(KeyEvent.VK_Q);
		fullScreen.setMnemonic(KeyEvent.VK_N);
		quitFullScreen.setMnemonic(KeyEvent.VK_Q);
		preferences.setMnemonic(KeyEvent.VK_P);
		helpContents.setMnemonic(KeyEvent.VK_S);
		about.setMnemonic(KeyEvent.VK_P);
	}

	private void setAccelerators() {
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, 
			ActionEvent.CTRL_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, 
			ActionEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, 
			ActionEvent.CTRL_MASK));
		online.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, 
			ActionEvent.CTRL_MASK));
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 
			ActionEvent.CTRL_MASK));
		fullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
		quitFullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
		helpContents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
	}

}
