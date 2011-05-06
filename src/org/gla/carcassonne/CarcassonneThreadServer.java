package org.gla.carcassonne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class CarcassonneThreadServer extends Thread {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private int port;
	private CarcassonneServer server;

	private final static String CONNECTION_RESET = "connection reset";

	public CarcassonneThreadServer(Socket socket, CarcassonneServer server)
			throws IOException {
		this.socket = socket;
		this.server = server;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		port = socket.getPort();
	}

	public void run() {
		String message;
		try {
			while ((message = in.readLine()) != null) {
				System.out.println("from "
						+ socket.getLocalAddress().getHostAddress() + " : "
						+ message);
			}
			in.close();
			out.close();
			socket.close();
			server.getClients().remove(this);
		} catch (SocketException e) {
			System.out.println(CONNECTION_RESET);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public PrintWriter getOut() {
		return out;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}