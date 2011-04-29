package org.gla.carcassonne;

public class TileManager {
        private int numberOfTileRemaining;
        private Board board;
        //private Map<TileType, Integer> tiles;
        private Tile [] tilesRemaining = 
                { new Tile(TileType.TILE_A), new Tile(TileType.TILE_A), 

                new Tile(TileType.TILE_B), new Tile(TileType.TILE_B), 
                new Tile(TileType.TILE_B), new Tile(TileType.TILE_B), 

                new Tile(TileType.TILE_C),

                new Tile(TileType.TILE_D), new Tile(TileType.TILE_D), 
                new Tile(TileType.TILE_D), new Tile(TileType.TILE_D), 

                new Tile(TileType.TILE_E), new Tile(TileType.TILE_E), 
                new Tile(TileType.TILE_E), new Tile(TileType.TILE_E), 
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
                new Tile(TileType.TILE_U), new Tile(TileType.TILE_U), 

                new Tile(TileType.TILE_V), new Tile(TileType.TILE_V), 
                new Tile(TileType.TILE_V), new Tile(TileType.TILE_V), 
                new Tile(TileType.TILE_V), new Tile(TileType.TILE_V), 
                new Tile(TileType.TILE_V), new Tile(TileType.TILE_V), 
                new Tile(TileType.TILE_V),


                new Tile(TileType.TILE_W), new Tile(TileType.TILE_W), 
                new Tile(TileType.TILE_W), new Tile(TileType.TILE_W), 

                new Tile(TileType.TILE_X) 
        };
        /*
        private Map<TileType, Integer> tilesRemaining;
        private static final int[] tilesCount = new int[] {
        2, // Tuile A
        2, // Tuile B
        2, // Tuile C
        2, // Tuile D
        2, // Tuile E
        2, // Tuile F
        2, // Tuile G
        2, // Tuile H
        2, // Tuile I
        2, // Tuile J
        2, // Tuile K
        2, // Tuile L
        2, // Tuile M
        2, // Tuile N
        2, // Tuile O
        2, // Tuile P
        2, // Tuile Q
        2, // Tuile R
        2, // Tuile S
        2, // Tuile T
        2, // Tuile U
        2, // Tuile V
        2, // Tuile W
        2 // Tuile X
        };*/
        
        private final static int MAX_TILE_NUMBER = 72;

        public TileManager() {
                numberOfTileRemaining = MAX_TILE_NUMBER;
                board = new Board();
                /*tiles = new EnumMap<TileType, Integer>(TileType.class);

                for (TileType t : TileType.values()) {
                tiles.put(t, tilesCount[i++]);
                }*/
        }

        public void putFirstTileOnBoard() {
                int tilePosition = selectTileRemainingRandomly();
                board.add(0, 0, tilesRemaining[tilePosition]);
                removeTileRemaining(tilePosition);
        }

        public int selectTileRemainingRandomly() {
                int lower = 0;
                int higher = numberOfTileRemaining;
                int tilePosition = (int)(Math.random() * (higher - lower)) + lower;
                return tilePosition;
        }
        /*
        public Tile selectTileRandomly() {
        	RandomGenerator<EnumMap<TileType, Integer>> generator = new RandomGenerator<EnumMap<TileType, Integer>>(TileType.class);
        	TileType t = generator.random();
        	return new Tile(t);
        }*/
        
        public void removeTileRemaining(int tilePosition) {
                int lastTile = numberOfTileRemaining - 1; 
                tilesRemaining[tilePosition] = tilesRemaining[lastTile];
                numberOfTileRemaining--;
        }

        public boolean tilesRemainingContains(Tile tile) {
                for(int i = 0; i < numberOfTileRemaining; i++)
                        if(tilesRemaining[i] == tile)
                                return true;
                return false;
        }

        public Tile getNextTile() {
                int tilePosition = selectTileRemainingRandomly(); 
                Tile tile = tilesRemaining[tilePosition];
                removeTileRemaining(tilePosition);
                return tile;
        }

        public Tile[] getTilesRemaining() {
                return tilesRemaining;
        }

        public Board getBoard() {
                return board;
        }

        public int getNumberOfTileRemaining() {
                return numberOfTileRemaining;
        }
}