package org.gla.carcassonne.managers;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gla.carcassonne.CarcassonneModel;
import org.gla.carcassonne.entities.Board;
import org.gla.carcassonne.entities.Player;
import org.gla.carcassonne.entities.Status;
import org.gla.carcassonne.entities.Tile;
import org.gla.carcassonne.entities.TileSideValue;
import org.gla.carcassonne.entities.TileType;
import org.gla.carcassonne.utils.RandomGenerator;

public class TileManager {
	private Board board;
	private Map<TileType, Integer> tiles;
	private int numberOfTileRemaining;
	private final static int MAX_TILE_NUMBER = 72;
	private Tile currentTile;
	private CarcassonneModel model;
	private boolean currentPlayerhasPlacedTile;
	private Set<Tile> tilesWherePieceFound;
	private Set<Tile> tilesTraversed;
	public static final int BUTTON_WIDTH = 72;
	private static final int MONK_POINT = 9;

	private static final int[] tilesCount = new int[] {
			// f, m, n o, q, s sont des tuiles d'extensions
			// inutiles pour l'instant
			2, // Tuile A
			4, // Tuile B
			1, // Tuile C
			4, // Tuile D
			5, // Tuile E
			3, // Tuile G
			3, // Tuile H
			2, // Tuile I
			3, // Tuile J
			3, // Tuile K
			3, // Tuile L
			5, // Tuile N
			5, // Tuile P
			4, // Tuile R
			3, // Tuile T
			8, // Tuile U
			9, // Tuile V
			4, // Tuile W
			1 // Tuile X
	};

	public TileManager(CarcassonneModel model) {
		int i = 0;
		currentPlayerhasPlacedTile = false;
		numberOfTileRemaining = MAX_TILE_NUMBER;
		currentTile = null;
		board = new Board(model);
		tilesWherePieceFound = new HashSet<Tile>();
		tilesTraversed = new HashSet<Tile>();
		this.model = model;
		tiles = new EnumMap<TileType, Integer>(TileType.class);

		for (TileType t : TileType.values()) {
			tiles.put(t, tilesCount[i++]);
		}
	}

	public void putFirstTileOnBoard() {
		currentTile = selectTileRandomly();
		remove(currentTile);
		board.add(2, 2, currentTile);
		getNextTile();
	}

	public Tile selectTileRandomly() {
		RandomGenerator generator = new RandomGenerator(tiles);
		TileType t = generator.getRandomTileType();
		return new Tile(t);
	}

	public void remove(Tile tile) {
		numberOfTileRemaining--;
		model.fireRemainingTile();
		int count = tiles.containsKey(tile.getType()) ? tiles.get(tile
				.getType()) : 0;

		// S'il ne reste plus qu'une carte, on va l'enlever de notre Map car il
		// n'existera
		// plus de carte de ce type au prochain tour (et donc l'exclure du
		// random au prochain tour)
		if (count > 1)
			tiles.put(tile.getType(), count - 1);
		else
			tiles.remove(tile.getType());
	}

	public void getNextTile() {
		if (numberOfTileRemaining > 0) {
			currentTile = selectTileRandomly();
			if (board.canPlaceSomeWhere(currentTile)) {
				remove(currentTile);
				currentPlayerhasPlacedTile = false;
				model.fireLockConfirmButton();
				model.fireNextTile();
				return;
			} else
				getNextTile();
		}
	}

	public Board getBoard() {
		return board;
	}

	public Map<TileType, Integer> getTiles() {
		return tiles;
	}

	public int getNumberOfTileRemaining() {
		return numberOfTileRemaining;
	}

	public Tile getCurrentTile() {
		return currentTile;
	}

	public void rotateLeft() {
		currentTile.rotateLeft();
		model.fireRotateLeft();
	}

	public void rotateRight() {
		currentTile.rotateRight();
		model.fireRotateRight();
	}

