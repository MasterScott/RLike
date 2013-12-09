package roguelike.actors;

import roguelike.actors.classes.Classes;
import roguelike.actors.classes.RLClass;
import roguelike.ui.graphics.Graphic.GraphicFile;

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

	/**
	 * Creates a new creature at the given coordinates.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 */
	public Creature(int x, int y) {
		super(x, y);
		traversable = false;
	}

	/**
	 * Creates a new creature at the given coordinates with the specified image.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @param graphicFile
	 *            Tileset to use.
	 * @param row
	 *            Row of icon to use.
	 * @param col
	 *            Column of icon to use.
	 */
	public Creature(int x, int y, GraphicFile graphicFile, int row, int col) {
		super(x,y);
		setImage(graphicFile, row, col);
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
