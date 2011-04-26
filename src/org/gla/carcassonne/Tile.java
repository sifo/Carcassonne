package org.gla.carcassonne;

// Valeurs (dessins) possibles pour chaque côté d'une tuile
enum TileSideValue {
	ROAD,
	GRASS,
	CITY
}

public class Tile {
	// Définition des 4 côtés d'une tuile
	public final static int NORTH = 0;
	public final static int EAST = 1;
	public final static int SOUTH = 2;
	public final static int WEST = 3;
	
	private TileType type;
	private TileSideValue[] sideValue;
	private Player player;	// appartenance de la pièce
	
	public Tile(TileType type) {
		this.setType(type);
		this.setSideValues(type);
		this.player = null;
	}
	
	public void setSideValues(TileType type) {
		switch(type) {
			case TILE_A:
				sideValue = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.ROAD,
						TileSideValue.GRASS};
				break;
			case TILE_B:
				sideValue = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.GRASS};
				break;
			case TILE_C:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.CITY};
				break;
			case TILE_D:
				sideValue = new TileSideValue[] {
						TileSideValue.ROAD,
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.GRASS};
				break;
			case TILE_E:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.GRASS};
				break;
			case TILE_F:
				sideValue = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.CITY};
				break;
			case TILE_G:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.CITY,
						TileSideValue.GRASS};
				break;
			case TILE_H:
				sideValue = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.CITY};
				break;
			case TILE_I:
				sideValue = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.GRASS};
				break;
			case TILE_J:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.ROAD,
						TileSideValue.GRASS};
				break;
			case TILE_K:
				sideValue = new TileSideValue[] {
						TileSideValue.ROAD,
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.ROAD};
				break;
			case TILE_L:
				sideValue = new TileSideValue[] {
						TileSideValue.ROAD,
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.ROAD};
				break;
			case TILE_M:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.CITY};
				break;
			case TILE_N:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.CITY};
				break;
			case TILE_O:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.ROAD,
						TileSideValue.CITY};
				break;
			case TILE_P:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.ROAD,
						TileSideValue.CITY};
				break;
			case TILE_Q:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.CITY};
				break;
			case TILE_R:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.CITY};
				break;
			case TILE_S:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.CITY};
				break;
			case TILE_T:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.CITY};
				break;
			case TILE_U:
				sideValue = new TileSideValue[] {
						TileSideValue.ROAD,
						TileSideValue.GRASS,
						TileSideValue.ROAD,
						TileSideValue.GRASS};
				break;
			case TILE_V:
				sideValue = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.ROAD,
						TileSideValue.ROAD};
				break;
			case TILE_W:
				sideValue = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.ROAD,
						TileSideValue.ROAD,
						TileSideValue.ROAD};
				break;
			case TILE_X:
				sideValue = new TileSideValue[] {
						TileSideValue.ROAD,
						TileSideValue.ROAD,
						TileSideValue.ROAD,
						TileSideValue.ROAD};
				break;
		}
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public TileSideValue getSideValue(int side) {
		return sideValue[side];
	}
	
	public TileType getType() {
		return type;
	}
}