	public void placePieceOnTile(int xOnBoard, int yOnBoard, MouseEvent arg0) {
		if (currentTile.getxOnBoard() != xOnBoard
				|| currentTile.getyOnBoard() != yOnBoard) {
			return;
		} else {
			int yOnTile = arg0.getX() * Tile.CELL_WIDTH / BUTTON_WIDTH;
			int xOnTile = arg0.getY() * Tile.CELL_WIDTH / BUTTON_WIDTH;
			Player player = model.getPlayerManager().getCurrentPlayer();
			if (currentTile.getZoneValue(xOnTile, yOnTile) == Status.NONE
					|| player.getPieceCount() == 0
					|| !isPossibleToPlacePiece(xOnTile, yOnTile, xOnBoard,
							yOnBoard)) {
				return;
			}
			currentTile.setxOnTile(xOnTile);
			currentTile.setyOnTile(yOnTile);
			currentTile.setStatus();
			currentTile.setxOnClick(arg0.getX());
			currentTile.setyOnClick(arg0.getY());
			if (!model.getPlayerManager().getCurrentPlayerhasPlacedPiece()) {
				model.getPlayerManager().setCurrentPlayerhasPlacedPiece(true);
			}
			currentTile.setPlayer(player);
			model.firePlacePieceOnTile();
		}
	}

	private boolean isPossibleToPlacePiece(int xOnTile, int yOnTile,
			int xOnBoard, int yOnBoard) {
		boolean[][][][] boolMap = new boolean[board.getBoard().length][board
				.getBoard()[0].length][7][7];
		return !findPiece(xOnTile, yOnTile, boolMap, currentTile, xOnBoard,
				yOnBoard);
	}

