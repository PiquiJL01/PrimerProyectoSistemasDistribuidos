package Engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Smoker implements Serializable{

	private Item infiniteIngredient;
	private List<Item> missingIngedients;
	private boolean smoking; 
	private Accion action;
	private int intentos;

	public Smoker (Item infiniteIngredient) {
		this.infiniteIngredient = infiniteIngredient;
		this.missingIngedients = new ArrayList<>();
		this.smoking = false;
		this.action = Accion.buscar;
		this.intentos = 0;
	}

	public Accion getAction () {
		return this.action;
	}

	public void setAction (Accion action) {
		this.action = action;
	}

	public Item getInfiniteIngredient() {
		return this.infiniteIngredient;
	}

	public List<Item> getMissingIngredients() {
		return this.missingIngedients;
	}

	public boolean iWantToSmoke () {
		List<Item> compareList = new LinkedList<>(Arrays.asList(Item.values()));
		compareList.remove(this.infiniteIngredient);
		return this.missingIngedients.containsAll(compareList);
	}

	public void finishSmoking () {
		this.action = Accion.buscar;
		this.smoking = false;
		System.out.println("Fumador [" + infiniteIngredient +"] termino su cigarro");
	}

	// public void 
	
	public void startSmoke (Stand stand) throws InterruptedException {
		if (stand.getIngredent() != null) {
			addMissingIngredient(stand.giveIngredient(this));

			if (!this.smoking && iWantToSmoke()) {
				this.action = Accion.fumar;
				this.useIngredients();
				this.smoking = true;
				System.out.println("Fumador [" + infiniteIngredient +"] empieza su cigarro");
			}
		}
	}

	public void addMissingIngredient (Item ingredient) {
		if (ingredient != null && ingredient != this.infiniteIngredient && !missingIngedients.contains(ingredient)) { 
			this.missingIngedients.add(ingredient);
			this.action = Accion.recibir;
			System.out.println("Fumador [" + infiniteIngredient +"] ha añadido "  + ingredient);
		}
	}

	public void useIngredients () {
		this.missingIngedients.clear();
	}

	public void orderNewIngredients () {
		if (this.intentos == 2) this.action = Accion.pedir;
	}

	

	// public void run () {
	// 	while (true) {
	// 		try {
	// 			for (Stand stand : stands) {
	// 				// System.out.println("stand");
	// 				if (this.iWantToSmoke()) {
	// 					stand.startSmoke(this);
	// 					Thread.sleep(5000);
	// 					stand.finishSmoking(this);
	// 					Thread.sleep(3000);
	// 				} else {
	// 					this.addMissingIngredient(stand.giveIngredient(this));
	// 					System.out.println("Fumador [" + infiniteIngredient +"] esta buscando mas ingredientes. Actual: " + this.missingIngedients);

	// 				}	
	// 			}
	// 		} catch (InterruptedException e) {
	// 			e.printStackTrace();
	// 		}
	// 	}
	// }

}
