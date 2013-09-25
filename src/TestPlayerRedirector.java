import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;


public class TestPlayerRedirector implements Runnable {

	public TestPlayerRedirector( Socket playerConnection ) {
		player = playerConnection;
		builder = new StringBuilder();
		rand = new Random();
	}
	
	public Socket player; 
	
	private StringBuilder builder;
	private Random rand;
	
	/**
	 * Return all of the game information required for the new game.
	 * @return
	 */
	public String generateGameInformation() {
		builder = new StringBuilder();
		
		builder.append("Game Found\n");
		
		// Add the letter pool seed
		builder.append( rand.nextInt() );
		builder.append("\n");
		
		return builder.toString();
		
	}
	
	
	@Override
	public void run() {
		try {
			System.out.println("Writing data!");
			OutputStreamWriter playerOutput = new OutputStreamWriter( player.getOutputStream() );
			BufferedReader playerInput = new BufferedReader( new InputStreamReader( player.getInputStream() ));
			BufferedReader consoleInput = new BufferedReader( new InputStreamReader( System.in ));
			
			//playerOutput.write("Game Found\n");
			// Determine the info for the new game. 
			playerOutput.write(generateGameInformation());
			playerOutput.flush();
			
			while(true) {
				
				String playerWord = playerInput.readLine();
				System.out.println(" The player wrote: " + playerWord );
				System.out.println("Enter your response:" );
				playerOutput.write(consoleInput.readLine() + "\n");
				playerOutput.flush();
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
