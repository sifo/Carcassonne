package org.gla.carcassonne.network;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.gla.carcassonne.utils.*;

public class TCPClient extends Thread{
	
	int num_client;
	Socket s;
	Message current_mess;
	boolean locked;
	PushbackInputStream read;
	BufferedOutputStream out;

	public TCPClient(String serveur, int port) throws IOException {
		super();
		
		try {
			this.s = new Socket(serveur, port);
			
			read = new PushbackInputStream(this.s.getInputStream());
			out = new BufferedOutputStream(this.s.getOutputStream());
			
			// Création du HELLO 0
			Message m = new Message("HELLO");
			m.getNthValue(1).setIntValue(0);
			
			// envoi du message
			m.format(out);
			out.flush();
			
			// reception de la réponse
			m = Message.parse(read);
			String receive = m.getNthValue(0).toString();
			
			// HELLONACK : on propose l'attente
			if (receive.equals("HELLONACK")){
				System.out.println("Trop peu de joueurs, attendre ?");
				Scanner sc = new Scanner(System.in);
				char rep = sc.nextLine().charAt(0);
				
				// Si le joueur veut attendre, on attend un HELLOACK
				if (rep == 'o' || rep == 'O'){
					
					System.out.println("close pour quitter");
					
					while(!receive.equals("HELLOACK")){
						
						read = new PushbackInputStream(this.s.getInputStream());
						m = Message.parse(read);
						receive = m.getNthValue(0).toString();
						
						// S'il ne veut plus attendre, on quitte
						if (sc.hasNext()){
							String fin = sc.nextLine().toLowerCase();
							if (fin.equals("close")){
								this.deconnect();
								break;
							}
						}
					}
					
					if (receive.equals("HELLOACK"))
						num_client = m.getNthValue(1).getIntValue();
				}
				else
					this.deconnect();
			}
			
			else if (receive.equals("HELLONACK"))
				num_client = m.getNthValue(1).getIntValue();
			
			locked = true;
		}
		catch (UnknownHostException e) {e.printStackTrace();} 
		catch (ProtocolParseError e) {e.printStackTrace();} 
		catch (ProtocolError e) {e.printStackTrace();}
	}
	
	// si le client a envoy� du texte
	public void setTuile(String tuile, int positionx, int positiony, int positionpion){
		Message m = new Message(tuile);
		
		try {
			m.getNthValue(1).setIntValue(positionx);
			m.getNthValue(2).setIntValue(positiony);
			m.getNthValue(3).setIntValue(positionpion);
		} 
		catch (ProtocolError e) {
			m = new Message("NOOP");
			e.printStackTrace();
		}
		
		this.current_mess = m;
		this.locked = false;
	}
	
	public void setTuile(String tuile, int positionx, int positiony){
		Message m = new Message(tuile);
		
		try {
			m.getNthValue(1).setIntValue(positionx);
			m.getNthValue(2).setIntValue(positiony);
		} 
		catch (ProtocolError e) {
			m = new Message("NOOP");
			e.printStackTrace();
		}
		
		this.current_mess = m;
		this.locked = false;
	}
	
	public void deconnect(){
		try {
			// D�connection : envoi de CLOSE et le num�ro du client
			Message m = new Message("CLOSE");
			m.getNthValue(1).setIntValue(num_client);
			m.format(out);
			out.flush();
		}
		catch (IOException e) {e.printStackTrace();}
		catch (ProtocolError e) {e.printStackTrace();}
		System.exit(0);
	}
	
	public void start(){
		try {
			Message m = new Message("START");
			m.getNthValue(1).setIntValue(num_client);
			m.format(out);
			out.flush();
			
			while(true){
				
				read = new PushbackInputStream(this.s.getInputStream());
				m = Message.parse(read);
				String receive = m.getNthValue(0).toString();
				
				// le serveur donne la main au client
				if (receive.equals("GIVEHAND")){
					
					// on attend que le client ait envoy� le texte
					while(this.locked){}
					
					// on envoie le texte et on bloque
					current_mess.format(out);
					out.flush();
					locked = true;
				}
				else
					System.out.println(receive);
			}
		}
		catch (IOException e) {e.printStackTrace();}
		catch (ProtocolParseError e) {e.printStackTrace();}
		catch (ProtocolError e) {e.printStackTrace();}
	}
}