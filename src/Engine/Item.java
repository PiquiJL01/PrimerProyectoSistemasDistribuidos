package Engine;

import java.util.List;
import java.util.Random;

public enum Item{
	papel,
	tabaco,
	fosforo;

	public static final List<Item> Items =
			List.of(values());
	private static final int SIZE = Items.size();
	private static final Random RANDOM = new Random();

	public static Item randomItem()  {
		return Items.get(RANDOM.nextInt(SIZE));
	}
}