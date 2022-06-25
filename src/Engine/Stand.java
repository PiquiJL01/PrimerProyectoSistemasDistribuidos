package Engine;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Stand implements Serializable {
	private Item ingredent;
	// private List<Item> ingredents;
	private boolean isOccupied;
	private boolean hasIngredent;
	
	public Stand() {
		// this.ingredent = new ArrayList<>();
		this.ingredent = addInitialIngredent();
		this.isOccupied = false;
		this.hasIngredent = true;
	}

	private Item addInitialIngredent() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, Item.values().length);
		return Item.values()[randomNum];
		// Item item = Item.values()[randomNum];

		// List<Item> itemsList = new LinkedList<>(Arrays.asList(Item.values()));
		// itemsList.remove(item);
		// return itemsList;
	}
	

	public synchronized void refillIngredient(Item newIngredient) throws InterruptedException {
		
		while(isOccupied || hasIngredent) wait(); // Si esta ocupado o tiene un ingrediente espera para llenar
		
		this.ingredent = newIngredient;
		this.hasIngredent = true;
		System.out.println("El vendedor coloca " + this.ingredent);
		notifyAll();
	}
	
	
	public synchronized Item giveIngredient(Smoker smoker) throws InterruptedException {

		while (ingredent == null || !this.hasIngredent) wait();

		// List<Item> pickedItems = new ArrayList<>();
		Item pickedItem = null;
		
		if (this.ingredent != smoker.getInfiniteIngredient() && !smoker.getMissingIngredients().contains(this.ingredent)) {
			pickedItem = this.ingredent;
			this.ingredent = null;
			this.hasIngredent = false;
			// this.ingredents.clear();
			System.out.println("El fumador [" + smoker.getInfiniteIngredient() +"] ha agarrado [" + pickedItem + "]");
			notifyAll();
		}
		
		return pickedItem;
	}

	public synchronized void finishSmoking (Smoker smoker) {
		this.isOccupied = false;
		smoker.finishSmoking();
		notifyAll();
	}
	
	public synchronized void startSmoke ( Smoker smoker) throws InterruptedException {

		while(isOccupied || ingredent == null || ingredent == smoker.getInfiniteIngredient()) wait();

		if (smoker.iWantToSmoke()) {
			this.isOccupied = true;
			smoker.startSmoke(this);
		}

		// smoker.addMissingIngredient(this.giveIngredient(smoker));

	}

}
