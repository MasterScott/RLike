package roguelike.actors;

/**
 * Template for a stat to be used by an actor.
 * 
 * @author Dan
 * 
 */
public class Stat {

	/**
	 * Current value of stat.
	 */
	public int current;
	
	/**
	 * Maximum value of stat.
	 */
	public int max;

	/**
	 * Define a new stat with the specified max value.
	 * 
	 * @param value
	 *            Value of stat.
	 */
	public Stat(int value) {
		this.max = value;
		this.current = value;
	}

	@Override
	public String toString() {
		return current + "/" + max;
	}

}
