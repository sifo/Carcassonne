package org.gla.carcassonne.entities;

import org.gla.carcassonne.CarcassonneModel;

public class Board {
	private static final int DEFAULT_SIZE = 5;
	private Tile[][] board;
	private int tileCount;
	private CarcassonneModel model;

	public Board(CarcassonneModel model) {
		this.model = model;
		board = new Tile[DEFAULT_SIZE][DEFAULT_SIZE];
		tileCount = 0;
	}

	public Tile[][] getBoard() {
		return board;
	}

	public int getTileCount() {
		return tileCount;
	}

	public void add(int x, int y, Tile tile) {
		if (!canPlace(x, y, tile) 
				|| model.getTileManager().getCurrentPlayerHasPlacedTile()) {
			model.fireCantAddTile();
		} else {
			board[x][y] = tile;
			tile.setxOnBoard(x);
			tile.setyOnBoard(y);
			resize(x, y);
			tileCount++;
			if(tileCount > 1) {
				model.getTileManager().setCurrentPlayerhasPlacedTile(true);
				tile.setPlayer(model.getPlayerManager().getCurrentPlayer());
				model.fireUnlockConfirmButton();
				model.fireCardBack();
			}
			model.fireAddTile();
		}
	}

	public void resize(int x, int y) {
		if (y == 0) {
			int width = board[0].length + 1;
			for (int i = 0; i < board.length; i++) {
				board[i] = (Tile[]) resizeArray(board[i], 1, width);
				// Avec ce for, on incrémente les oordonnées des tuiles
				for (int j = 0; j < board[i].length; j++) {
					Tile tile = board[i][j];
					if (tile != null) {
						tile.setyOnBoard(tile.getyOnBoard() + 1);
					}
				}
			}
			model.fireBoard();
		}
		if (y == board[0].length - 1) {
			int width = board[0].length + 1;
			for (int i = 0; i < board.length; i++)
				board[i] = (Tile[]) resizeArray(board[i], 0, width);
			model.fireBoard();
		}
		if (x == 0) {
			int width = board[0].length;
			board = (Tile[][]) resizeArray(board, 1, board.length + 1);
			for (int i = 0; i < board.length; i++) {
				if (board[i] == null)
					board[i] = new Tile[width];
				else
					board[i] = (Tile[]) board[i];
				// Avec ce for, on incrémente les abscisses des tuiles
				for (int j = 0; j < board[i].length; j++) {
					Tile tile = board[i][j];
					if (tile != null) {
						tile.setxOnBoard(tile.getxOnBoard() + 1);
					}
				}
			}
			model.fireBoard();
		}
		if (x == board.length - 1) {
			int width = board[0].length;
			board = (Tile[][]) resizeArray(board, 0, board.length + 1);
			for (int i = 0; i < board.length; i++)
				if (board[i] == null)
					board[i] = new Tile[width];
				else
					board[i] = (Tile[]) board[i];
			model.fireBoard();
		}
	}

	public boolean willNeedResize(int x, int y) {
		if (y == 0 || y == board[0].length - 1 || x == 0
				|| x == board.length - 1)
			return true;
		return false;
	}

	public Object resizeArray(Object oldArray, int destPos, int newSize) {
		int oldSize = java.lang.reflect.Array.getLength(oldArray);
		Class elementType = oldArray.getClass().getComponentType();
		Object newArray = java.lang.reflect.Array.newInstance(elementType,
				newSize);
		int preserveLength = Math.min(oldSize, newSize);
		if (preserveLength > 0)
			System.arraycopy(oldArray, 0, newArray, destPos, preserveLength);
		return newArray;
	}

	public Tile get(int x, int y) {
		return board[x][y];
	}

	public void setBoard(Tile[][] board) {
		this.board = board;
	}

	public boolean contains(Tile tile) {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				if (board[i][j] == tile)
					return true;
		return false;
	}
	
	public boolean canPlace(int x, int y, Tile tile) {
		if (tileCount == 0) {
			return true;
		}
		if (board[x][y] != null) {
			return false;
		}
		
		int numberOfLink = 0;
		int numberOfBreakLink = 0;

		if (y > 0 && board[x][y - 1] != null){
			if(tile.getSideValue(Tile.SOUTH) == board[x][y - 1]
								.getSideValue(Tile.NORTH))
					numberOfLink++;
			else numberOfBreakLink++;
		}
		if (y < board[x].length - 1 && board[x][y + 1] != null){
			if( tile.getSideValue(Tile.NORTH) == board[x][y + 1]
						.getSideValue(Tile.SOUTH))
				numberOfLink++;
			else numberOfBreakLink++;
		}
		if (x > 0 && board[x - 1][y] != null){
				if(tile.getSideValue(Tile.WEST) == board[x - 1][y]
						.getSideValue(Tile.EAST)) 
					numberOfLink++;
				else numberOfBreakLink++;
		}
		if (x < board.length - 1 && board[x + 1][y] != null){
			if(tile.getSideValue(Tile.EAST) == board[x + 1][y]
						.getSideValue(Tile.WEST)) 
				numberOfLink++;
			else numberOfBreakLink++;
		}
		if(numberOfLink > 0 && numberOfBreakLink == 0)
			return true;
		return false;
	}

	public boolean canPlaceSomeWhere(Tile tile) {
		int initialRotationCount = 0;
		int maxRotationCount = 3;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				for(int k = initialRotationCount; k <= maxRotationCount; k++){
					tile.setRotationCount(k);
					if(canPlace(i, j, tile)){
						tile.setRotationCount(initialRotationCount);
						return true;
					}
				}
			}
		}
		return false;
	}
}