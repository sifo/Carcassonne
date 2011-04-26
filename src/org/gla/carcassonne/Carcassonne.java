package org.gla.carcassonne;

public class Carcassonne implements Game {
	private TileManager tileManager;
	private PlayerManager playerManager;

	public Carcassonne(){
		tileManager = new TileManager();
		playerManager = new PlayerManager();
	}

	public void quit() {

	}

	public void save() {

	}

	public void start() {

	}

	public void play() {

	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public TileManager getTileManager() {
		return tileManager;
	}
}
