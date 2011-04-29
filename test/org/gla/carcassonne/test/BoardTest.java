package org.gla.carcassonne.test;

import org.gla.carcassonne.Board;
import org.gla.carcassonne.Tile;
import org.gla.carcassonne.TileType;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
	
	private Board board;
	
	protected void setUp() {
		board = new Board();
	}
	
	public void testBoard() {
		assertEquals(5, board.getBoard().length);
		assertEquals(5, board.getBoard()[0].length);
	}
	
	public void testAdd() {
		Tile tile = new Tile(TileType.TILE_A);
		Tile randomTile = new Tile(TileType.TILE_A);
		board.add(0, 0, tile);
		//Il faut bien voir que resize agrandit en hauteur ET en largeur
		assertEquals(board.get(1, 1), tile);
		board.add(0, 0, randomTile);
		assertNotSame(board.get(0, 0), randomTile);
	}
	
	public void testResizeOnTopEdge() {
		int oldSize = board.getBoard().length;
		Tile[][] newBoard = board.getBoard();
		newBoard[0][0] = new Tile(TileType.TILE_A);
		board.setBoard(newBoard);
		board.resize(2, 0);
		assertEquals(oldSize + 1, board.getBoard().length);
		for(int i = 0; i < board.getBoard()[0].length; i++){
			assertNull(board.getBoard()[0][i]);
		}
	}
	
	public void testResizeOnBottomEdge() {
		int oldSize = board.getBoard().length;
		Tile[][] newBoard = board.getBoard();
		newBoard[0][oldSize - 1] = new Tile(TileType.TILE_A);
		board.setBoard(newBoard);
		board.resize(0, oldSize - 1);
		assertEquals(oldSize + 1, board.getBoard().length);
		
		for(int i = 0; i < board.getBoard()[0].length; i++){
			assertNull(board.getBoard()[board.getBoard().length - 1][i]);
		}
	}
	
	public void testResizeOnRightEdge() {
		int oldSize = board.getBoard()[0].length;
		Tile[][] newBoard = board.getBoard();
		newBoard[oldSize - 1][0] = new Tile(TileType.TILE_A);
		board.setBoard(newBoard);
		board.resize(oldSize - 1, 0);
		for(int i = 0; i < board.getBoard().length; i++){
			assertEquals(oldSize + 1, board.getBoard()[i].length);
		}
		
		for(int i = 0; i < board.getBoard().length; i++){
			int lastElement = board.getBoard()[i].length - 1;
			assertNull(board.getBoard()[i][lastElement]);
		}
	}
	
	public void testResizeOnLeftEdge() {
		int oldSize = board.getBoard()[0].length;
		Tile[][] newBoard = board.getBoard();
		newBoard[0][0] = new Tile(TileType.TILE_A);
		board.setBoard(newBoard);
		board.resize(0, 0);
		for(int i = 0; i < board.getBoard().length; i++){
			assertEquals(oldSize + 1, board.getBoard()[i].length);
		}
		
		for(int i = 0; i < board.getBoard().length; i++){
			assertNull(board.getBoard()[i][0]);
		}
	}
}
