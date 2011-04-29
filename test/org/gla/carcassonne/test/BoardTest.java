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
	
	public void testResizeOnBottomEdge() {
		int oldSize = board.getBoard()[0].length;
		Tile[][] newBoard = board.getBoard();
		newBoard[2][0] = new Tile(TileType.TILE_A);
		board.setBoard(newBoard);
		board.resize(2, 0);
		for(int i = 0; i < board.getBoard().length; i++){
			assertEquals(oldSize + 1, board.getBoard()[0].length);
		}
		
		for(int i = 0; i < board.getBoard()[0].length; i++){
			assertNull(board.getBoard()[0][i]);
		}
	}
	
	public void testResizeOnTopEdge() {
		int oldSize = board.getBoard()[0].length;
		Tile[][] newBoard = board.getBoard();
		newBoard[0][oldSize - 1] = new Tile(TileType.TILE_A);
		board.setBoard(newBoard);
		board.resize(0, oldSize - 1);
		for(int i = 0; i < board.getBoard().length; i++){
			assertEquals(oldSize + 1, board.getBoard()[0].length);
		}
		
		for(int i = 0; i < board.getBoard()[0].length; i++){
			int lastElement = board.getBoard()[i].length - 1;
			assertNull(board.getBoard()[i][lastElement]);
		}
	}
	
	public void testResizeOnRightEdge() {
		int oldSize = board.getBoard().length;
		Tile[][] newBoard = board.getBoard();
		newBoard[oldSize - 1][0] = new Tile(TileType.TILE_A);
		board.setBoard(newBoard);
		board.resize(oldSize - 1, 0);
		assertEquals(oldSize + 1, board.getBoard().length);
	
		int lastTable = board.getBoard().length - 1;
		for(int i = 0; i < board.getBoard()[lastTable].length; i++){
			assertNull(board.getBoard()[lastTable][i]);
		}
	}
	
	public void testResizeOnLeftEdge() {
		int oldSize = board.getBoard().length;
		Tile[][] newBoard = board.getBoard();
		newBoard[0][2] = new Tile(TileType.TILE_A);
		board.setBoard(newBoard);
		board.resize(0, 0);
		assertEquals(oldSize + 1, board.getBoard().length);
	
		for(int i = 0; i < board.getBoard()[0].length; i++){
			assertNull(board.getBoard()[0][i]);
		}
	}
	
	public void testContains() {
		Tile[][] newBoard = board.getBoard();
		Tile t = new Tile(TileType.TILE_A);
		newBoard[0][0] = t; 
		board.setBoard(newBoard);
		assertTrue(board.contains(t));
	}
	
	public void testCanPlace() {
		Tile firstTile = new Tile(TileType.TILE_A);
		Tile secondTile = new Tile(TileType.TILE_A);
		board.add(2,2, firstTile);
		assertTrue(board.canPlace(3, 2, secondTile));
		assertFalse(board.canPlace(2, 1, secondTile));
	}
}
