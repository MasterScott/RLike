package roguelike.actors;

import roguelike.actors.AI.AI;
import roguelike.actors.abilities.ActionList;
import roguelike.actors.classes.Classes;
import roguelike.actors.classes.RLClass;
import roguelike.etc.Session;
import roguelike.ui.graphics.Graphic.GraphicFile;

/**
 * Contains all relevant data for a creature - any object that can perform
 * actions like a player can but is controlled by AI rather than by a user.
 * 
 * @author Dan
 * 
 */
public class Creature extends Actor {

	private final int DEFAULT_REGEN_RATE = 5;
	private final int DEFAULT_REGEN_AMOUNT = 1;

	public Stat hp, mp, strength, intelligence, dexterity;
	private boolean hostile;
	String name;
	int regenRate = DEFAULT_REGEN_RATE;
	int regenAmount = DEFAULT_REGEN_AMOUNT;
	RLClass c;
	AI ai;

	/**
	 * Creates a new creature at the given coordinates.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @param name
	 *            Name of creature.
	 */
	public Creature(int x, int y, String name) {
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
	 * @param name
	 *            Name of creature.
	 * @param graphicFile
	 *            Tileset to use.
	 * @param row
	 *            Row of icon to use.
	 * @param col
	 *            Column of icon to use.
	 */
	public Creature(int x, int y, String name, GraphicFile graphicFile, int row, int col) {
		super(x, y);
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

	/**
	 * Sets creatures AI to the specified AI.
	 * 
	 * @param ai
	 *            AI to set for creature.
	 */
	public void setAI(AI ai) {
		ai.setHost(this);
		this.ai = ai;
	}

	/**
	 * Returns AI of creature.
	 * 
	 * @return AI of creature.
	 */
	public AI getAI() {
		return ai;
	}

	/**
	 * Does highest prioritized action as defined by this creatures AI.
	 * 
	 * @throws NullPointerException
	 *             If this creature does not have an AI defined.
	 */
	public void doPrioritizedAction() throws NullPointerException {
		if (ai == null)
			throw new NullPointerException("Creature needs to have an AI defined.");
		ai.doPrioritizedAction();
	}

	/**
	 * Performs a melee attack against the specified creature.
	 * 
	 * @param recipient
	 *            Creature to perform melee attack against.
	 * @return True if attack was successful; false otherwise.
	 */
	public boolean meleeAttack(Creature recipient) {
		// TODO Attack calculations.
		/*
		 * Need to determine whether or not the attack was successful, how much
		 * damage it did, etc.
		 */

		ActionList.MELEE_ATTACK.doAction(this, recipient);
		return true;
	}

	/**
	 * Sets creature's natural regen to happen one out of every x turns, where x
	 * is specified by rate.
	 * 
	 * @param rate
	 *            Regeneration rate.
	 */
	public void setRegenRate(int rate) {
		this.regenRate = rate;
	}

	/**
	 * Returns the denominator of the creature's 1/x chance to regenerate on a
	 * given turn.
	 * 
	 * @return Regeneration rate.
	 */
	public int getRegenRate() {
		return regenRate;
	}

	/**
	 * Causes the creature to regenerate health, if this turn is divisible by
	 * the creature's regeneration rate.
	 */
	public void regen() {
		if (Session.turnCount % regenRate == 0)
			hp.current = Math.min(hp.current + regenAmount, hp.max);
	}

	/**
	 * Method that performs all calculations needed when this creature takes a
	 * turn.
	 */
	public void processTurn() {
		regen();
	}

	/**
	 * Sets this creature's name.
	 * 
	 * @param name
	 *            Creature's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns this creature's name.
	 * 
	 * @return Creature's name.
	 */
	public String getName() {
		return name;
	}
	
	
}
