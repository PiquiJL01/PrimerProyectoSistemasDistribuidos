package Engine;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Seller extends Thread {

	private final ArrayList<Stand> stands;

	Seller (ArrayList<Stand> stands) {
		this.stands = stands;
	}

	public Item selectItem() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, Item.values().length);
		Item[] item = Item.values();
		return item[randomNum];
	}

	public void addItemToStand (Stand stand) {
		try {
			if (stands.contains(stand)) stand.refillIngredient(selectItem());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void run () {
		while (true) {
			try {
				Thread.sleep(3000);
				// necesito ir chequeando los stands para llenarlos
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
