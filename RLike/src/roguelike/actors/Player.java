package roguelike.actors;

import java.awt.Color;

/**
 * Contains all relevant data for the player's avatar in-game.
 * 
 * @author Dan
 * 
 */
public class Player extends Creature {

	public Stat hp, mp, strength, intelligence, dexterity;

	public Player(char icon, Color color, int x, int y) {
		super(icon, color, x, y);

		traversable = false;
		
		// TODO Placeholder values
		hp = new Stat(15);
		mp = new Stat(6);
		strength = new Stat(12);
		intelligence = new Stat(11);
		dexterity = new Stat(14);
	}
	
	
}
