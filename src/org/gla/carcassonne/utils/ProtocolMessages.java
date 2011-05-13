package org.gla.carcassonne.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Défini la liste des types de messages du protocole dédié au module multijoueur.
 * Utile pour tester aisément si un type de message envoyé existe dans le protocole
 */
public class ProtocolMessages {

	private static final Set<String> TYPES_SET;
	static {
		Set<String> set = new HashSet<String>();
		set.add("HELLO");
		set.add("HELLOACK");
		set.add("HELLONACK");
		set.add("CLOSE");
		set.add("READY");
		set.add("START");
		set.add("FINISH");
		set.add("MOVE");
		set.add("MOVEACK");
		set.add("MOVENACK");
		set.add("TOKEN");
		set.add("NOOP");
		TYPES_SET = Collections.unmodifiableSet(set);
	}
	
	public static Set<String> getTypesSet() {
		return TYPES_SET;
	}
}