	private boolean findPiece(int xOnTile, int yOnTile, boolean[][][][] boolMap,
			Tile tile, int xOnBoard, int yOnBoard) {
		boolean res = false;
//		 System.out.println("lancerPropagationSurTuile(" + xOnTile + ", " +
//		 yOnTile + ", " + tile.getType().getPath() +")");
		boolMap[xOnBoard][yOnBoard][xOnTile][yOnTile] = true;

		if (tile != currentTile 
			&& tile.getxOnTile() == xOnTile
			&& tile.getyOnTile() == yOnTile) {
//			 System.out.println("Pion Trouvé sur zone!");
			res = true;
		}

		if ((xOnTile + 1) < 7
			&& (tile.getZoneValue(xOnTile, yOnTile) 
				== tile.getZoneValue(xOnTile + 1, yOnTile))
			&& (!boolMap[xOnBoard][yOnBoard][xOnTile + 1][yOnTile])) {
			res = res || findPiece(xOnTile + 1, yOnTile, boolMap, tile, xOnBoard,
							yOnBoard);
		} else if ((xOnTile + 1 == 7) 
					&& (yOnBoard - 1) >= 0) {
			Tile nextTile = getBoard().getBoard()[xOnBoard][yOnBoard - 1];
			if (nextTile != null
				&& tile.getSideValue(Tile.SOUTH) 
					== nextTile.getSideValue(Tile.NORTH)
				&& tile.getZoneValue(xOnTile, yOnTile) 
					== nextTile.getZoneValue(0, yOnTile)
				&& !boolMap[xOnBoard][yOnBoard - 1][0][yOnTile]) {
				res = res || findPiece(0, yOnTile, boolMap, nextTile, xOnBoard,
								yOnBoard - 1);
			}
		}

		if ((xOnTile - 1) >= 0
			&& tile.getZoneValue(xOnTile, yOnTile) 
				== tile.getZoneValue(xOnTile - 1, yOnTile)
			&& !boolMap[xOnBoard][yOnBoard][xOnTile - 1][yOnTile]) {
			res = res || findPiece(xOnTile - 1, yOnTile, boolMap, tile, xOnBoard,
							yOnBoard);
		} else if ((xOnTile - 1) < 0
				&& (yOnBoard + 1) < board.getBoard()[0].length) {
			Tile nextTile = getBoard().getBoard()[xOnBoard][yOnBoard + 1];
			if (nextTile != null
				&& tile.getSideValue(Tile.NORTH) 
					== nextTile.getSideValue(Tile.SOUTH)
				&& tile.getZoneValue(xOnTile, yOnTile) 
					== nextTile.getZoneValue(6, yOnTile)
				&& !boolMap[xOnBoard][yOnBoard + 1][6][yOnTile]) {
				res = res || findPiece(6, yOnTile, boolMap, nextTile, xOnBoard,
								yOnBoard + 1);
			}
		}

		if ((yOnTile + 1) < 7
			&& tile.getZoneValue(xOnTile, yOnTile) 
				== tile.getZoneValue(xOnTile, yOnTile + 1)
			&& !boolMap[xOnBoard][yOnBoard][xOnTile][yOnTile + 1]) {
			res = res || findPiece(xOnTile, yOnTile + 1, boolMap, tile, xOnBoard,
							yOnBoard);
		} else if ((yOnTile + 1) == 7
				&& (xOnBoard + 1) < board.getBoard().length) {
			Tile nextTile = getBoard().getBoard()[xOnBoard + 1][yOnBoard];
			if (nextTile != null
				&& tile.getSideValue(Tile.EAST) 
					== nextTile.getSideValue(Tile.WEST)
				&& tile.getZoneValue(xOnTile, yOnTile) 
					== nextTile.getZoneValue(xOnTile, 0)
				&& !boolMap[xOnBoard + 1][yOnBoard][xOnTile][0]) {
				res = res || findPiece(xOnTile, 0, boolMap, nextTile,
								xOnBoard + 1, yOnBoard);
			}
		}

		if ((yOnTile - 1) >= 0
			&& tile.getZoneValue(xOnTile, yOnTile) 
				== tile.getZoneValue(xOnTile, yOnTile - 1)
			&& !boolMap[xOnBoard][yOnBoard][xOnTile][yOnTile - 1]) {
			res = res || findPiece(xOnTile, yOnTile - 1, boolMap, tile, xOnBoard,
							yOnBoard);
		} else if ((yOnTile - 1) < 0 && (xOnBoard - 1) >= 0) {
			Tile nextTile = getBoard().getBoard()[xOnBoard - 1][yOnBoard];
			if (nextTile != null
				&& tile.getSideValue(Tile.WEST) 
					== nextTile.getSideValue(Tile.EAST)
				&& tile.getZoneValue(xOnTile, yOnTile) 
					== nextTile.getZoneValue(xOnTile, 6)
				&& !boolMap[xOnBoard - 1][yOnBoard][xOnTile][6]) {
				res = res || findPiece(xOnTile, 6, boolMap, nextTile,
								xOnBoard - 1, yOnBoard);
			}
		}
		return res;
	}
	
