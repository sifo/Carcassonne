package org.gla.carcassonne.managers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.entities.Player;

public class PlayerManager {

	private List<Player> players;
	private List<Player> order;
	private int indexInOrder;
	private boolean currentPlayerhasPlacedPiece;
	private CarcassonneModel model;

	private final static int MAX_PLAYER_NUMBER = 6;

	public PlayerManager(CarcassonneModel model) {
		players = new ArrayList<Player>();
		order = players;
		indexInOrder = 0;
		this.model = model;
		currentPlayerhasPlacedPiece = false;
	}

	public void add(Player p) {
		if (players.size() == MAX_PLAYER_NUMBER) {
			return;
		}
		players.add(p);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayersFromNames(List<String> names) {
		String[] colorNames = { "blue", "red", "#DAA520", "green", "black",
				"purple" };
		Color[] colors = { Color.BLUE, Color.RED, new Color(218, 165, 32),
				Color.GREEN, Color.BLACK, new Color(128, 0, 128) };
		List<Player> p = new ArrayList<Player>();
		for (int i = 0; i < names.size(); i++)
			p.add(new Player(names.get(i), colorNames[i], colors[i]));
		this.players = p;
		/*
		 * players.add(new Player("tom")); players.add(new Player("pierre"));
		 */
		model.firePlayers();
	}

	public List<Player> getOrder() {
		return order;
	}

	public Player getCurrentPlayer() {
		return players.get(indexInOrder);
	}

	public void setNextPlayer() {
		if (indexInOrder == players.size() - 1)
			indexInOrder = 0;
		else
			indexInOrder++;
		model.firePlayers();
	}

	public int getIndexInOrder() {
		return indexInOrder;
	}

	public void setIndexInOrder(int indexInOrder) {
		this.indexInOrder = indexInOrder;
	}

	public boolean getCurrentPlayerhasPlacedPiece() {
		return currentPlayerhasPlacedPiece;
	}

	public void setCurrentPlayerhasPlacedPiece(boolean currentPlayerhasPlacedPiece) {
		this.currentPlayerhasPlacedPiece = currentPlayerhasPlacedPiece;
	}
}