package WWF;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class DebugServer {
	private List<Player> queuedPlayers;
	private List<Game<Player>> games;
	
	public static void main( String[] argv ) throws IOException {
		DebugServer server = new DebugServer();
	}
	
	private int currentPlayers;
	
	public DebugServer() throws IOException {
		queuedPlayers = new LinkedList<Player>();
		games = new LinkedList<Game<Player>>();
		currentPlayers = 0;
		
		ServerSocket listener = new ServerSocket();
		listener.bind( new InetSocketAddress( InetAddress.getLocalHost(), 5555 ));
		
		while (true) {
			System.out.println("Starting to listen for clients");
			Socket newCon = listener.accept();
			
			Player p = new Player(newCon);
			
			System.out.println("A player connected");
			
			/*if ( currentPlayers >= 1 ) {
				Player p2 = queuedPlayers.get(0);
				queuedPlayers.remove(0);
				currentPlayers--;
				Game<Player> g = new Game<Player>( p, p2 );
				g.start();
				games.add(g);
				
				System.out.println("Fetching a player from the queue and putting them into a game.");
			} else {
				System.out.println("Putting the player into the player queue");
				queuedPlayers.add(p);
				currentPlayers++;
			}
			*/
			
			// Immediately start the game.
			p.startGame();
		}
	}
}
