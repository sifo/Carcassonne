package org.gla.carcassonne.managers;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
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
	private Tile currentTile;
	private CarcassonneModel model;
	private boolean currentPlayerhasPlacedTile;
	private Set<Tile> tilesWherePieceFound;
	private Set<Tile> tilesWherePieceFoundForEndGame;
	private Set<Tile> tilesTraversed;
	private Set<Set<Tile>> cities;
	private boolean gameFinished;
	public static final int BUTTON_WIDTH = 72;

	private static final int[] tilesCount = new int[] {
			2, // Tuile A
			4, // Tuile B
			1, // Tuile C
			4, // Tuile D
			5, // Tuile E
			2, // Tuile F
			1, // Tuile G
			3, // Tuile H
			2, // Tuile I
			3, // Tuile J
			3, // Tuile K
			3, // Tuile L
			2, // Tuile M
			3, // Tuile N
			2, // Tuile O
			3, // Tuile P
			1, // Tuile Q
			3, // Tuile R
			2, // Tuile S
			1, // Tuile T
			8, // Tuile U
			9, // Tuile V
			4, // Tuile W
			1 // Tuile X
	};

	public TileManager(CarcassonneModel model) {
		int i = 0;
		currentPlayerhasPlacedTile = false;
		numberOfTileRemaining = maxTileNumber();
		currentTile = null;
		board = new Board(model);
		tilesWherePieceFound = new HashSet<Tile>();
		tilesWherePieceFoundForEndGame = new HashSet<Tile>();
		tilesTraversed = new HashSet<Tile>();
		cities = new HashSet<Set<Tile>>();
		this.model = model;
		gameFinished = false;
		tiles = new EnumMap<TileType, Integer>(TileType.class);

		for (TileType t : TileType.values()) {
			tiles.put(t, tilesCount[i++]);
		}
	}

	private int maxTileNumber() {
		int res = 0;
		for(int i : tilesCount)
			res += i;
		return res;
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
				if(numberOfTileRemaining == 0)
					gameFinished = true;
				currentPlayerhasPlacedTile = false;
				model.fireLockConfirmButton();
				model.fireNextTile();
				return;
			} else {
				getNextTile();
			}
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
//		 System.out.println("findExit(" + xOnTile + ", " +
//		 yOnTile + ", " + tile.getType().getPath() +")");
		boolMap[xOnBoard][yOnBoard][xOnTile][yOnTile] = true;
		boolean test = tilesTraversed.add(board.getBoard()[xOnBoard][yOnBoard]);
		if(!test && gameFinished) {
//			System.out.println("Tuile non ajoutée (" + xOnBoard + ", " + yOnBoard + ")");
		}
		if (tile.getxOnTile() == xOnTile
			&& tile.getyOnTile() == yOnTile) {
//			 System.out.println("Pion Trouvé sur zone!");
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
	
	public void resolveEndGamePoint() {
		System.out.println("resolve end game points : ");
		Tile [][] boardFinal = board.getBoard();	
		boolean[][][][] boolMap = new boolean[board.getBoard().length][board.getBoard()[0].length][7][7];
		for(int i = 0; i < boardFinal.length; i++){
			for(int j = 0; j < boardFinal[0].length; j++){
				Tile tile = boardFinal[i][j];
				if(tile != null
					&& tile.getStatus() != null
					&& !tilesWherePieceFoundForEndGame.contains(tile)){
					if(tile.getStatus() == Status.MONK){
						resolveMonk(tile);
					} else {
						int x = tile.getxOnBoard();
						int y = tile.getyOnBoard();
						int xOnTile = tile.getxOnTile();
						int yOnTile = tile.getyOnTile();
						tilesWherePieceFound.clear();
						tilesTraversed.clear();
						boolMap = new boolean[board.getBoard().length][board.getBoard()[0].length][7][7];
						findExit(xOnTile, yOnTile, boolMap, tile, x, y);
						if(tile.getStatus() == Status.KNIGHT)
							resolveCityOrRoadOrFarmer(1, 1, Status.KNIGHT);
						else if(tile.getStatus() == Status.THIEF)
							resolveCityOrRoadOrFarmer(1, 0, Status.THIEF);
						else if(tile.getStatus() == Status.FARMER)
							resolveCityOrRoadOrFarmer(0, 0, Status.FARMER);
						tilesWherePieceFoundForEndGame.addAll(tilesWherePieceFound);
					}
				}
			}
		}
	}

	public void resolveZoneClose() {
		List<Tile> nearTiles = new ArrayList<Tile>();
		nearTiles.add(currentTile);
		
		for(Tile tile : nearTiles){
			if(tile != null){
				resolveCityOrRoad(tile);
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
	
	private void resolveCityOrRoad(Tile tile) {
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
				if(tile.getSideValue(Tile.NORTH) == TileSideValue.CITY) {
					resolveCityOrRoadOrFarmer(2, 2, Status.KNIGHT);
					cities.add(tilesTraversed);
				}
				else 
					resolveCityOrRoadOrFarmer(1, 0, Status.THIEF);
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
				if(tile.getSideValue(Tile.EAST) == TileSideValue.CITY) {
					resolveCityOrRoadOrFarmer(2, 2, Status.KNIGHT);
					cities.add(tilesTraversed);
				}
				else 
					resolveCityOrRoadOrFarmer(1, 0, Status.THIEF);
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
				if(tile.getSideValue(Tile.SOUTH) == TileSideValue.CITY) {
					resolveCityOrRoadOrFarmer(2, 2, Status.KNIGHT);
					cities.add(tilesTraversed);
				}
				else 
					resolveCityOrRoadOrFarmer(1, 0, Status.THIEF);
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
				if(tile.getSideValue(Tile.WEST) == TileSideValue.CITY) {
					resolveCityOrRoadOrFarmer(2, 2, Status.KNIGHT);
					cities.add(tilesTraversed);
				}
				else 
					resolveCityOrRoadOrFarmer(1, 0, Status.THIEF);
			}
		}
	    if(gameFinished) {
			}
	}
	
	public void resolveMonk(Tile tile) {
		if(tile.getStatus() == Status.MONK 
			&& (surroundedByTiles(tile) || gameFinished)) {
			int point = numberOfTilesSurrounding(tile) + 1;
			Player player = tile.getPlayer();
			player.setPoints(player.getPoints() + point);
			System.out.println("(" + tile.getxOnBoard()+ ", " + tile.getyOnBoard() + ")" 
					+ "[monk] " + player.getName() + " +" + point);
			player.setPieceCount(player.getPieceCount() + 1);
			if(!gameFinished) {
				tile.setxOnTile(-1);
				tile.setxOnClick(-1);
				tile.setyOnTile(-1);
				tile.setyOnClick(-1);
				tile.setStatus(null);		
				tile.setPlayer(null);
			}
			else {
				tilesWherePieceFoundForEndGame.add(tile);
			}
			model.firePlayers();
			model.fireBoard();
		}
	}
	
	private int numberOfTilesSurrounding(Tile tile) {
		int x = tile.getxOnBoard();
		int y = tile.getyOnBoard();
		int res = 0;
		if(board.getBoard()[x + 1][y] != null) res++;
		if(board.getBoard()[x - 1][y] != null) res++;
		if(board.getBoard()[x][y + 1] != null) res++;
		if(board.getBoard()[x][y - 1] != null) res++;
		if(board.getBoard()[x + 1][y + 1] != null) res++;
		if(board.getBoard()[x + 1][y - 1] != null) res++;
		if(board.getBoard()[x - 1][y + 1] != null) res++;
		if(board.getBoard()[x - 1][y - 1] != null) res++;
		return res;
	}

	public void resolveCityOrRoadOrFarmer(int bonusForTile, int bonusPennant, Status status) {
		int bonusForSuplyingCities = 3;
		String statusString;
		if(status == Status.THIEF)
			statusString = "[thief]";
		else if(status == Status.KNIGHT){
			statusString = "[knight]";
		}
		else {
			statusString = "[farmer]";
		}
		int points = 0;
		Map<Player, Integer> players = new HashMap<Player, Integer>();
		int maxCount = 0;
		for(Tile t : tilesWherePieceFound){
			int count = players.containsKey(t.getPlayer()) ? players.get(t.getPlayer()) : 0;
			players.put(t.getPlayer(), count + 1);
			if(count + 1 > maxCount)
				maxCount = count + 1;
		}
		for(Tile t : tilesWherePieceFound){
			if(!gameFinished) {
				t.getPlayer().setPieceCount(t.getPlayer().getPieceCount() + 1);
				t.setxOnTile(-1);
				t.setxOnClick(-1);
				t.setyOnTile(-1);
				t.setyOnClick(-1);
				t.setStatus(null);
				t.setPlayer(null);
			}
		}
		for(Tile t : tilesTraversed) {
			if(t.getType().hasPennant())
				points += bonusPennant;
		}
		if(status != Status.FARMER)
			points += tilesTraversed.size() * bonusForTile;
		else {
			points += supplyingCitiesByFarmNumber() * bonusForSuplyingCities;
		}
		for(Player player : players.keySet()){
			if(players.get(player) == maxCount) {
				player.setPoints(player.getPoints() + points);
//				Tile tile = ((Tile []) tilesTraversed.toArray())[0];
			System.out.println(//"(" + tile.getxOnBoard()+ ", " + tile.getyOnBoard() + ")" +
				statusString + " " + player.getName() + " +" + points);
			}
		}
		model.firePlayers();
		model.fireBoard();
	}
	
	private int supplyingCitiesByFarmNumber() {
		int res = 0;
		Set<Set<Tile>> citiesRemoved = new HashSet<Set<Tile>>();
		for(Tile t : tilesTraversed){
			for(Set<Tile> city : cities) {
				if(city.contains(t)){
					res++;
					citiesRemoved.add(city);
					cities.remove(city);
				}
			}
		}
		for(Set<Tile> city : citiesRemoved) {
			cities.add(city);
		}
		return res;
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
	public boolean isGameFinished() {
		return gameFinished;
	}
}