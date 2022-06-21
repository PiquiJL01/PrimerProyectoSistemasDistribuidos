package Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Seller extends Thread {

	private final ArrayList<Stand> stands;

	Seller (ArrayList<Stand> stands) {
		this.stands = stands;
	}

	public List<Item> selectItems() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, Item.values().length);
		Item item = Item.values()[randomNum];

		List<Item> itemsList = new LinkedList<>(Arrays.asList(Item.values()));
		itemsList.remove(item);
		return itemsList;
	}

	public void addItemToStand (Stand stand) {
		try {
			if (stands.contains(stand)) stand.refillIngredient(selectItems());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run () {
		while (true) {
			try {
				// necesito ir chequeando los stands para llenarlos
				for (Stand stand : stands) {
					Thread.sleep(2000);
					this.addItemToStand(stand);
					Thread.sleep(2000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
