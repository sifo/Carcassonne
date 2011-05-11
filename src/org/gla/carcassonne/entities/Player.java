package org.gla.carcassonne.entities;

import java.util.ArrayList;

public class Player {
	
	private String name;
	private int points;
	private ArrayList<Tile> tilesList;
	private int pieceCount;

	public Player(String name) {
		this.name = name;
		points = 0;
		pieceCount = 7;
		setTilesList(new ArrayList<Tile>());
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public void setTilesList(ArrayList<Tile> tilesList) {
		this.tilesList = tilesList;
	}

	public ArrayList<Tile> getTilesList() {
		return tilesList;
	}

	public void setPieceCount(int pieceCount) {
		this.pieceCount = pieceCount;
	}

	public int getPieceCount() {
		return pieceCount;
	}

}