package roguelike.actors.classes;

import roguelike.actors.actions.Skill;

/**
 * Basic interface for any class that can be assigned to a creature.
 * 
 * @author Dan
 * 
 */
public interface RLClass {

	/**
	 * Returns an integer representing the amount of bonus strength this actor
	 * gains per level.
	 * 
	 * @return Bonus strength gained per level.
	 */
	public int getBonusStr();

	/**
	 * Returns an integer representing the amount of bonus dexterity this actor
	 * gains per level.
	 * 
	 * @return Bonus dexterity gained per level.
	 */
	public int getBonusDex();

	/**
	 * Returns an integer representing the amount of bonus intelligence this
	 * actor gains per level.
	 * 
	 * @return Bonus intelligence gained per level.
	 */
	public int getBonusInt();

	/**
	 * Returns an integer representing the amount of bonus HP this actor gains
	 * per level.
	 * 
	 * @return Bonus HP gained per level.
	 */
	public int getBonusHP();

	/**
	 * Returns all skills available to this class.
	 * 
	 * @return Skills available to this class.
	 */
	public Skill[] getSkills();

	/**
	 * Returns display name of this class.
	 * 
	 * @return Name of class.
	 */
	public String getName();

}
