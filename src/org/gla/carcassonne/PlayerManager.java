package org.gla.carcassonne;

import org.gla.carcassonne.Player;

public class PlayerManager {

	private Player[] players;	
	private int playerNumber;
	private String[] names = 
		{"Bleu", "Rouge", "Jaune", "Vert", "Orange", "Violet"};

	private final static int DEFAULT_PLAYER_NUMBER = 2;
	private final static int MAX_PLAYER_NUMBER = 6;	

	public PlayerManager() {
		players = new Player[MAX_PLAYER_NUMBER];
		addPlayer();
		addPlayer();
		names = new String[MAX_PLAYER_NUMBER];
	}
	
	public void addPlayer() {
		if(playerNumber == MAX_PLAYER_NUMBER) {
			return;
		}
		players[playerNumber++] = new Player(names[playerNumber]);
	}

	public void removePlayer() {
		if(playerNumber == 0) {
			return;
		}
		playerNumber--;
	}

	public Player[] getPlayers() {
		return players;
	}
	
	public int getPlayerNumber() {
		return playerNumber;
	}
}
