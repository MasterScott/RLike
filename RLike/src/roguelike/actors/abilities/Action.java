package roguelike.actors.abilities;

import roguelike.actors.Actor;

/**
 * Basic interface for any action taken by an actor.
 * 
 * @author Dan
 * 
 */
public interface Action {

	/**
	 * Performs an action to the given recipient.
	 * 
	 * @param recipient
	 *            Actor to perform action on.
	 */
	public void doAction();

	/**
	 * Sets actor to perform this action on.
	 * 
	 * @param recipient
	 *            Actor to perform action on.
	 */
	public void setRecipient(Actor recipient);

	/**
	 * Returns a string containing the name of this action.
	 * 
	 * @return Name of this action.
	 */
	public String getName();

	/**
	 * Returns a string containing the action text.
	 * 
	 * @return Action text for this action.
	 */
	public String getActionText();
}