	private boolean findExit(int xOnTile, int yOnTile, boolean[][][][] boolMap,
			Tile tile, int xOnBoard, int yOnBoard) {
		boolean res = false;
//		 System.out.println("lancerPropagationSurTuile(" + xOnTile + ", " +
//		 yOnTile + ", " + tile.getType().getPath() +")");
		boolMap[xOnBoard][yOnBoard][xOnTile][yOnTile] = true;
		tilesTraversed.add(board.getBoard()[xOnBoard][yOnBoard]);

		if (tile.getxOnTile() == xOnTile
			&& tile.getyOnTile() == yOnTile) {
			 System.out.println("Pion Trouvé sur zone!");
			 tilesWherePieceFound.add(board.getBoard()[xOnBoard][yOnBoard]);
		}

		if ((xOnTile + 1) < 7
			&& (tile.getZoneValue(xOnTile, yOnTile) 
				== tile.getZoneValue(xOnTile + 1, yOnTile))
			&& (!boolMap[xOnBoard][yOnBoard][xOnTile + 1][yOnTile])) {
			res = res || findExit(xOnTile + 1, yOnTile, boolMap, tile, xOnBoard,
							yOnBoard);
		} else if ((xOnTile + 1 == 7) 
					&& (yOnBoard - 1) >= 0) {
			Tile nextTile = getBoard().getBoard()[xOnBoard][yOnBoard - 1];
			if(nextTile != null){
				if (tile.getSideValue(Tile.SOUTH) 
						== nextTile.getSideValue(Tile.NORTH)
					&& tile.getZoneValue(xOnTile, yOnTile) 
						== nextTile.getZoneValue(0, yOnTile)
					&& !boolMap[xOnBoard][yOnBoard - 1][0][yOnTile]) {
					res = res || findExit(0, yOnTile, boolMap, nextTile, xOnBoard,
									yOnBoard - 1);
				}
			} else {
				res = true;
			}
		}

		if ((xOnTile - 1) >= 0
			&& tile.getZoneValue(xOnTile, yOnTile) 
				== tile.getZoneValue(xOnTile - 1, yOnTile)
			&& !boolMap[xOnBoard][yOnBoard][xOnTile - 1][yOnTile]) {
			res = res || findExit(xOnTile - 1, yOnTile, boolMap, tile, xOnBoard,
							yOnBoard);
		} else if ((xOnTile - 1) < 0
				&& (yOnBoard + 1) < board.getBoard()[0].length) {
			Tile nextTile = getBoard().getBoard()[xOnBoard][yOnBoard + 1];
			if(nextTile != null){
				if (tile.getSideValue(Tile.NORTH) 
						== nextTile.getSideValue(Tile.SOUTH)
					&& tile.getZoneValue(xOnTile, yOnTile) 
						== nextTile.getZoneValue(6, yOnTile)
					&& !boolMap[xOnBoard][yOnBoard + 1][6][yOnTile]) {
					res = res || findExit(6, yOnTile, boolMap, nextTile, xOnBoard,
									yOnBoard + 1);
				}
			} else {
				res = true;
			}
		}

		if ((yOnTile + 1) < 7
			&& tile.getZoneValue(xOnTile, yOnTile) 
				== tile.getZoneValue(xOnTile, yOnTile + 1)
			&& !boolMap[xOnBoard][yOnBoard][xOnTile][yOnTile + 1]) {
			res = res || findExit(xOnTile, yOnTile + 1, boolMap, tile, xOnBoard,
							yOnBoard);
		} else if ((yOnTile + 1) == 7
				&& (xOnBoard + 1) < board.getBoard().length) {
			Tile nextTile = getBoard().getBoard()[xOnBoard + 1][yOnBoard];
			if(nextTile != null){
				if (tile.getSideValue(Tile.EAST) 
						== nextTile.getSideValue(Tile.WEST)
					&& tile.getZoneValue(xOnTile, yOnTile) 
						== nextTile.getZoneValue(xOnTile, 0)
					&& !boolMap[xOnBoard + 1][yOnBoard][xOnTile][0]) {
					res = res || findExit(xOnTile, 0, boolMap, nextTile,
									xOnBoard + 1, yOnBoard);
				}
			} else {
				res = true;
			}
		}

		if ((yOnTile - 1) >= 0
			&& tile.getZoneValue(xOnTile, yOnTile) 
				== tile.getZoneValue(xOnTile, yOnTile - 1)
			&& !boolMap[xOnBoard][yOnBoard][xOnTile][yOnTile - 1]) {
			res = res || findExit(xOnTile, yOnTile - 1, boolMap, tile, xOnBoard,
							yOnBoard);
		} else if ((yOnTile - 1) < 0 && (xOnBoard - 1) >= 0) {
			Tile nextTile = getBoard().getBoard()[xOnBoard - 1][yOnBoard];
			if (nextTile != null) {
					if(tile.getSideValue(Tile.WEST) 
						== nextTile.getSideValue(Tile.EAST)
					&& tile.getZoneValue(xOnTile, yOnTile) 
						== nextTile.getZoneValue(xOnTile, 6)
					&& !boolMap[xOnBoard - 1][yOnBoard][xOnTile][6]) {
					res = res || findExit(xOnTile, 6, boolMap, nextTile,
									xOnBoard - 1, yOnBoard);
				}
			} else {
				res = true;
			}
		}
		return res;
	}
	
