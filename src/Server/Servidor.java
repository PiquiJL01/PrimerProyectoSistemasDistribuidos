package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Engine.Connection;
import Engine.Item;
import Engine.Seller;
import Engine.Stand;

public class Servidor extends Thread {

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ArrayList<Stand> stands;
	
	public Servidor(ObjectInputStream in, ObjectOutputStream out) {
		Stand stand1 = new Stand();
		Stand stand2 = new Stand();
		Stand stand3 = new Stand();

		stands.add(stand1);
        stands.add(stand2);
        stands.add(stand3);

		this.in = in;
		this.out = out;
	}

	@Override
	public void run() {
		
	}

	
}
