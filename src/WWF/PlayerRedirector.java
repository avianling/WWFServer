package WWF;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class PlayerRedirector < E extends Player > implements Runnable {

	private E otherPlayer;
	private E thisPlayer;
	
	public PlayerRedirector( E me, E otherPlayer )
	{
		thisPlayer = me;
		this.otherPlayer = otherPlayer;
		
		new Thread(this).run();
	}
	
	@Override
	public void run() {
		
		// get the input stream from me
		// forward everything in it to the other player.
			thisPlayer.startGame();
			
			while( true )
			{	
				String s = thisPlayer.getWordBlocking();
				otherPlayer.sendWord(s);	
			}
		
	}

}
