package Engine;
import java.util.concurrent.ThreadLocalRandom;

public class Stand {
	private Item item1;
	private Item item2;
	
	public Stand() {
		abastecer();
	}
	
	private Item RNGItem() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
		if(randomNum == 1) {
			return Item.papel;
		}
		else {
			return Item.tabaco;
		}
	}
	
	public boolean Revisar(Item item) {
		if(item1 == item) {
			item1 = Item.vacio;
			return true;
		}
		if(item2 == item) {
			item2 = Item.vacio;
			return true;
		}
		return false;
	}
	
	public Item abastecer() {
		item1 = RNGItem();
		item2 = item1;
		return item1;
	}
}
