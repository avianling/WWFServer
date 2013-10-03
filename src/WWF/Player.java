package WWF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;

public class Player {
	
	private Socket playerConnection;
	
	private BufferedReader input;
	private BufferedWriter output;
	private Random rand;
	
	public Player( Socket playerConnection ) throws IOException {
		this.playerConnection = playerConnection;
		
		input = new BufferedReader( new InputStreamReader( playerConnection.getInputStream() ) );
		output = new BufferedWriter( new OutputStreamWriter( playerConnection.getOutputStream() ) );
		
		rand = new Random();
	}
	
	public void closeConnection() {
		try {
			output.flush();
			playerConnection.close();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public void startGame() throws IOException {
		sendWord("Game Found");
		// We also need to send out the game information.
		// send out the seed?
		sendWord(rand.nextInt() + "");
	}
	
	public void sendWord( String word ) throws IOException {
		Message m = new Message( word );
		m.send(output);
	}
	
	public String getWordBlocking() throws IOException {
			return input.readLine();
	}
}
