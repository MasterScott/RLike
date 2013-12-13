package roguelike.actors.abilities;

import roguelike.actors.Actor;
import roguelike.actors.Creature;

public class ActionMethods {

	private static int damage;

	/**
	 * Performs a melee attack against the given recipient.
	 * 
	 * @param performer
	 *            Performer of the attack.
	 * @param recipient
	 *            Recipient of the attack.
	 */
	public static void meleeAttack(Actor performer, Actor recipient) {
		damage = (int) (Math.random() * 2 + 1);
		((Creature) recipient).hp.current -= (int) (Math.random() * 2 + 1);
	}

	/**
	 * Returns the most recent damage done by any of these actions.
	 * 
	 * @return Most recent damage done.
	 */
	public static int getDamage() {
		return damage;
	}
}
