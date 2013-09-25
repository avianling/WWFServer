package WWF;

public class Game< E extends Player > {
	private E player1;
	private E player2;
	
	public Game( E player1, E player2 ) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void start() {
		player1.startGame();
		player2.startGame();
	}
	
}
