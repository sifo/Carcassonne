package org.gla.carcassonne;

public class Board {
	private static final int DEFAULT_SIZE = 5;
	private Tile[][] board;
	private int tileCount;

	public Board() {
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
		if (canPlace(x, y, tile))
			board[x][y] = tile;
		else return;
		tileCount++;
		resize(x, y);
	}

	public void resize(int x, int y) {
		if (y == 0) {
			int width = board[0].length + 1;
			for(int i = 0; i < board.length; i++)
					board[i] = (Tile[]) resizeArray(board[i], 1, width);
		}
		if (y == board[0].length - 1) {
			int width = board[0].length + 1;
			for(int i = 0; i < board.length; i++)
				board[i] = (Tile[]) resizeArray(board[i], 0, width);
		}
		if (x == 0) {
			int width = board[0].length;
			board = (Tile[][]) resizeArray(board, 1, board.length + 1);
			for(int i = 0; i < board.length; i++)
				if(board[i] == null)
					board[i] = new Tile[width];
				else
					board[i] = (Tile[]) board[i];
		}
		if (x == board.length - 1) {
			int width = board[0].length;
			board = (Tile[][]) resizeArray(board, 0, board.length + 1);
			for(int i = 0; i < board.length; i++)
				if(board[i] == null)
					board[i] = new Tile[width];
				else
					board[i] = (Tile[]) board[i];
		}
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
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board[i].length; j++)
			if(board[i][j] == tile)
				return true;
		return false;
	}
	
	public boolean canPlace(int x, int y, Tile tile){
		if(tileCount == 0){
			return true;
		}
		if ( board[x][y] != null) {
			return false;
		}
		if(y > 0
			&& board[x][y - 1] != null
			&& tile.getSideValue(Tile.SOUTH) 
			!= board[x][y - 1].getSideValue(Tile.NORTH)){
			return false;
		}
		if(y < board[x].length - 1
			&& board[x][y + 1] != null
			&& tile.getSideValue(Tile.NORTH) 
			!= board[x][y + 1].getSideValue(Tile.SOUTH)){
			return false;
		}
		if(x > 0
			&& board[x - 1][y] != null
			&& tile.getSideValue(Tile.WEST) 
			!= board[x - 1][y].getSideValue(Tile.EAST)){
			return false;
		}
		if(x < board.length - 1
			&& board[x + 1][y] != null
			&& tile.getSideValue(Tile.EAST) 
			!= board[x + 1][y].getSideValue(Tile.WEST)){
			return false;
		}
		return true;
	}
}
