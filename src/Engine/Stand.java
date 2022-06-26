package Engine;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Stand implements Serializable {
	private Item ingredent;
	// private List<Item> ingredents;
	private boolean isOccupied;
	private boolean hasIngredent;
	
	public Stand(Boolean initial) {
		// this.ingredent = new ArrayList<>();
		if (initial == true) this.ingredent = addInitialIngredent();
		else  this.ingredent = null;
		this.isOccupied = false;
		this.hasIngredent = true;
	}

	public Item getIngredent() {
		return this.ingredent;
	}

	public boolean getIsOccupied() {
		return this.isOccupied;
	}

	public boolean getHasIngredent() {
		return this.hasIngredent;
	}

	private Item addInitialIngredent() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, Item.values().length);
		return Item.values()[randomNum];
	}
	

	public void refillIngredient(Item newIngredient) throws InterruptedException {
		
		if (!this.hasIngredent && !this.isOccupied) {
			this.ingredent = newIngredient;
			this.hasIngredent = true;
			System.out.println("El vendedor coloca " + this.ingredent);
		}
	}
	
	
	public Item giveIngredient(Smoker smoker) throws InterruptedException {

		Item pickedItem = null;
		
		if (this.ingredent != null && this.ingredent != smoker.getInfiniteIngredient() && !smoker.getMissingIngredients().contains(this.ingredent)) {
			pickedItem = this.ingredent;
			
			this.ingredent = null;
			this.hasIngredent = false;

			if (smoker.iWantToSmoke()) smoker.setAction(Accion.fumar);

			System.out.println("El fumador [" + smoker.getInfiniteIngredient() +"] ha agarrado [" + pickedItem + "]");
		}
		else {
			System.out.println("El fumador [" + smoker.getInfiniteIngredient() +"] no puede agarrar [" + pickedItem + "]");
		}
		
		return pickedItem;
	}

	public  void finishSmoking (Smoker smoker) throws InterruptedException {
		this.isOccupied = false;
		smoker.finishSmoking();
		Thread.sleep(2000);
	}
	
	public void startSmoke ( Smoker smoker) throws InterruptedException {


		System.out.println("Intentando fumar 2");

		if (smoker.iWantToSmoke()) {
			this.isOccupied = true;
			smoker.startSmoke(this);
			Thread.sleep(2000);
		}

	}

}
