package Server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import Client.IThreadClient;
import Client.ThreadClient;
import Engine.Connection;
import Engine.Stand;

public class Servidor extends Connection {
	Servidor(String type) throws IOException {
		super(type);
	}

	public void startServer() throws IOException {
		Stand stand1 = new Stand();
		Stand stand2 = new Stand();
		Stand stand3 = new Stand();

		ArrayList<Stand> stands = new ArrayList<>();
        stands.add(stand1);
        stands.add(stand2);
        stands.add(stand3);

		System.out.println("Server is up");
		
		while (true) {
			cs = ss.accept();
			ThreadClient client = (ThreadClient) new Thread((Runnable) cs);
			client.start();
		}
	}

	
}
