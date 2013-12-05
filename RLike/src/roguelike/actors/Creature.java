package roguelike.actors;

import java.awt.Color;

import roguelike.actors.classes.Classes;
import roguelike.actors.classes.RLClass;

/**
 * Contains all relevant data for a creature - any object that can perform
 * actions like a player can but is controlled by AI rather than by a user.
 * 
 * @author Dan
 * 
 */
public class Creature extends Actor {

	public Stat hp, mp, strength, intelligence, dexterity;
	private boolean hostile;
	private RLClass c;

	public Creature(char icon, Color color, int x, int y) {
		super(icon, color, x, y);
		traversable = false;
	}

	/**
	 * Sets the class of this creature.
	 * 
	 * @param c
	 *            Class of this creature.
	 */
	public void setCreatureClass(Classes c) {
		this.c = c;
	}

	/**
	 * Returns the class of this creature.
	 * 
	 * @return Class of this creature.
	 */
	public RLClass getCreatureClass() {
		return c;
	}

	/**
	 * Sets whether or not this creature is hostile to the player.
	 * 
	 * @param hostile
	 *            Whether or not this creature is hostile to the player.
	 */
	public void setHostile(boolean hostile) {
		this.hostile = hostile;
	}

	/**
	 * Returns whether or not this creature is hostile to the player.
	 * 
	 * @return Whether or not this creature is hostile to the player.
	 */
	public boolean isHostile() {
		return hostile;
	}

	public boolean meleeAttack(Creature recipient) {
		// TODO Attack calculations.
		/*
		 * Need to determine whether or not the attack was successful, how much
		 * damage it did, etc.
		 */

		recipient.hp.current -= (int) (Math.random() * 2 + 1);
		return true;
	}
}
