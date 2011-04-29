package org.gla.carcassonne;

import java.util.EnumMap;
import java.util.Random;

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
