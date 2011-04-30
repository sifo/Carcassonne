package org.gla.carcassonne;

/*
 * Enumération des différentes types de cartes
 */
public enum TileType {
	//f, m, n o, q, s sont des tuiles d'extensions
	TILE_A ("res/drawable/tile-a.png"),
	TILE_B ("res/drawable/tile-b.png"),
	TILE_C ("res/drawable/tile-c.png"),
	TILE_D ("res/drawable/tile-d.png"),
	TILE_E ("res/drawable/tile-e.png"),
	//TILE_F ("res/drawable/tile-f.png"),
	TILE_G ("res/drawable/tile-g.png"),
	TILE_H ("res/drawable/tile-h.png"),
	TILE_I ("res/drawable/tile-i.png"),
	TILE_J ("res/drawable/tile-j.png"),
	TILE_K ("res/drawable/tile-k.png"),
	TILE_L ("res/drawable/tile-l.png"),
	//TILE_M ("res/drawable/tile-m.png"),
	TILE_N ("res/drawable/tile-n.png"),
	//TILE_O ("res/drawable/tile-o.png"),
	TILE_P ("res/drawable/tile-p.png"),
	//TILE_Q ("res/drawable/tile-q.png"),
	TILE_R ("res/drawable/tile-r.png"),
	//TILE_S ("res/drawable/tile-s.png"),
	TILE_T ("res/drawable/tile-t.png"),
	TILE_U ("res/drawable/tile-u.png"),
	TILE_V ("res/drawable/tile-v.png"),
	TILE_W ("res/drawable/tile-w.png"),
	TILE_X ("res/drawable/tile-x.png");

	private final String path;
	
	TileType(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
