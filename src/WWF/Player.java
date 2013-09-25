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
		
	}
	
	public void startGame() {
		sendWord("Game Found");
		// We also need to send out the game information.
		// send out the seed?
		sendWord(rand.nextInt() + "");
	}
	
	public void sendWord( String word ) {
		try {
			output.write(word.toString());
			output.write("\n");
			output.flush();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public String getWordBlocking() {
		
		try {
			return input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
	}
}
