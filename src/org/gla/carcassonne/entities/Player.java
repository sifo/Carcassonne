package org.gla.carcassonne.entities;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
	
	private String name;
	private int points;
	private ArrayList<Tile> tilesList;
	private int pieceCount;
	private String colorName;
	private Color color;

	public Player(String name, String colorName, Color color) {
		this.name = name;
		points = 0;
		pieceCount = 7;
		this.colorName = colorName;
		this.color = color;
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

	public void setColorName(String color) {
		this.colorName = color;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

}