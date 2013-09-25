import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;


public class Server {
	
	private List<Game> queuedGames;
	private List<Game> fullGames;
	
	public static void main( String[] argv ) throws IOException {
		Server server = new Server();
	}
	
	public Server() throws IOException {
		queuedGames = new LinkedList<Game>();
		fullGames = new LinkedList<Game>();
		
		ServerSocket listener = new ServerSocket();
		listener.bind( new InetSocketAddress( InetAddress.getLocalHost(), 5555 ));
		
		while (true) {
			System.out.println("Starting to listen for clients");
			Socket newCon = listener.accept();
			
			System.out.println("A client connected");
			
			Game g = new Game();
			g.addPlayer(newCon);
			g.startGame();
			
			/*if ( queuedGames.size() == 0 ) {
				Game g = new Game();
				queuedGames.add(g);
				g.addPlayer(newCon);
			} else {
				Game g = queuedGames.get(0);
				if ( g.addPlayer(newCon) == true ) {
					queuedGames.remove(0);
					fullGames.add(g);
					g.startGame();
				}
			}*/
			
		}
	}
}
