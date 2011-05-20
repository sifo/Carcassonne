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
import org.gla.carcassonne.entities.Zone;
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
	private List<Set<Tile>> cities;
	private boolean gameFinished;
	public static final int BUTTON_WIDTH = 72;

//	private static final int[] tilesCount = new int[] {
//			2, // Tuile A
//			4, // Tuile B
//			1, // Tuile C
//			4, // Tuile D
//			5, // Tuile E
//			2, // Tuile F
//			1, // Tuile G
//			3, // Tuile H
//			2, // Tuile I
//			3, // Tuile J
//			3, // Tuile K
//			3, // Tuile L
//			2, // Tuile M
//			3, // Tuile N
//			2, // Tuile O
//			3, // Tuile P
//			1, // Tuile Q
//			3, // Tuile R
//			2, // Tuile S
//			1, // Tuile T
//			8, // Tuile U
//			9, // Tuile V
//			4, // Tuile W
//			1 // Tuile X
//	};
	
	private static final int[] tilesCount = new int[] {
			0, // Tuile A
			0, // Tuile B
			0, // Tuile C
			0, // Tuile D
			10, // Tuile E
			0, // Tuile F
			0, // Tuile G
			0, // Tuile H
			0, // Tuile I
			0, // Tuile J
			0, // Tuile K
			0, // Tuile L
			0, // Tuile M
			0, // Tuile N
			0, // Tuile O
			0, // Tuile P
			0, // Tuile Q
			0, // Tuile R
			0, // Tuile S
			0, // Tuile T
			0, // Tuile U
			0, // Tuile V
			0, // Tuile W 
			0 // Tuile X
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
		cities = new ArrayList<Set<Tile>>();
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
				currentPlayerhasPlacedTile = false;
				model.fireLockConfirmButton();
				model.fireUnlockRotateButtons();
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
		if(!currentPlayerhasPlacedTile) {
			currentTile.rotateLeft();
			model.fireRotateLeft();
		}
	}

	public void rotateRight() {
		if(!currentPlayerhasPlacedTile) {
			currentTile.rotateRight();
			model.fireRotateRight();
		}
	}

	public void placePieceOnTile(int xOnBoard, int yOnBoard, MouseEvent arg0) {
		if (currentTile.getxOnBoard() != xOnBoard
				|| currentTile.getyOnBoard() != yOnBoard) {
			return;
		} else {
			currentTile.setxOnTile(-1);
			currentTile.setyOnTile(-1);
			currentTile.setxOnClick(-1);
			currentTile.setyOnClick(-1);
			currentTile.setStatus(null);
			model.firePlacePieceOnTile();
			model.getPlayerManager().setCurrentPlayerhasPlacedPiece(false);
			int yOnTile = arg0.getX() * Tile.CELL_WIDTH / BUTTON_WIDTH;
			int xOnTile = arg0.getY() * Tile.CELL_WIDTH / BUTTON_WIDTH;
			Player player = model.getPlayerManager().getCurrentPlayer();
			if (currentTile.getZoneValue(xOnTile, yOnTile) == Status.NONE
					|| player.getPieceCount() == 0
					|| !isPossibleToPlacePiece(xOnTile, yOnTile)) {
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

	private boolean isPossibleToPlacePiece(int xOnTile, int yOnTile) {
		int xOnZoneMap = yOnTile + (currentTile.getxOnBoard()) * 7;
		int yOnZoneMap = (currentTile.getyOnBoard()) * 7 + (6 - xOnTile);
		Zone [][] zoneMap = boardToZoneTable();
		return !findPiece(xOnZoneMap, yOnZoneMap, zoneMap);
	}
	
	private Zone[][] boardToZoneTable() {
		Zone[][] res = new Zone[board.getBoard().length * 7][board.getBoard()[0].length * 7];
		for(int i = 0; i < board.getBoard().length; i++)
			for(int j = 0; j < board.getBoard()[0].length; j++){
				if(board.getBoard()[i][j] != null) {
					for(int x = 0; x < 7; x++)
						for(int y = 0; y < 7;y++){
							if(board.getBoard()[i][j] != null){
								int newX = i*7 + y;
								int newY = j*7 + (6 - x);
								res[newX][newY] = new Zone(board.getBoard()[i][j].getStatus(x, y));
								Tile tile = board.getBoard()[i][j];
								if(tile.getxOnTile() == x
										&& tile.getyOnTile() == y){
									res[newX][newY].setHasPiece(true);
								}
							}
						}
				}
			}
		return res;
	}

	private boolean findPiece(int xOnStatusMap, int yOnStatusMap, Zone[][] zoneMap) {
		boolean res = false;
		Zone currentZone = zoneMap[xOnStatusMap][yOnStatusMap];
		Status currentStatus = currentZone.getStatus();
		currentZone.setStatus(null);
		
		if(currentZone.hasPiece()){
			res = true;
		}
		
		int [] xToGoThrough = {xOnStatusMap + 1, xOnStatusMap - 1, xOnStatusMap, xOnStatusMap};
		int [] yToGoThrough = {yOnStatusMap, yOnStatusMap, yOnStatusMap + 1, yOnStatusMap - 1};
		
		for(int i = 0; i < xToGoThrough.length; i++) {
			Zone nextZone = zoneMap[xToGoThrough[i]][yToGoThrough[i]];
			if(nextZone != null && currentStatus == nextZone.getStatus()) {
				res = res || findPiece(xToGoThrough[i], yToGoThrough[i], zoneMap);
			}
		}
		return res;
	}
	
	private boolean findExit(int xOnStatusMap, int yOnStatusMap, Zone[][] zoneMap) {
		boolean res = false;
		Zone currentZone = zoneMap[xOnStatusMap][yOnStatusMap];
		Status currentStatus = currentZone.getStatus();
		currentZone.setStatus(null);
//		 System.out.println("findExit(" + xOnTile + ", " +
//		 yOnTile + ", " + tile.getType().getPath() +")");
		int currentXOnBoard = xOnStatusMap / 7;
		int currentYOnBoard = yOnStatusMap / 7;
//		System.out.println("tile traversed("+ currentXOnBoard + ", " + currentYOnBoard + ")");
		tilesTraversed.add(board.getBoard()[currentXOnBoard][currentYOnBoard]);

		if (currentZone.hasPiece()) {
//			 System.out.println("Pion Trouvé sur zone(" + xOnStatusMap + ", " + yOnStatusMap + ")!");
			 tilesWherePieceFound.add(board.getBoard()[currentXOnBoard][currentYOnBoard]);
		}
		
		int [] xToGoThrough = {xOnStatusMap + 1, xOnStatusMap - 1, xOnStatusMap, xOnStatusMap};
		int [] yToGoThrough = {yOnStatusMap, yOnStatusMap, yOnStatusMap + 1, yOnStatusMap - 1};
		
		for(int i = 0; i < xToGoThrough.length; i++) {
			Zone nextZone = zoneMap[xToGoThrough[i]][yToGoThrough[i]];
			if(nextZone != null && currentStatus == nextZone.getStatus()) {
				res = findExit(xToGoThrough[i], yToGoThrough[i], zoneMap) || res;
			} else if(nextZone == null) {
				res = true;
			}
		}
		return res;
	}
	
	private void findTileWithCity(int xOnStatusMap, int yOnStatusMap, Zone[][] zoneMap) {
		Zone currentZone = zoneMap[xOnStatusMap][yOnStatusMap];
		Status currentStatus = currentZone.getStatus();
		currentZone.setStatus(null);
		int currentXOnBoard = xOnStatusMap / 7;
		int currentYOnBoard = yOnStatusMap / 7;
		
		if (currentZone.hasPiece()) {
//			 System.out.println("Pion Trouvé sur zone(" + xOnStatusMap + ", " + yOnStatusMap + ")!");
			 tilesWherePieceFound.add(board.getBoard()[currentXOnBoard][currentYOnBoard]);
		}
		
		int [] xToGoThrough = {xOnStatusMap + 1, xOnStatusMap - 1, xOnStatusMap, xOnStatusMap};
		int [] yToGoThrough = {yOnStatusMap, yOnStatusMap, yOnStatusMap + 1, yOnStatusMap - 1};

		
		for(int i = 0; i < xToGoThrough.length; i++) {
			Zone nextZone = zoneMap[xToGoThrough[i]][yToGoThrough[i]];
			if(nextZone != null) {
				if(currentStatus == nextZone.getStatus()) {
					findTileWithCity(xToGoThrough[i], yToGoThrough[i], zoneMap);
				} else if(nextZone.getStatus() == Status.KNIGHT) {

	//				System.out.println("tile traversed("+ currentXOnBoard + ", " + currentYOnBoard + ")");
					tilesTraversed.add(board.getBoard()[currentXOnBoard][currentYOnBoard]);
				}
			}
		}
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
		Zone [][] zoneMap = boardToZoneTable();
		for(int i = 0; i < boardFinal.length; i++){
			for(int j = 0; j < boardFinal[0].length; j++){
				Tile tile = boardFinal[i][j];
				if(tile != null
					&& tile.getStatus() != null
					&& !tilesWherePieceFoundForEndGame.contains(tile)){
					if(tile.getStatus() == Status.MONK){
						resolveMonk(tile);
					} else {
						int xOnTile = tile.getxOnTile();
						int yOnTile = tile.getyOnTile();
						int xOnZoneMap = yOnTile + tile.getxOnBoard() * 7;
						int yOnZoneMap = tile.getyOnBoard() * 7 + (6 - xOnTile);
						tilesWherePieceFound.clear();
						tilesTraversed.clear();
						zoneMap =  boardToZoneTable();
						if(tile.getStatus() == Status.KNIGHT) {
							findExit(xOnZoneMap, yOnZoneMap, zoneMap);
							resolveCityOrRoadOrFarmer(1, 1, Status.KNIGHT);
						}
						else if(tile.getStatus() == Status.THIEF) {
							findExit(xOnZoneMap, yOnZoneMap, zoneMap);
							resolveCityOrRoadOrFarmer(1, 0, Status.THIEF);
						}
						else if(tile.getStatus() == Status.FARMER) {
							findTileWithCity(xOnZoneMap, yOnZoneMap, zoneMap);
							resolveCityOrRoadOrFarmer(0, 0, Status.FARMER);
						}
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
		int xOnTile = -1;
		int yOnTile = -1;
		int xOnZoneMap = -1;
		int yOnZoneMap = -1;
		Zone [][] zoneMap = boardToZoneTable();
		
		if(tile.getSideValue(Tile.NORTH) == TileSideValue.CITY
			|| tile.getSideValue(Tile.NORTH) == TileSideValue.ROAD){
			xOnTile = 0;
			yOnTile = 3;
			xOnZoneMap = yOnTile + (tile.getxOnBoard()) * 7;
			yOnZoneMap = (tile.getyOnBoard()) * 7 + (6 - xOnTile);
				tilesWherePieceFound = new HashSet<Tile>();
				tilesTraversed = new HashSet<Tile>();
			if(!findExit(xOnZoneMap, yOnZoneMap, zoneMap)){
				if(tile.getSideValue(Tile.NORTH) == TileSideValue.CITY) {
					resolveCityOrRoadOrFarmer(2, 2, Status.KNIGHT);
					if(tilesWherePieceFound.size() > 0)
						cities.add(tilesTraversed);
				}
				else 
					resolveCityOrRoadOrFarmer(1, 0, Status.THIEF);
			} 
		}
		zoneMap = boardToZoneTable();
		if(tile.getSideValue(Tile.EAST) == TileSideValue.CITY
			|| tile.getSideValue(Tile.EAST) == TileSideValue.ROAD){
			xOnTile = 3;
			yOnTile = 6;
			xOnZoneMap = yOnTile + (tile.getxOnBoard()) * 7;
			yOnZoneMap = (tile.getyOnBoard()) * 7 + (6 - xOnTile);
			tilesWherePieceFound = new HashSet<Tile>();
			tilesTraversed = new HashSet<Tile>();
			if(!findExit(xOnZoneMap, yOnZoneMap, zoneMap)){
				if(tile.getSideValue(Tile.EAST) == TileSideValue.CITY) {
					resolveCityOrRoadOrFarmer(2, 2, Status.KNIGHT);
					if(tilesWherePieceFound.size() > 0)
						cities.add(tilesTraversed);
				}
				else 
					resolveCityOrRoadOrFarmer(1, 0, Status.THIEF);
			}
		}
		zoneMap = boardToZoneTable();
		if(tile.getSideValue(Tile.SOUTH) == TileSideValue.CITY
			|| tile.getSideValue(Tile.SOUTH) == TileSideValue.ROAD){
			xOnTile = 6;
			yOnTile = 3;
			xOnZoneMap = yOnTile + (tile.getxOnBoard()) * 7;
			yOnZoneMap = (tile.getyOnBoard()) * 7 + (6 - xOnTile);
			tilesWherePieceFound = new HashSet<Tile>();
			tilesTraversed = new HashSet<Tile>();
			if(!findExit(xOnZoneMap, yOnZoneMap, zoneMap)){
				if(tile.getSideValue(Tile.SOUTH) == TileSideValue.CITY) {
					resolveCityOrRoadOrFarmer(2, 2, Status.KNIGHT);
					if(tilesWherePieceFound.size() > 0)
						cities.add(tilesTraversed);
				}
				else 
					resolveCityOrRoadOrFarmer(1, 0, Status.THIEF);
			} 
		}
		zoneMap = boardToZoneTable();
		if(tile.getSideValue(Tile.WEST) == TileSideValue.CITY
			|| tile.getSideValue(Tile.WEST) == TileSideValue.ROAD) {
			xOnTile = 3;
			yOnTile = 0;
			xOnZoneMap = yOnTile + (tile.getxOnBoard()) * 7;
			yOnZoneMap = (tile.getyOnBoard()) * 7 + (6 - xOnTile);
			tilesWherePieceFound = new HashSet<Tile>();
			tilesTraversed = new HashSet<Tile>();
			if(!findExit(xOnZoneMap, yOnZoneMap, zoneMap)){
				if(tile.getSideValue(Tile.WEST) == TileSideValue.CITY) {
					resolveCityOrRoadOrFarmer(2, 2, Status.KNIGHT);
					if(tilesWherePieceFound.size() > 0)
						cities.add(tilesTraversed);
				}
				else 
					resolveCityOrRoadOrFarmer(1, 0, Status.THIEF);
			}
		}
	}
	
	public void resolveMonk(Tile tile) {
		if(tile.getStatus() == Status.MONK 
			&& (surroundedByTiles(tile) || gameFinished)) {
			int point = numberOfTilesSurrounding(tile) + 1;
			Player player = tile.getPlayer();
			player.setPoints(player.getPoints() + point);
			System.out.println("[monk] " + player.getName() + " +" + point);
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
			if(status == Status.KNIGHT && t.getType().hasPennant())
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
				if(city.contains(t) && !citiesRemoved.contains(city)){
					res++;
					citiesRemoved.add(city);
				}
			}
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
	
	public void setGameFinished(boolean gameFinished) {
		this.gameFinished = gameFinished;
	}
}