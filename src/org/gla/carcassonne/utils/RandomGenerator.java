package org.gla.carcassonne.utils;

import java.util.EnumMap;
import java.util.Random;

import org.gla.carcassonne.entities.TileType;

public class RandomGenerator<E extends EnumMap<?,?>> {

	private static final Random RND = new Random();
	private final TileType[] values;
	
	public RandomGenerator(Class<TileType> token) {
		values = token.getEnumConstants();
	}
	
	public TileType random() {
		return values[RND.nextInt(values.length)];
	}
}