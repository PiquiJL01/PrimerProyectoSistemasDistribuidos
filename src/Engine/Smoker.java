package Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Smoker extends Thread {

	private Item infiniteIngredient;
	private List<Item> missingIngedients;
	private boolean smoking; 
	private Stand stand;

	Smoker (Item infiniteIngredient) {
		this.infiniteIngredient = infiniteIngredient;
		this.missingIngedients = new ArrayList<>();
		this.smoking = false;
	}

	private boolean iWantToSmoke () {
		List<Item> compareList = new LinkedList<>(Arrays.asList(Item.values()));
		compareList.remove(this.infiniteIngredient);
		return this.missingIngedients.containsAll(compareList) ? true : false;
	}

	public void finishSmoking () {
		this.smoking = false;
	}
	
	public void startSmoke () {
		if (iWantToSmoke() && !smoking) {
			this.useIngredients();
			this.smoking = true;
		}
	}

	public void addMissingIngredient (Item item) {
		if (!this.missingIngedients.contains(item) && item != this.infiniteIngredient) {
			this.missingIngedients.add(item);
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
				if (!stand.hasSmoker()) {
					this.startSmoke();
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
