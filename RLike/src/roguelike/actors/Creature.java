package roguelike.actors;

import java.awt.Color;

/**
 * Contains all relevant data for a creature - any object that can perform
 * actions like a player can but is controlled by AI rather than by a user.
 * 
 * @author Dan
 * 
 */
public class Creature extends Actor {

	public Stat hp, mp, strength, intelligence, dexterity;
	
	public Creature(char icon, Color color, int x, int y) {
		super(icon, color, x, y);
		traversable = false;
	}

}
