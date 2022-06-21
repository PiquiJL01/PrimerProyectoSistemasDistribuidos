package Engine;

import java.util.ArrayList;
import java.util.List;

public class Stand {
	private List<Item> ingredients;
	private boolean isOccupied;
	private boolean hasIngredient;
	
	public Stand() {
		this.ingredients = new ArrayList<>();
		this.isOccupied = false;
		this.hasIngredient = true;
	}

	public synchronized void refillIngredient(List<Item> newIngredients) throws InterruptedException {
		
		while(isOccupied || hasIngredient) wait(); // Si esta ocupado o tiene un ingrediente espera para llenar
		
		this.ingredients.addAll(newIngredients);
		this.hasIngredient = true;
		System.out.println("El vendedor coloca " + this.ingredients);
		notifyAll();
	}
	
	
	public synchronized List<Item> giveIngredient(Smoker smoker) throws InterruptedException {

		while (!ingredients.isEmpty()) wait();

		List<Item> pickedItems = this.ingredients;
		this.hasIngredient = false;
		this.ingredients.clear();
		System.out.println("El fumador [" + smoker +"] ha agarrado ¨"+ pickedItems + "");
		notifyAll();
		return pickedItems;
	}
	
	// public synchronized boolean hasIngredient() throws InterruptedException {
	// 	if (this.hasIngredient) return true;
	// 	return false;
	// }
	// public synchronized boolean hasSmoker() throws InterruptedException {
	// 	if (this.isOccupied) return true;
	// 	return false;
	// } 
}
