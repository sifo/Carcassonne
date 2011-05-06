package org.gla.carcassonne;

enum TileSideValue {
	ROAD,
	GRASS,
	CITY
}

enum Status {
	KNIGHT,
	MONK,
	FARMER,
	THIEF
}

public class Tile {
	private int x;
	private int y;
	private int rotationCount;
	private TileType type;
	private TileSideValue[] sideValues;
	private Player player;
	private Status[][] zones;

	public final static int NORTH = 0;
	public final static int EAST = 1;
	public final static int SOUTH = 2;
	public final static int WEST = 3;

	public Tile(TileType type) {
		this.x = -1;
		this.y = -1;
		this.rotationCount = 0;
		this.setType(type);
		this.setSideValues(type);
		this.setZoneValues(type);
		this.player = null;
	}
	
	public void setZoneValues(TileType type) {
		switch(type) {
		case TILE_A:
			setZones(new Status[][] {
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.MONK,Status.MONK,Status.MONK,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.MONK,Status.MONK,Status.MONK,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.MONK,Status.MONK,Status.MONK,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER}});
			break;
		case TILE_B:
			setZones(new Status[][] {
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.MONK,Status.MONK,Status.MONK,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.MONK,Status.MONK,Status.MONK,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.MONK,Status.MONK,Status.MONK,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER}
	});
			break;
		case TILE_C:
			setZones(new Status[][] {
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT}
	});
			break;
		case TILE_D:
			setZones(new Status[][] {
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.KNIGHT}
	});
			break;
		case TILE_E:
			setZones(new Status[][] {
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER}
	});
			break;
		/*case TILE_F:
	*/
		case TILE_G:
			setZones(new Status[][] {
	{Status.FARMER,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.FARMER}
	});
			break;
		case TILE_H:
			setZones(new Status[][] {
	{Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT}
					});
		case TILE_I:
			setZones(new Status[][] {
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT}
					});
			break;
		case TILE_J:
			setZones(new Status[][] {
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.THIEF,Status.THIEF,Status.THIEF},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER}
	});
			break;
		case TILE_K:
			setZones(new Status[][] {
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.THIEF,Status.THIEF,Status.THIEF,Status.THIEF,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT}});
			break;
		case TILE_L:			
			setZones(new Status[][] {
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.THIEF,Status.THIEF,Status.THIEF,Status.FARMER,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.KNIGHT,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.KNIGHT}});
			break;
		/*case TILE_M:

			break;*/
		case TILE_N:
			setZones(new Status[][] {
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.FARMER},
	{Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER}
	});
			break;
		/*case TILE_O:

			break;*/
		case TILE_P:
			setZones(new Status[][] {
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.FARMER},
	{Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.FARMER,Status.THIEF,Status.THIEF,Status.THIEF},
	{Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.KNIGHT,Status.KNIGHT,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.KNIGHT,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER}
	});
			break;
		/*case TILE_Q:

			break;*/
		case TILE_R:
			setZones(new Status[][] {
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER}
	});
			break;
		/*case TILE_S:

			break;*/
		case TILE_T:
			setZones(new Status[][] {
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT,Status.KNIGHT},
	{Status.KNIGHT,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.KNIGHT},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER}
	});
			break;
		case TILE_U:
			setZones(new Status[][] {
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER}
	});
			break;
		case TILE_V:
			setZones(new Status[][] {
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.THIEF,Status.THIEF,Status.THIEF,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER}
					});
			break;
		case TILE_W:
			setZones(new Status[][] {
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.THIEF,Status.THIEF,Status.THIEF,Status.FARMER,Status.THIEF,Status.THIEF,Status.THIEF},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER}
					});
			break;
		case TILE_X:
			setZones(new Status[][] {
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.THIEF,Status.THIEF,Status.THIEF,Status.FARMER,Status.THIEF,Status.THIEF,Status.THIEF},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER},
	{Status.FARMER,Status.FARMER,Status.FARMER,Status.THIEF,Status.FARMER,Status.FARMER,Status.FARMER}
					});
			break;
		}
	}

	public void setSideValues(TileType type) {
		switch(type) {
			case TILE_A:
				sideValues = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.ROAD,
						TileSideValue.GRASS};
				break;
			case TILE_B:
				sideValues = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.GRASS};
				break;
			case TILE_C:
				sideValues = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.CITY};
				break;
			case TILE_D:
				sideValues = new TileSideValue[] {
						TileSideValue.ROAD,
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.GRASS};
				break;
			case TILE_E:
				sideValues = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.GRASS};
				break;
			/*case TILE_F:
				sideValue = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.CITY};
				break;*/
			case TILE_G:
				sideValues = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.CITY,
						TileSideValue.GRASS};
				break;
			case TILE_H:
				sideValues = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.CITY};
				break;
			case TILE_I:
				sideValues = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.GRASS};
				break;
			case TILE_J:
				sideValues = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.ROAD,
						TileSideValue.GRASS};
				break;
			case TILE_K:
				sideValues = new TileSideValue[] {
						TileSideValue.ROAD,
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.ROAD};
				break;
			case TILE_L:
				sideValues = new TileSideValue[] {
						TileSideValue.ROAD,
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.ROAD};
				break;
			/*case TILE_M:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.CITY};
				break;*/
			case TILE_N:
				sideValues = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.CITY};
				break;
			/*case TILE_O:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.ROAD,
						TileSideValue.CITY};
				break;*/
			case TILE_P:
				sideValues = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.ROAD,
						TileSideValue.CITY};
				break;
			/*case TILE_Q:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.CITY};
				break;*/
			case TILE_R:
				sideValues = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.GRASS,
						TileSideValue.CITY};
				break;
			/*case TILE_S:
				sideValue = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.CITY};
				break;*/
			case TILE_T:
				sideValues = new TileSideValue[] {
						TileSideValue.CITY,
						TileSideValue.CITY,
						TileSideValue.ROAD,
						TileSideValue.CITY};
				break;
			case TILE_U:
				sideValues = new TileSideValue[] {
						TileSideValue.ROAD,
						TileSideValue.GRASS,
						TileSideValue.ROAD,
						TileSideValue.GRASS};
				break;
			case TILE_V:
				sideValues = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.GRASS,
						TileSideValue.ROAD,
						TileSideValue.ROAD};
				break;
			case TILE_W:
				sideValues = new TileSideValue[] {
						TileSideValue.GRASS,
						TileSideValue.ROAD,
						TileSideValue.ROAD,
						TileSideValue.ROAD};
				break;
			case TILE_X:
				sideValues = new TileSideValue[] {
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
		return sideValues[side];
	}

	public TileSideValue[] getSideValues() {
		return sideValues;
	}

	public void setSideValues(TileSideValue[] sideValues) {
		this.sideValues = sideValues;
	}

	public TileType getType() {
		return type;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setRotationCount(int rotationCount) {
		this.rotationCount = rotationCount;
	}

	public int getRotationCount() {
		return rotationCount;
	}

	public void setZones(Status[][] zones) {
		this.zones = zones;
	}

	public Status[][] getZones() {
		return zones;
	}
}
