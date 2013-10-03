package WWF;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Handles receiving messages from one client and sending them to another.
 * @author Alex
 *
 */
public class PlayerRedirector implements Runnable {

	private Player otherPlayer;
	private Player thisPlayer;
	
	private Game game;
	
	public PlayerRedirector( Player me, Player otherPlayer, Game game )
	{
		this.game = game;
		thisPlayer = me;
		this.otherPlayer = otherPlayer;
		
		new Thread(this).start();
		System.out.println("Finished creating a player");
	}
	
	@Override
	public void run() {
		
		// get the input stream from me
		// forward everything in it to the other player.
			System.out.println("Thread started, sending out a game started message");
			
			while( true )
			{	
				try {
					String s = thisPlayer.getWordBlocking();
					otherPlayer.sendWord(s);
				} catch ( Exception e ) {
					e.printStackTrace();
				}
			}
		
	}

}
