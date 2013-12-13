package roguelike.actors.AI;

import roguelike.actors.Creature;

/**
 * Artificial intelligence for creatures.
 * 
 * @author Dan
 * 
 */
public abstract class AI {

	protected Creature c;

	/**
	 * Creates a new artificial intelligence for the given creature.
	 */
	public AI() {

	}

	/**
	 * Performs this creature's highest prioritized action.
	 */
	public abstract void doPrioritizedAction();

	/**
	 * Returns a random chance of one out of the given number. i.e. if the
	 * number is twenty, the method would return true one out of every twenty
	 * times.
	 * 
	 * @param num
	 *            Number to base chance on.
	 * @return True if one; otherwise false.
	 */
	boolean chanceOfOneIn(int num) {
		int i = (int) Math.random() * num;
		return i == 0;
	}

	/**
	 * Moves in the specified direction.
	 * 
	 * @param dx
	 *            x-offset.
	 * @param dy
	 *            y-offset.
	 */
	public void moveToward(int dx, int dy) {
		int x = c.getX();
		int y = c.getY();

		c.setCoords(x + dx, y + dy);
	}

	/**
	 * Sets host for this AI.
	 * 
	 * @param c
	 *            Creature to utilize AI.
	 */
	public void setHost(Creature c) {
		this.c = c;
	}

}
