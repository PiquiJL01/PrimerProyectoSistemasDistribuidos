package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Engine.Accion;
import Engine.Connection;
import Engine.Item;
import Engine.Seller;
import Engine.Smoker;
import Engine.Stand;

public class Servidor extends Thread {

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ArrayList<Stand> stands;
	
	public Servidor(ObjectInputStream in, ObjectOutputStream out) {
		Stand stand1 = new Stand(true);
		Stand stand2 = new Stand(true);
		Stand stand3 = new Stand(false);

		stands.add(stand1);
        stands.add(stand2);
        stands.add(stand3);

		this.in = in;
		this.out = out;
	}

	@Override
	public void run() {

		try {

			Smoker recivedSmoker;
			Seller recivedSeller;

			while(true) {

				Accion recivedAction = (Accion) in.readObject();
	 
				for (Stand stand : stands) {
					
					recivedSmoker = (Smoker) in.readObject();
					recivedSeller = (Seller) in.readObject();
				
					if (recivedSmoker.iWantToSmoke()) {
						stand.startSmoke(recivedSmoker);
						Thread.sleep(2000);
						stand.finishSmoking(recivedSmoker);
						Thread.sleep(2000);
					}

					Thread.sleep(2000);
					stand.refillIngredient(recivedSeller.selectItems());
					Thread.sleep(2000);
				
					// if (recivedAction == Accion.buscar) {
					// 	recivedSmoker = (Smoker) in.readObject();
						
					// }
	
					// if (recivedAction == Accion.abastecer) {
					// 	recivedSeller = (Seller) in.readObject();
						
					// }

					// if (recivedAction == Accion.pedir) {
					// 	// pide al vendedor
					// }

					// if (recivedAction == Accion.fumar) {

					// }

					// if (recivedAction == Accion.recibir) {

					// }
						
				}

	
	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
