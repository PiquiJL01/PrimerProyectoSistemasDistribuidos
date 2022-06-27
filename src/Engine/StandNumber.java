package Engine;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public enum StandNumber {
    Stand1,
    Stand2,
    Stand3;



    public static final List<StandNumber> standNumers = new LinkedList<StandNumber>(List.of(values()));
	private static final int SIZE = standNumers.size();
	private static final Random RANDOM = new Random();

	public static List<StandNumber> randomStand()  {
		StandNumber removeStand = standNumers.get(RANDOM.nextInt(SIZE));
        standNumers.remove(removeStand);
        return standNumers;
	}
}
