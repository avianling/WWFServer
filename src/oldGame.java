import java.net.Socket;


public class oldGame {
	private Socket player1;
	private Socket player2;
	
	private TestPlayerRedirector redirect;
	
	public oldGame() {
		System.out.println("A game created");
	}
	
	public boolean addPlayer( Socket newPlayer ) {
		System.out.println("Player added");
		
		if ( player1 == null ) {
			player1 = newPlayer;
			return false;
		}
		
		if ( player1 != null && player2 == null ) {
			player2 = newPlayer;
			return true;
		}
		
		return false;
	}
	
	public void startGame() {
		System.out.println("Game Started");
		//PlayerRedirector p1 = new PlayerRedirector( player1, player2);
		//PlayerRedirector p2 = new PlayerRedirector( player2, player1);
		
		redirect = new TestPlayerRedirector(player1);
		redirect.run();
	}
}
