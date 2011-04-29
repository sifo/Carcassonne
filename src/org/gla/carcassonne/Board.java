package org.gla.carcassonne;

public class Board {
	private static final int DEFAULT_SIZE = 5;
	private Tile[][] board;

	public Board() {
		board = new Tile[DEFAULT_SIZE][DEFAULT_SIZE];
	}

	public Tile[][] getBoard() {
		return board;
	}

	public void add(int x, int y, Tile tile) {
		if (board[x][y] == null)
			board[x][y] = tile;
		resize(x, y);
	}

	public void resize(int x, int y) {
		if (x == 0) {
			int width = board[0].length + 1;
			for(int i = 0; i < board.length; i++)
					board[i] = (Tile[]) resizeArray(board[i], 1, width);
		}
		if (x == board[0].length - 1) {
			int width = board[0].length + 1;
			for(int i = 0; i < board.length; i++)
				board[i] = (Tile[]) resizeArray(board[i], 0, width);
		}
		if (y == 0) {
			int width = board[0].length;
			board = (Tile[][]) resizeArray(board, 1, board.length + 1);
			for(int i = 0; i < board.length; i++)
				if(board[i] == null)
					board[i] = new Tile[width];
				else
					board[i] = (Tile[]) board[i];
		}
		if (y == board.length - 1) {
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

}
