package Server;

import java.io.IOException;
// import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Engine.Accion;
import Engine.Item;
// import Engine.Connection;
// import Engine.Item;
import Engine.Seller;
import Engine.Smoker;
import Engine.Stand;

public class Servidor extends Thread  {

	private ObjectInputStream inSmoker, inAction, inItem;
    private ObjectOutputStream outSmoker, outAction, outItem;
	private ArrayList<Stand> stands;
	private Accion action;
	
	public Servidor(ArrayList<Stand> stands, ObjectInputStream inSmoker, ObjectOutputStream outSmoker, ObjectInputStream inAction, ObjectOutputStream outAction, ObjectInputStream inItem, ObjectOutputStream outItem) {

		this.stands = stands;

		this.inSmoker = inSmoker;
		this.outSmoker = outSmoker;;
		this.inAction = inAction;
		this.outAction = outAction;
		this.inItem = inItem;
		this.outItem = outItem;
		
	}

	@Override
	public void run() {

		try {
			while (true) {

				this.action = (Accion) inAction.readObject();

				if(action.equals(Accion.buscar)) {
					//recibo y no termino hasta tener los ingredientes para fumar
					Smoker smoker = (Smoker) inSmoker.readObject();

					for (Stand stand: stands) {
						//siempre busco en los bancos
						stand.startSmoke(smoker);
						Thread.sleep(1000);
						stand.finishSmoking(smoker);
					}

				}

				if(action.equals(Accion.recibir)) {
					Thread.sleep(2000);
					System.out.println("Obteniendo recibir desde el cliente " + action);
					
				}

			}
		} catch (Exception  e) {
			e.printStackTrace();
		}
		
	}


	public void darleAlFumadorSuVaina(ArrayList<Stand> stands) {

		for (Stand stand : stands) {
			
		}


	}

	
}
