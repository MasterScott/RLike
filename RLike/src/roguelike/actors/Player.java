package roguelike.actors;

import java.awt.Color;

import roguelike.ui.los.LOS;

/**
 * Contains all relevant data for the player's avatar in-game.
 * 
 * @author Dan
 * 
 */
public class Player extends Actor {

	public Stat hp, mp, strength, intelligence, dexterity;
	private LOS los;

	public Player(char icon, Color color, int x, int y) {
		super(icon, color, x, y);

		traversable = false;
		los = new LOS(this);
		
		// TODO Placeholder values
		hp = new Stat(15);
		mp = new Stat(6);
		strength = new Stat(12);
		intelligence = new Stat(11);
		dexterity = new Stat(14);
	}
	
	/**
	 * Returns line of sight object for the player.
	 * @return Player's line of sight object.
	 */
	public LOS getLOS() {
		return los;
	}
}
