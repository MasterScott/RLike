package roguelike.actors;

import roguelike.actors.AI.AI;
import roguelike.actors.abilities.ActionList;
import roguelike.actors.classes.Classes;
import roguelike.actors.classes.RLClass;
import roguelike.actors.util.CreatureTemplate;
import roguelike.actors.util.Stat;
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

	int regenRate = DEFAULT_REGEN_RATE;
	int regenAmount = DEFAULT_REGEN_AMOUNT;
	RLClass c;
	AI ai;

	private boolean hostile;
	private long expGiven;

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
		this.name = name;
		traversable = false;
		hostile = true;
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
	 * @param index
	 *            Index of icon to use.
	 */
	public Creature(int x, int y, String name, GraphicFile graphicFile, int index) {
		this(x, y, name);
		setImage(graphicFile, index);
	}

	/**
	 * Constructs a pre-specified creature. To be used with the CreatureTemplate
	 * enum in the Actor utilities package.
	 * 
	 * @param type
	 *            CreatureTemplate type.
	 * @return A new creature of the specified CreatureTemplate type.
	 */
	public static Creature constructCreature(CreatureTemplate type) {
		Creature c = new Creature(0, 0, type.name, type.gf, type.index);
		c.hp = new Stat(type.hp);
		c.mp = new Stat(type.mp);
		c.strength = new Stat(type.str);
		c.intelligence = new Stat(type.intl);
		c.dexterity = new Stat(type.dex);
		c.setHostile(c.hostile);
		c.setExpGiven(type.xp);
		c.setCreatureClass(type.c);

		// Have to set AI here rather than in the enumerated type, so all
		// creatures don't share the same AI.
		try {
			c.setAI((AI) type.ai.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return c;
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
	 * Returns AI of creature.
	 * 
	 * @return AI of creature.
	 */
	public AI getAI() {
		return ai;
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
	 * Returns amount of experience this creature gives upon death.
	 * 
	 * @return Amount of experience given.
	 */
	public long getExpGiven() {
		return expGiven;
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
	 * Returns whether or not this creature is hostile to the player.
	 * 
	 * @return Whether or not this creature is hostile to the player.
	 */
	public boolean isHostile() {
		return hostile;
	}

	/**
	 * Performs a melee attack against the specified creature.
	 * 
	 * @param recipient
	 *            Creature to perform melee attack against.
	 * @return True if attack was successful; false otherwise.
	 */
	public boolean meleeAttack(Creature recipient) {
		ActionList.MELEE_ATTACK.doAction(this, recipient);
		return true;
	}

	/**
	 * Method that performs all calculations needed when this creature takes a
	 * turn.
	 */
	public void processTurn() {
		regen();
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
	 * Sets the class of this creature.
	 * 
	 * @param c
	 *            Class of this creature.
	 */
	public void setCreatureClass(Classes c) {
		this.c = c;
	}

	/**
	 * Sets amount of experience this creature gives upon death.
	 * 
	 * @param expGiven
	 *            Amount of experience given.
	 */
	public void setExpGiven(long expGiven) {
		this.expGiven = expGiven;
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
	 * Sets creature's natural regen to happen one out of every x turns, where x
	 * is specified by rate.
	 * 
	 * @param rate
	 *            Regeneration rate.
	 */
	public void setRegenRate(int rate) {
		this.regenRate = rate;
	}
}
