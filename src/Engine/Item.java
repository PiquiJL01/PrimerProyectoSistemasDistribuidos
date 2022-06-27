package Engine;

import java.util.List;
import java.util.Random;

public enum Item{
	papel("Papel"),
	tabaco("Tabaco"),
	fosforo("Fosforo");

	private final String text;

	/**
	 * @param text
	 */
	Item(final String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return text;
	}

	public static final List<Item> Items =
			List.of(values());
	private static final int SIZE = Items.size();
	private static final Random RANDOM = new Random();

	public static Item randomItem()  {
		return Items.get(RANDOM.nextInt(SIZE));
	}
}