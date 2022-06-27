package Engine;

public class Stand {
	protected String ingredient1;
	protected String  ingredient2;
	
	public Stand() {
		refill();
	}

	public void refill(){
		ingredient1 = Item.randomItem().toString();
		Writer.Write("Stand llenado con " + ingredient1);
		ingredient2 = Item.randomItem().toString();
		Writer.Write("Stand llenado con " + ingredient2);
	}

	public boolean getIngredient(String item) {
		Writer.Write("Buscando " + item);
		Writer.Write(ingredient1);
		if(item == ingredient1){
			Writer.Write("Encontrado");
			ingredient1 = null;
			return true;
		}

		if((ingredient1 == null)){
			refill();
		}
		return false;
	}
}
