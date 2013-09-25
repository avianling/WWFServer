import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class PlayerRedirector implements Runnable {

	private Socket otherPlayer;
	private Socket thisPlayer;
	
	public PlayerRedirector( Socket me, Socket otherPlayer )
	{
		thisPlayer = me;
		this.otherPlayer = otherPlayer;
		
		new Thread(this).run();
	}
	
	@Override
	public void run() {
		
		// get the input stream from me
		// forward everything in it to the other player.
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(thisPlayer.getInputStream()));
			OutputStreamWriter output = new OutputStreamWriter(otherPlayer.getOutputStream());
			
			// Tell this player that a game has been found.
			output.write("Game Found\n");
			
			while( true )
			{
				String s = input.readLine();
				System.out.println(s);
				output.write(s);
				output.write("\n");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
