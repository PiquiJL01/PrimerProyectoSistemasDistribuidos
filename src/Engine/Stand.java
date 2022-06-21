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
		pickedItems.addAll(this.ingredents);
		this.hasIngredent = false;
		this.ingredents.clear();
		System.out.println("El fumador [" + smoker.getInfiniteIngredient() +"] ha agarrado " + pickedItems);
		notifyAll();
		return pickedItems;
	}

	// public synchronized void finishSmoking () {
	// 	// while ()
		
	// 	this.isOccupied = false;
	// 	System.out.println("Fumador [" + infiniteIngredient +"] termino su cigarro");
	// 	notifyAll();
	// }
	
	// public synchronized void startSmoke (List<Item> ingredients) throws InterruptedException {

	// 	while(smoking || missingIngedients.isEmpty()) wait();

	// 	addMissingIngredients(ingredients);

	// 	if (iWantToSmoke() && !smoking) {
	// 		this.useIngredients();
	// 		this.smoking = true;
	// 		System.out.println("Fumador [" + infiniteIngredient +"] empieza su cigarro");

	// 		notifyAll();
	// 	}
	// }

	
	// public synchronized boolean hasIngredient() throws InterruptedException {
	// 	if (this.hasIngredient) return true;
	// 	return false;
	// }
	// public synchronized boolean hasSmoker() throws InterruptedException {
	// 	if (this.isOccupied) return true;
	// 	return false;
	// } 
}
