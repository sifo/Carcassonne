package org.gla.carcassonne;

public class Grid {
	public static final int LENGTH = 10;
	public static final int HEIGHT = 10;
	
	private Tile[][] grid;
	
	public Grid() {
		grid = new Tile[LENGTH][HEIGHT];
	}
	
	public Tile getTileAt(int length, int height) {
		return grid[length][height];
	}
	
	/*
	 * Placement d'une tuile à un emplacement donné sur le plateau de jeu
	 * TODO : tenir compte des rotations
	 */
	public boolean setTileAt(Tile tile, int x, int y) {
		if (x < 0 || y < 0)
			return false;
		
		if (x > LENGTH-1 || y > HEIGHT-1)
			return false;
		
		if (y > 0)
			if (grid[x][y-1] != null) 
				if (grid[x][y-1].getSideValue(Tile.SOUTH) != tile.getSideValue(Tile.NORTH))
					return false;
		
		if (x < LENGTH-2)
			if (grid[x+1][y] != null) 
				if (grid[x+1][y].getSideValue(Tile.WEST) != tile.getSideValue(Tile.EAST))
					return false;
			
		if (x < HEIGHT-2)
			if (grid[x][y+1] != null) 
				if (grid[x][y+1].getSideValue(Tile.NORTH) != tile.getSideValue(Tile.SOUTH))
					return false;
			
		if (x > 0)
			if (grid[x-1][y] != null) 
				if (grid[x-1][y].getSideValue(Tile.EAST) != tile.getSideValue(Tile.WEST))
					return false;
		
		// Placement de la tuile possible
		grid[x][y] = tile;
		return true;
	}
}
