package org.gla.carcassonne.managers;

import java.util.ArrayList;
import java.util.List;

import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.entities.Player;

public class PlayerManager {

	private List<Player> players;
	private List<Player> order;
	private int indexInOrder;

	private CarcassonneModel model;
	private String[] colors = { "Bleu", "Rouge", "Jaune", "Vert", "Orange",
			"Violet" };

	private final static int MAX_PLAYER_NUMBER = 6;

	public PlayerManager(CarcassonneModel model) {
		players = new ArrayList<Player>();
		order = players;
		indexInOrder = 0;
		setColors(new String[MAX_PLAYER_NUMBER]);
		this.model = model;
	}

	public void add(Player p) {
		if (players.size() == MAX_PLAYER_NUMBER) {
			return;
		}
		players.add(p);
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}

	public String[] getColors() {
		return colors;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayersFromNames(List<String> names) {
		List<Player> p = new ArrayList<Player>();
		for (String name : names)
			p.add(new Player(name));
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
}