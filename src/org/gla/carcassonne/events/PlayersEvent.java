package org.gla.carcassonne.events;

import java.util.EventObject;
import java.util.List;
import org.gla.carcassonne.entities.Player;

public class PlayersEvent extends EventObject {
	private List<Player> players;
	private Player currentPlayer;
	private static final long serialVersionUID = -3588074956097041159L;

	public PlayersEvent(Object source, List<Player> players,
			Player currentPlayer) {
		super(source);
		this.players = players;
		this.currentPlayer = currentPlayer;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
}
