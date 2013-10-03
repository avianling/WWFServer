package WWF;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;

public class Message {
	public String payload;
	
	public Message( String s ) {
		payload = s;
	}
	
	/*public String serialize() {
		return payload.length() + payload;
	}*/
	
	public void send( BufferedWriter s ) throws IOException {
		s.write(payload.length());
		s.flush();
		
		s.write(payload);
		s.flush();
	}
}
