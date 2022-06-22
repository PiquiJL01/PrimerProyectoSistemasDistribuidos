package Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Stand {
	private List<Item> ingredents;
	private boolean isOccupied;
	private boolean hasIngredent;
	
	public Stand() {
		this.ingredents = new ArrayList<>();
		this.ingredents.addAll(addInitialIngredents());
		this.isOccupied = false;
		this.hasIngredent = true;
	}

	private List<Item> addInitialIngredents() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, Item.values().length);
		Item item = Item.values()[randomNum];

		List<Item> itemsList = new LinkedList<>(Arrays.asList(Item.values()));
		itemsList.remove(item);
		return itemsList;
	}
	

	public synchronized void refillIngredient(List<Item> newIngredients) throws InterruptedException {
		
		while(isOccupied || hasIngredent) wait(); // Si esta ocupado o tiene un ingrediente espera para llenar
		
		this.ingredents.addAll(newIngredients);
		this.hasIngredent = true;
		System.out.println("El vendedor coloca " + this.ingredents);
		notifyAll();
	}
	
	
	public synchronized List<Item> giveIngredient(Smoker smoker) throws InterruptedException {

		while (ingredents.isEmpty() || !this.hasIngredent) wait();

		List<Item> pickedItems = new ArrayList<>();
		
		if (!this.ingredents.contains(smoker.getInfiniteIngredient())) {
			pickedItems.addAll(this.ingredents);
			this.hasIngredent = false;
			this.ingredents.clear();
			System.out.println("El fumador [" + smoker.getInfiniteIngredient() +"] ha agarrado " + pickedItems);
			notifyAll();
		}

		return pickedItems;
	}

	public synchronized void finishSmoking (Smoker smoker) {

		smoker.finishSmoking();
		this.isOccupied = false;
		notifyAll();
	}
	
	public synchronized void startSmoke ( Smoker smoker) throws InterruptedException {

		while(isOccupied || ingredents.isEmpty() || smoker.iWantToSmoke()) wait();

		this.isOccupied = true;
		smoker.startSmoke(this);

	}

}
