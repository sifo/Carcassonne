package org.gla.carcassonne;

public class Map {
	public static final int LENGTH = 10;
	public static final int HEIGHT = 10;
	
	private Tile[][] grid;
	
	public Map() {
		grid = new Tile[LENGTH][HEIGHT];
	}
	
	public Tile getTileAt(int length, int height) {
		return grid[length][height];
	}
	
	/*
	 * Placement d'une tuile à un emplacement donné sur le plateau de jeu
	 * TODO : tenir compte des rotations
	 */
	public boolean setTileAt(int x, int y) {
		if (x < 0 || y < 0)
			return false;
		
		if (x > LENGTH || y > HEIGHT)
			return false;
		
		if (x > 1 && y > 1) {
		}
		
		return true;
	}
}
