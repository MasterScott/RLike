package roguelike.actors.AI;

import roguelike.actors.Creature;

/**
 * Artificial intelligence for creatures.
 * 
 * @author Dan
 * 
 */
public abstract class AI {

	Creature c;

	/**
	 * Creates a new artificial intelligence for the given creature.
	 * 
	 * @param c
	 *            Creature to give AI to.
	 */
	public AI(Creature c) {
		this.c = c;
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
}
