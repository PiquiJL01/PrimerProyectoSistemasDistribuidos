package Engine;
public class Stand {
	private Item ingredient;
	private boolean isOccupied;
	private boolean hasIngredient;
	private Seller seller;
	
	public Stand(Seller seller) {
		this.seller = seller;
		this.ingredient = seller.selectItem();
		this.isOccupied = false;
		this.hasIngredient = true;
	}

	public synchronized void refillIngredient(Item newIngredient) throws InterruptedException {
		if (!this.hasIngredient()) this.ingredient = newIngredient;
	}
	
	public synchronized boolean hasIngredient() throws InterruptedException {
		if (this.hasIngredient) return true;
		return false;
	}

	public synchronized Item pickIngredient() throws InterruptedException {
		Item pickedItem = this.ingredient;
		this.hasIngredient = false;
		this.ingredient = null;
		return pickedItem;
	}

	public synchronized boolean hasSmoker() throws InterruptedException {
		return this.isOccupied = true;
	} 
}
