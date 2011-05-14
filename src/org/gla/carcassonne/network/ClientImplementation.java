package org.gla.carcassonne.network;

import java.util.Set;

public class ClientImplementation implements ClientFactory {

	public ClientImplementation() {
		
	}
	
	@Override
	public Object getPlayers(Set<Integer> p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int setX(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setY(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String setTile(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setPiece(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkMove(int player, String tile, int x, int y, String piece) {
		// TODO Auto-generated method stub
		return false;
	}

}
