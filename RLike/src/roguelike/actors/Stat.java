package roguelike.actors;

/**
 * Template for a stat to be used by an actor.
 * 
 * @author Dan
 * 
 */
public class Stat {

	private int cur, max;

	/**
	 * Define a new stat with the specified max value.
	 * 
	 * @param max
	 *            Maximum value of stat.
	 */
	public Stat(int max) {
		this.max = max;
		this.cur = max;
	}

	/**
	 * Returns current value of this stat.
	 * 
	 * @return Current value of stat.
	 */
	public int getCur() {
		return cur;
	}

	/**
	 * Sets current value of this stat.
	 * 
	 * @param cur
	 *            Value to set stat to.
	 */
	public void setCur(int cur) {
		this.cur = cur;
	}

	/**
	 * Returns maximum value of stat.
	 * 
	 * @return Maximum value of stat.
	 */
	public int getMax() {
		return max;
	}

	/**
	 * Sets maximum value of this stat.
	 * 
	 * @param max
	 *            Value to set max to.
	 */
	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return cur + "/" + max;
	}

}
