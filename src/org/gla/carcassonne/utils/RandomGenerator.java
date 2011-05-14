package org.gla.carcassonne.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.gla.carcassonne.entities.TileType;

public class RandomGenerator {

	private TileType type;
	
	/*
	 * Méthode du random :
	 * Somme des valeurs du map donné en paramètre. On choisi un nombre aléatoire parmi le nombre
	 * total de tuiles.
	 * Dans un ordre quelconque, si le nombre aléatoire est inclu dans le plus petit ensemble de l'union
	 * des tuiles, alors on retourne le type de l'ensemble.
	 */
	public RandomGenerator(Map<TileType, Integer> map) {
		int valuesSum = 0;
		
		for(Entry<TileType, Integer> entry : map.entrySet()) {
			valuesSum += entry.getValue();
		}
		
		Random generator = new Random();
		int value = generator.nextInt(valuesSum);
		
		for(Entry<TileType, Integer> entry : map.entrySet()) {
			value -= entry.getValue();
			
			if (value <= 0) {
				type = entry.getKey();
				return;
			}
		}
		
		assert(value > 0);
	}
	
	public TileType getRandomTileType() {
		return type;
	}
}