	public boolean getCurrentPlayerHasPlacedTile() {
		return currentPlayerhasPlacedTile;
	}

	public void setCurrentPlayerhasPlacedTile(boolean currentPlayerhasPlacedTile) {
		this.currentPlayerhasPlacedTile = currentPlayerhasPlacedTile;
	}

	public void resolveZoneClose() {
		List<Tile> nearTiles = new ArrayList<Tile>();
		nearTiles.add(currentTile);
		
		for(Tile tile : nearTiles){
			if(tile != null){
				resolveCity(tile);
			}
		}
		
		nearTiles.add(board.getBoard()[currentTile.getxOnBoard()][currentTile.getyOnBoard() + 1]);
		nearTiles.add(board.getBoard()[currentTile.getxOnBoard()][currentTile.getyOnBoard() - 1]);
		nearTiles.add(board.getBoard()[currentTile.getxOnBoard() + 1][currentTile.getyOnBoard()]);
		nearTiles.add(board.getBoard()[currentTile.getxOnBoard() - 1][currentTile.getyOnBoard()]);
		
		nearTiles.add(board.getBoard()[currentTile.getxOnBoard() + 1][currentTile.getyOnBoard() + 1]);
		nearTiles.add(board.getBoard()[currentTile.getxOnBoard() + 1][currentTile.getyOnBoard() - 1]);
		nearTiles.add(board.getBoard()[currentTile.getxOnBoard() - 1][currentTile.getyOnBoard() - 1]);
		nearTiles.add(board.getBoard()[currentTile.getxOnBoard() - 1][currentTile.getyOnBoard() + 1]);
		
		for(Tile tile : nearTiles) {
			if(tile != null) {
				resolveMonk(tile);
			}
		}
	}
	
	private void resolveCity(Tile tile) {
		int x = tile.getxOnBoard();
		int y = tile.getyOnBoard();
		int xOnTile = -1;
		int yOnTile = -1;
		boolean[][][][] boolMap = new boolean[board.getBoard().length][board.getBoard()[0].length][7][7];
		if(tile.getSideValue(Tile.NORTH) == TileSideValue.CITY
			|| tile.getSideValue(Tile.NORTH) == TileSideValue.ROAD){
			xOnTile = 0;
			yOnTile = 3;
				tilesWherePieceFound.clear();
				tilesTraversed.clear();
			if(!findExit(xOnTile, yOnTile, boolMap, currentTile, x, y)){
				System.out.println("ville fermée!");
				if(tile.getSideValue(Tile.NORTH) == TileSideValue.CITY)
					resolveCityPlayer();
				else 
					resolveRoadPlayer();
			} else {
				System.out.println("ville ouverte!");
			}
		}
		boolMap = new boolean[board.getBoard().length][board.getBoard()[0].length][7][7];
		if(tile.getSideValue(Tile.EAST) == TileSideValue.CITY
			|| tile.getSideValue(Tile.EAST) == TileSideValue.ROAD){
			xOnTile = 3;
			yOnTile = 6;
				tilesWherePieceFound.clear();
				tilesTraversed.clear();
			if(!findExit(xOnTile, yOnTile, boolMap, currentTile, x, y)){
				System.out.println("ville fermée!");
				if(tile.getSideValue(Tile.EAST) == TileSideValue.CITY)
					resolveCityPlayer();
				else 
					resolveRoadPlayer();
			} else {
				System.out.println("ville ouverte!");
			}
		}
		boolMap = new boolean[board.getBoard().length][board.getBoard()[0].length][7][7];
		if(tile.getSideValue(Tile.SOUTH) == TileSideValue.CITY
			|| tile.getSideValue(Tile.SOUTH) == TileSideValue.ROAD){
			xOnTile = 6;
			yOnTile = 3;
				tilesWherePieceFound.clear();
				tilesTraversed.clear();
			if(!findExit(xOnTile, yOnTile, boolMap, currentTile, x, y)){
				System.out.println("ville fermée!");
				if(tile.getSideValue(Tile.SOUTH) == TileSideValue.CITY)
					resolveCityPlayer();
				else 
					resolveRoadPlayer();
			} else {
				System.out.println("ville ouverte!");
			}
		}
		boolMap = new boolean[board.getBoard().length][board.getBoard()[0].length][7][7];
		if(tile.getSideValue(Tile.WEST) == TileSideValue.CITY
			|| tile.getSideValue(Tile.WEST) == TileSideValue.ROAD) {
			xOnTile = 3;
			yOnTile = 0;
				tilesWherePieceFound.clear();
				tilesTraversed.clear();
			if(!findExit(xOnTile, yOnTile, boolMap, currentTile, x, y)){
				System.out.println("ville fermée!");
				if(tile.getSideValue(Tile.WEST) == TileSideValue.CITY)
					resolveCityPlayer();
				else 
					resolveRoadPlayer();
			} else {
				System.out.println("ville ouverte!");
			}
		}
	}
	
