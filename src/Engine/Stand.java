package Engine;

public class Stand {
	protected String ingredient1;
	
	public Stand() {
		refill();
	}

	public synchronized void refill(){
		ingredient1 = Item.randomItem().toString();
		Writer.Write("Stand llenado con " + ingredient1);
	}

	public synchronized boolean getIngredient(String item) {
		Writer.Write("Buscando " + item);
		Writer.Write(ingredient1);
		if(item == ingredient1){
			Writer.Write("Encontrado");
			ingredient1 = null;
			return true;
		}

		if((ingredient1 == null)){
			Writer.Write("haciendo refill");
			refill();
		}
		return false;
	}
}
