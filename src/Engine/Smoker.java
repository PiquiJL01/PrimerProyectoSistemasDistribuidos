package Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Smoker extends Thread {

	private Item infiniteIngredient;
	private List<Item> missingIngedients;
	private boolean smoking; 
	private List<Stand> stands;

	Smoker (Item infiniteIngredient, List<Stand> stands) {
		this.infiniteIngredient = infiniteIngredient;
		this.missingIngedients = new ArrayList<>();
		this.smoking = false;
		this.stands = stands;
	}

	private boolean iWantToSmoke () {
		List<Item> compareList = new LinkedList<>(Arrays.asList(Item.values()));
		compareList.remove(this.infiniteIngredient);
		return this.missingIngedients.containsAll(compareList) ? true : false;
	}

	public void finishSmoking () {
		this.smoking = false;
		System.out.println("Fumador [" + infiniteIngredient +"] termino su cigarro");
		notifyAll();
	}
	
	public void startSmoke (List<Item> ingredients) {

		// while(smoking) wait();

		addMissingIngredients(ingredients);

		if (iWantToSmoke() && !smoking) {
			this.useIngredients();
			this.smoking = true;
			System.out.println("Fumador [" + infiniteIngredient +"] empieza su cigarro");

			notifyAll();
		}
	}

	public void addMissingIngredients (List<Item> ingredients) {
		if (!ingredients.contains(this.infiniteIngredient)) {
			this.missingIngedients.addAll(ingredients);
		}
	}

	private void useIngredients () {
		this.missingIngedients.clear();
	}

	public void orderNewIngredients () {

	}

	public void run () {
		while (true) {
			try {
				for (Stand stand : stands) {
					this.startSmoke(stand.giveIngredient());
					Thread.sleep(5000);
					this.finishSmoking();
					Thread.sleep(3000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
