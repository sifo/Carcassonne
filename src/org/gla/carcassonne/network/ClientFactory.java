package org.gla.carcassonne.network;

import java.util.Set;

public interface ClientFactory {
	// Transformer le numéro du joueur vers une implémentation spécifique
	Object getPlayers(Set<Integer> p);
	
	// Transformer les objets associés aux coordonnées de grille en entiers
	int setX(Object o);
	int setY(Object o);
	String setOrientation(Object o);
	
	String setTile(Object o);
	String setPiece(Object o);
	
	// Vérification du mouvement d'une pièce par le moteur
	boolean checkMove(int player, String tile, int x, int y, String o, String piece);
}