	private void resolveRoadPlayer() {
		int bonusForPiece = 0;
		int bonusForTile = 1;
		Set<Player> playersOnZone = new HashSet<Player>();
		for(Tile t : tilesWherePieceFound){
			playersOnZone.add(t.getPlayer());
			t.getPlayer().setPieceCount(t.getPlayer().getPieceCount() + 1);
			t.getPlayer().setPoints(t.getPlayer().getPoints() + bonusForPiece);
			t.setxOnTile(-1);
			t.setxOnClick(-1);
			t.setyOnTile(-1);
			t.setyOnClick(-1);
			t.setStatus(null);
			t.setPlayer(null);
		}
		for(Player player : playersOnZone)
			player.setPoints(player.getPoints() + tilesTraversed.size() * bonusForTile);
		model.firePlayers();
		model.fireBoard();
	}

	public void resolveMonk(Tile tile) {
		int point = 0;
		if(tile.getStatus() == Status.MONK && surroundedByTiles(tile)){
			point = MONK_POINT;
			tile.setxOnTile(-1);
			tile.setxOnClick(-1);
			tile.setyOnTile(-1);
			tile.setyOnClick(-1);
			tile.setStatus(null);		
			Player player = tile.getPlayer();
			player.setPoints(player.getPoints() + point);
			player.setPieceCount(player.getPieceCount() + 1);
			tile.setPlayer(null);
			model.firePlayers();
			model.fireBoard();
		}
	}
	
	public void resolveCityPlayer() {
		int bonusForPiece = 2;
		int bonusForTile = 2;
		Set<Player> playersOnZone = new HashSet<Player>();
		for(Tile t : tilesWherePieceFound){
			playersOnZone.add(t.getPlayer());
			t.getPlayer().setPieceCount(t.getPlayer().getPieceCount() + 1);
			t.getPlayer().setPoints(t.getPlayer().getPoints() + bonusForPiece);
			t.setxOnTile(-1);
			t.setxOnClick(-1);
			t.setyOnTile(-1);
			t.setyOnClick(-1);
			t.setStatus(null);
			t.setPlayer(null);
		}
		for(Player player : playersOnZone)
			player.setPoints(player.getPoints() + tilesTraversed.size() * bonusForTile);
		model.firePlayers();
		model.fireBoard();
	}
	
	public boolean surroundedByTiles(Tile tile) {
		int x = tile.getxOnBoard();
		int y = tile.getyOnBoard();
		if(board.getBoard()[x + 1][y] == null
			|| board.getBoard()[x - 1][y] == null
			|| board.getBoard()[x][y + 1] == null
			|| board.getBoard()[x][y - 1] == null
			|| board.getBoard()[x + 1][y + 1] == null
			|| board.getBoard()[x + 1][y - 1] == null
			|| board.getBoard()[x - 1][y + 1] == null
			|| board.getBoard()[x - 1][y - 1] == null) {
			return false;
		}
		return  true;
	}
		
}