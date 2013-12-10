package roguelike.actors;


/**
 * Contains all relevant data for the player's avatar in-game.
 * 
 * @author Dan
 * 
 */
public class Player extends Creature {

	public long turnCount;
	
	public Player(int x, int y) {
		super(x, y);

		turnCount = 0;
		traversable = false;
		
		// TODO Placeholder values
		hp = new Stat(15);
		mp = new Stat(6);
		strength = new Stat(12);
		intelligence = new Stat(11);
		dexterity = new Stat(14);
	}
	
	
}
