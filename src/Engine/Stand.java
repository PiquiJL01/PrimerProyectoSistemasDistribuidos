package Engine;

public class Stand {
	protected Item ingredient1;
	
	public Stand() {
		refill();
	}

	public void refill(){
		ingredient1 = Item.randomItem();
		Writer.WriteStandContent(ingredient1);
	}

	public boolean getIngredient(Item item) {
		if(item == ingredient1){
			ingredient1 = null;
			return true;
		}

		if((ingredient1 == null)){
			refill();
		}
		return false;
	}
}
