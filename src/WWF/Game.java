package WWF;

import java.io.IOException;

public class Game< E extends Player > {
	private E player1;
	private E player2;
	
	/**
	 * Store the time when the game was started
	 * This can be used to determine if the game should be closed.
	 * Games should be closed after 2 minutes?
	 */
	private long gameStartTime;
	
	private PlayerRedirector x, y;
	
	public Game( E player1, E player2 ) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void start() {
		System.out.println("Starting the game");
		
		gameStartTime = System.currentTimeMillis();
		
		try {
			player1.startGame();
		} catch ( IOException e ) {
			// Unable to connect to player 1.
			player1.closeConnection();
			
			endGame();
			return;
		}
		
		try {
			player2.startGame();
		} catch ( IOException e ) {
			player1.closeConnection();
			player2.closeConnection();
			
			endGame();
			return;
		}
		
		x = new PlayerRedirector( player1, player2, this );
		y = new PlayerRedirector( player2, player1, this );
	}
	
	private void endGame() {
		// Remove this game from the servers records in some way.
	}

	// Called when the game is ended by disconnection
	public void disconnected() {
		// Close both parties?
		System.out.println("A disconnection occured");
		player1.closeConnection();
		player2.closeConnection();
		endGame();
	}
	
}
