package org.gla.carcassonne.network;

import java.io.IOException;

public class SingleServerTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		new CarcassonneServer().start();
	}
}
