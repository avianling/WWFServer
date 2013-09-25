package WWF;

public class Game< E extends Player > {
	private E player1;
	private E player2;
	
	private PlayerRedirector x, y;
	
	public Game( E player1, E player2 ) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void start() {
		System.out.println("Starting the game");
		
		player1.startGame();
		player2.startGame();
		
		x = new PlayerRedirector( player1, player2 );
		y = new PlayerRedirector( player2, player1 );
	}
	
}
