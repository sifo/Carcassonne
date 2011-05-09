package org.gla.carcassonne.entities;

public class TileZone {
	
    private int mask;	// permet de cumuler les propriétés d'une tuile (sides)
    
    public static final TileZone CENTER;
    public static final TileZone NORTH;
    public static final TileZone EAST;
    public static final TileZone SOUTH;
    public static final TileZone WEST;
    public static final TileZone NORTH_LEFT;
    public static final TileZone NORTH_RIGHT;
    public static final TileZone EAST_LEFT;
    public static final TileZone EAST_RIGHT;
    public static final TileZone SOUTH_LEFT;
    public static final TileZone SOUTH_RIGHT;
    public static final TileZone WEST_LEFT;
    public static final TileZone WEST_RIGHT;
    
    public static final TileZone NORTH_WEST;
    public static final TileZone NORTH_EAST;
    public static final TileZone SOUTH_EAST;
    public static final TileZone SOUTH_WEST;
    
    /*
     * La tuile est découpée en 13 zones surlesquelles on peut poser une pièce
     */
    static 
    {
    	CENTER = new TileZone(0);
    	NORTH = new TileZone(1);
    	EAST = new TileZone(2);
    	SOUTH = new TileZone(4);
    	WEST = new TileZone(8);
    	NORTH_LEFT = new TileZone(16);
    	NORTH_RIGHT = new TileZone(32);
    	EAST_LEFT = new TileZone(64);
    	EAST_RIGHT = new TileZone(128);
    	SOUTH_LEFT = new TileZone(256);
    	SOUTH_RIGHT = new TileZone(512);
    	WEST_LEFT = new TileZone(1024);
    	WEST_RIGHT = new TileZone(2048);
    	
    	NORTH_WEST = new TileZone(16+2048);		// NORTH_LEFT + WEST_RIGHT
    	NORTH_EAST = new TileZone(32+64);		// NORTH_RIGHT + EAST_LEFT
    	SOUTH_EAST = new TileZone(128+256);		// EAST_RIGHT + SOUTH_LEFT
    	SOUTH_WEST = new TileZone(512+1024);	// SOUTH_RIGHT + WEST_LEFT
    }
 
    private TileZone(int mask)
    {
        this.mask = mask;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        
        if (!(obj instanceof TileZone))
            return false;
        
        return mask == ((TileZone)obj).mask;
    }
}
