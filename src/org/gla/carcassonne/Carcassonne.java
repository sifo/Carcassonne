package org.gla.carcassonne;

public class Carcassonne implements Game {
	private Player[] players;	
	private int playerNumber;
	private Tile[] tiles;
	private Tile[] tilesOnBoard;
	private String[] names = 
	{"Bleu", "Rouge", "Jaune", "Vert", "Orange", "Violet"};

	private final static int DEFAULT_PLAYER_NUMBER = 2;
	private final static int MAX_PLAYER_NUMBER = 6;
	private final static int MAX_TILE_NUMBER = 100;

	public Carcassonne(){
		players = new Player[MAX_PLAYER_NUMBER];
		playerNumber = DEFAULT_PLAYER_NUMBER;
		tiles = new Tile[MAX_TILE_NUMBER];
		tilesOnBoard = new Tile[MAX_TILE_NUMBER];
		names = new String[MAX_PLAYER_NUMBER];
	}

	public void quit() {

	}

	public void save() {

	}

	public void start() {

	}

	public void play() {

	}

	public void addPlayer() {
		if(playerNumber == MAX_PLAYER_NUMBER) {
			return;
		}
		players[playerNumber++] = new Player(names[playerNumber]);
	}

	public Player[] getPlayers() {
		return players;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public Tile[] getTilesOnBoard() {
		return tilesOnBoard;
	}
}

