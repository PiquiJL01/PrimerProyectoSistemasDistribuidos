package Engine;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Seller extends Thread implements Serializable {

	// private final List<Stand> stands;
	private Accion action;

	public Seller () {
		// this.stands = stands;
	}

	public Item selectItems() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, Item.values().length);
		return Item.values()[randomNum];
	}

	public void addItemToStand (Stand stand) {
		try {
			stand.refillIngredient(selectItems());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	// public void run () {
	// 	while (true) {
	// 		try {
	// 			// necesito ir chequeando los stands para llenarlos
	// 			for (Stand stand : stands) {
	// 				Thread.sleep(2000);
	// 				this.addItemToStand(stand);
	// 				Thread.sleep(2000);
	// 			}
	// 		} catch (InterruptedException e) {
	// 			e.printStackTrace();
	// 		}
	// 	}
	// }
}
