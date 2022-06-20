package Engine;

import java.io.IOException;
import java.net.*;

public class Servidor {
	private static final int PORT = 5059;
	
	public static void main(String[] args) throws IOException {
		// Stand stand1 = new Stand();
		// Stand stand2 = new Stand();
		// Stand stand3 = new Stand();

		ServerSocket ss = new ServerSocket(PORT);
		System.out.println("Server up");
		while (true) {
			
			Socket s = null;
			try {
				s = ss.accept(); // accept new client connection

				System.out.println("A client was connected: " + s);

				Thread hilo = new Handler();

			} catch(Exception e) {
				ss.close();
			}
		}
	}	
}
