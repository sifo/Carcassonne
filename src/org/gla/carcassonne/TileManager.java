package org.gla.carcassonne;

import org.gla.carcassonne.Tile;

public class TileManager {

	private Tile [] tilesOnBoard;
	private Tile [] tiles = 
		{ new Tile(TileType.TILE_A), new Tile(TileType.TILE_A), 

		new Tile(TileType.TILE_B), new Tile(TileType.TILE_B), 
		new Tile(TileType.TILE_B), new Tile(TileType.TILE_B), 

		new Tile(TileType.TILE_C),

		new Tile(TileType.TILE_D), new Tile(TileType.TILE_D), 
		new Tile(TileType.TILE_D), 

		new Tile(TileType.TILE_E), new Tile(TileType.TILE_E), 
		new Tile(TileType.TILE_E), 	new Tile(TileType.TILE_E), 
		new Tile(TileType.TILE_E),

		new Tile(TileType.TILE_G), new Tile(TileType.TILE_G), 
		new Tile(TileType.TILE_G), 

		new Tile(TileType.TILE_H), new Tile(TileType.TILE_H), 
		new Tile(TileType.TILE_H), 

		new Tile(TileType.TILE_I), new Tile(TileType.TILE_I), 

		new Tile(TileType.TILE_J), new Tile(TileType.TILE_J), 
		new Tile(TileType.TILE_J), 
	
		new Tile(TileType.TILE_K), new Tile(TileType.TILE_K), 
		new Tile(TileType.TILE_K),
	
		new Tile(TileType.TILE_L), new Tile(TileType.TILE_L), 
		new Tile(TileType.TILE_L),

		new Tile(TileType.TILE_N), new Tile(TileType.TILE_N), 
		new Tile(TileType.TILE_N), new Tile(TileType.TILE_N), 
		new Tile(TileType.TILE_N),

		new Tile(TileType.TILE_P), new Tile(TileType.TILE_P), 
		new Tile(TileType.TILE_P), new Tile(TileType.TILE_P), 
		new Tile(TileType.TILE_P),

		new Tile(TileType.TILE_R), new Tile(TileType.TILE_R), 
		new Tile(TileType.TILE_R), new Tile(TileType.TILE_R), 

		new Tile(TileType.TILE_T), new Tile(TileType.TILE_T), 
		new Tile(TileType.TILE_T), 
		
		new Tile(TileType.TILE_U), new Tile(TileType.TILE_U), 
		new Tile(TileType.TILE_U), new Tile(TileType.TILE_U), 
		new Tile(TileType.TILE_U), new Tile(TileType.TILE_U), 
		new Tile(TileType.TILE_U), 

		new Tile(TileType.TILE_V), new Tile(TileType.TILE_V), 
		new Tile(TileType.TILE_V), new Tile(TileType.TILE_V), 
		new Tile(TileType.TILE_V), new Tile(TileType.TILE_V), 
		new Tile(TileType.TILE_V), new Tile(TileType.TILE_V), 
		new Tile(TileType.TILE_V),


		new Tile(TileType.TILE_W), new Tile(TileType.TILE_W), 
		new Tile(TileType.TILE_W), new Tile(TileType.TILE_W), 

		new Tile(TileType.TILE_X) 
	};

	private final static int MAX_TILE_NUMBER = 100;

	public TileManager() {
		tiles = new Tile[MAX_TILE_NUMBER];
		tilesOnBoard = new Tile[MAX_TILE_NUMBER];
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public Tile[] getTilesOnBoard() {
		return tilesOnBoard;
	}

}
