package roguelike.etc.random;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implementation of a weighted object randomizer. Most code in generate() taken
 * from http://www.roguebasin.com/index.php?title=Weighted_random_generator with
 * a few minor tweaks.
 * 
 * @author Dan
 * 
 */
public class RLRandom {

	Random random;

	/**
	 * Create a new RLRandom object with a randomized seed.
	 */
	public RLRandom() {
		random = new Random();
	}

	/**
	 * Sets the seed used for pulling random numbers to the specified seed. Used
	 * for debugging purposes.
	 * 
	 * @param seed
	 *            Seed to set the randomizer to.
	 */
	public void setSeed(long seed) {
		random.setSeed(seed);
	}

	/**
	 * Returns a random choice from a list of weighted random items.
	 * 
	 * @param list
	 *            List to choose from.
	 * @param level
	 *            Depth of the floor in which this method is being called from.
	 * @return Random choice from list.
	 */
	public WeightedRandom generate(WeightedRandom[] list, int level) {
		int max = 0;
		ArrayList<WeightedRandom> table = new ArrayList<WeightedRandom>();

		// Add all items to table and determine maximum roll.
		for (int i = 0; i < list.length; i++) {
			if (list[i].getMinLevel() <= level && list[i].getMaxLevel() >= level) {
				table.add(list[i]);
				max += list[i].getWeight();
			}
		}

		if (table.size() == 0)
			return null;

		int roll = (int) (random.nextDouble() * max);

		/*
		 * Iterate through the list and check if the roll is less than each
		 * item's weight. If it is not, subtract that item's weight from the
		 * value of roll and move on to the next item.
		 */
		for (int i = 0; i < table.size(); i++) {
			if (roll < table.get(i).getWeight())
				return table.get(i);

			roll -= table.get(i).getWeight();
		}

		return null;
	}

}
