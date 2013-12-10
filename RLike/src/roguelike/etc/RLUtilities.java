package roguelike.etc;

public class RLUtilities {

	/**
	 * Return a random integer given a minimum and maximum and percentage
	 * thresholds.
	 * 
	 * @param min
	 *            Minimum number to apply bottom threshold to.
	 * @param max
	 *            Maximum number to apply top threshold to.
	 * @param bottomThreshold
	 *            Lowest possible percentage of the range to return.
	 * @param topThreshold
	 *            Highest possible percentage of the range to return.
	 * @return Random integer within the given thresholds.
	 */
	public static int generateRandom(int min, int max, double bottomThreshold, double topThreshold) {
		int result = (int) (((Math.random()
				* ((min + (max - min) * topThreshold) - (min + (max - min) * bottomThreshold)) + 1) + (min + (max - min)
				* bottomThreshold)));
		return result;
	}
}
