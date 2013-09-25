import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import WWF.Game;
import WWF.Player;


public class Server {
	
	private List<Player> queuedPlayers;
	private List<Game> games;
	
	public static void main( String[] argv ) throws IOException {
		Server server = new Server();
	}
	
	private int currentPlayers;
	
	public Server() throws IOException {
		queuedPlayers = new LinkedList<Player>();
		games = new LinkedList<Game>();
		currentPlayers = 0;
		
		ServerSocket listener = new ServerSocket();
		listener.bind( new InetSocketAddress( InetAddress.getLocalHost(), 5555 ));
		
		while (true) {
			System.out.println("Starting to listen for clients");
			Socket newCon = listener.accept();
			
			Player p = new Player(newCon);
			
			System.out.println("A player connected");
			
			if ( currentPlayers > 1 ) {
				Player p2 = queuedPlayers.get(0);
				queuedPlayers.remove(0);
				currentPlayers--;
				Game g = new Game( p, p2 );
				g.start();
				games.add(g);
				
				System.out.println("Fetching a player from the queue and putting them into a game.");
			} else {
				System.out.println("Putting the player into the player queue");
				queuedPlayers.add(p);
				currentPlayers++;
			}
			
		}
	}
}
