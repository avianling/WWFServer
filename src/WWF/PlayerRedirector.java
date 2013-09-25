package WWF;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class PlayerRedirector implements Runnable {

	private Player otherPlayer;
	private Player thisPlayer;
	
	public PlayerRedirector( Player me, Player otherPlayer )
	{
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
				String s = thisPlayer.getWordBlocking();
				otherPlayer.sendWord(s);	
			}
		
	}

}
