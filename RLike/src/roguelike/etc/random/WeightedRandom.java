package roguelike.etc.random;

/**
 * Interface used by all objects appearing at random in the game. Each object
 * has a minimum level it can appear, maximum level it can appear, and
 * probability of appearing.
 * 
 * @author Dan
 * 
 */
public interface WeightedRandom {

	/**
	 * Returns minimum floor this element can be found on.
	 * 
	 * @return Minimum floor this element can be found on.
	 */
	public int getMinLevel();

	/**
	 * Returns maximum floor this element can be found on.
	 * 
	 * @return Maximum floor this element can be found on.
	 */
	public int getMaxLevel();

	/**
	 * Returns the weighted probability of this element appearing.
	 * 
	 * @return Weighted probability of this element appearing.
	 */
	public int getWeight();

	/**
	 * Returns the name of this element.
	 * 
	 * @return Name of this element.
	 */
	public String getName();
}
