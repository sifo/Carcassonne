package org.gla.carcassonne.network;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Vector;

import org.gla.carcassonne.utils.*;

public class TCPClient extends Thread{
	
	int num_client, token;
	Socket s;
	Message current_mess;
	boolean locked;
	PushbackInputStream read;
	BufferedOutputStream out;
	Vector <Integer> clients_adverses = new Vector<Integer>();

	public TCPClient(String serveur, int port) throws IOException {
		super();
		
		try {
			this.s = new Socket(serveur, port);
			
			read = new PushbackInputStream(this.s.getInputStream());
			out = new BufferedOutputStream(this.s.getOutputStream());
			
			// Cr�ation du HELLO 0
			Message m = new Message("HELLO", new MessageInt(0));
			
			// envoi du message
			m.format(out);
			out.flush();
			
			// reception de la r�ponse
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
					
					while(!receive.equals("HELLO")){
						
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
					
					if (receive.equals("HELLO")){
						// Cr�ation du HELLOACK n
						m = new Message("HELLOACK", new MessageInt(num_client));
						
						// envoi du message
						m.format(out);
						out.flush();
						
						// r�ception des joueurs adverses
						m = Message.parse(read);
						receive = m.getNthValue(0).toString();
						while(receive.equals("HELLOACK")){
							clients_adverses.add(m.getNthValue(1).getIntValue());
							m = Message.parse(read);
							receive = m.getNthValue(0).toString();
						}
					}
				}
				else
					this.deconnect();
			}
			
			else if (receive.equals("HELLO")){
				num_client = m.getNthValue(1).getIntValue();
				
				// Cr�ation du HELLOACK n
				m = new Message("HELLOACK", new MessageInt(num_client));
				
				// envoi du message
				m.format(out);
				out.flush();
				
				// r�ception des joueurs adverses
				m = Message.parse(read);
				receive = m.getNthValue(0).toString();
				while(receive.equals("HELLOACK")){
					clients_adverses.add(m.getNthValue(1).getIntValue());
					m = Message.parse(read);
					receive = m.getNthValue(0).toString();
				}
			}
			locked = true;
		}
		catch (UnknownHostException e) {e.printStackTrace();} 
		catch (ProtocolParseError e) {e.printStackTrace();} 
		catch (ProtocolError e) {e.printStackTrace();}
	}
	
	// si le client a envoy� du texte
	public void setTuile(String tuile, int positionx, int positiony, int positionpion){
		Message m = new Message(
				"MOVE", new MessageInt(num_client), new MessageString(tuile), 
				new MessageInt(positionx), new MessageInt(positiony),
				new MessageInt(positionpion), new MessageInt(token));
		
		this.current_mess = m;
		this.locked = false;
	}
	
	public void setTuile(String tuile, int positionx, int positiony){
		Message m = new Message(
				"MOVE", new MessageInt(num_client), new MessageString(tuile), 
				new MessageInt(positionx), new MessageInt(positiony),
				new MessageInt(-1), new MessageInt(token));
		
		this.current_mess = m;
		this.locked = false;
	}
	
	public void acceptTuile(){
		Message m = new Message("MOVEACK");
		try {
			m.format(out);
			out.flush();
		}
		catch (IOException e) {e.printStackTrace();}
	}
	
	public void refuseTuile(){
		Message m = new Message("MOVENACK");
		try {
			m.format(out);
			out.flush();
		}
		catch (IOException e) {e.printStackTrace();}
	}
	
	public void finish(){
		Message m = new Message("FINISH");
		try {
			m.getNthValue(1).setIntValue(num_client);
			m.format(out);
			out.flush();
		}
		catch (IOException e) {e.printStackTrace();}
		catch (ProtocolError e) {e.printStackTrace();}
	}
	
	public void deconnect(){
		try {
			// D�connection : envoi de CLOSE et le num�ro du client
			Message m = new Message("CLOSE", new MessageInt(num_client));
			m.format(out);
			out.flush();
		}
		catch (IOException e) {e.printStackTrace();}
		System.exit(0);
	}
	
	public void start(){
		try {
			Message m = new Message("START", new MessageInt(num_client));
			m.format(out);
			out.flush();
			
			while(true){
				
				read = new PushbackInputStream(this.s.getInputStream());
				m = Message.parse(read);
				String receive = m.getNthValue(0).toString();
				
				// le serveur donne la main au client
				if (receive.equals("TOKEN")){
					
					token = m.getNthValue(1).getIntValue();
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