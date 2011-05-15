package org.gla.carcassonne.entities;

/*
 * Enumération des différentes types de cartes
 */
public enum TileType {
	TILE_A ("res/drawable/tile-a.png"),
	TILE_B ("res/drawable/tile-b.png"),
	TILE_C ("res/drawable/tile-c.png", true),
	TILE_D ("res/drawable/tile-d.png"),
	TILE_E ("res/drawable/tile-e.png"),
	TILE_F ("res/drawable/tile-f.png", true),
	TILE_G ("res/drawable/tile-g.png"),
	TILE_H ("res/drawable/tile-h.png"),
	TILE_I ("res/drawable/tile-i.png"),
	TILE_J ("res/drawable/tile-j.png"),
	TILE_K ("res/drawable/tile-k.png"),
	TILE_L ("res/drawable/tile-l.png"),
	TILE_M ("res/drawable/tile-m.png", true),
	TILE_N ("res/drawable/tile-n.png"),
	TILE_O ("res/drawable/tile-o.png", true),
	TILE_P ("res/drawable/tile-p.png"),
	TILE_Q ("res/drawable/tile-q.png", true),
	TILE_R ("res/drawable/tile-r.png"),
	TILE_S ("res/drawable/tile-s.png", true),
	TILE_T ("res/drawable/tile-t.png"),
	TILE_U ("res/drawable/tile-u.png"),
	TILE_V ("res/drawable/tile-v.png"),
	TILE_W ("res/drawable/tile-w.png"),
	TILE_X ("res/drawable/tile-x.png");

	private final String path;
	private final boolean hasPennant;
	
	TileType(String path) {
		this.path = path;
		this.hasPennant = false;
	}
	
	TileType(String path, boolean hasPennant) {
		this.path = path;
		this.hasPennant = hasPennant;
	}

	public String getPath() {
		return path;
	}
	
	public boolean hasPennant() {
		return hasPennant;
	}
}