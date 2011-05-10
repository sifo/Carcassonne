package org.gla.carcassonne.entities;

import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.managers.PlayerManager;
import org.gla.carcassonne.managers.TileManager;

public class Carcassonne implements Game {
	private TileManager tileManager;
	private PlayerManager playerManager;
	private CarcassonneModel model;

	public Carcassonne(CarcassonneModel model){
		tileManager = new TileManager(model);
		playerManager = new PlayerManager();
		this.model = model;
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