package Engine;

public class Stand {
	protected Item ingredient1;
	protected Item ingredient2;
	
	public Stand() {
		refill();
	}

	public void refill(){
		ingredient1 = Item.randomItem();
		Writer.WriteStandContent(ingredient1);
		ingredient2 = Item.randomItem();
		Writer.WriteStandContent(ingredient2);
	}

	public boolean getIngredient(Item item) {
		if(item == ingredient1){
			ingredient1 = null;
			return true;
		}
		if (item == ingredient2){
			ingredient2 = null;
			return true;
		}
		if((ingredient1 == null) && (ingredient2 == null)){
			refill();
		}
		return false;
	}
}
