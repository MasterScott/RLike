package roguelike.actors.AI;

import roguelike.actors.Creature;
import roguelike.actors.Tile;
import roguelike.etc.Session;

/**
 * Basic artificial intelligence. Will move toward and attack hostiles when in
 * range but will otherwise do nothing interesting.
 * 
 * @author Dan
 * 
 */
public class BasicAI extends AI {

	/**
	 * Creates a new basic artificial intelligence for the given creature.
	 * 
	 * @param c
	 *            Creature to give AI to.
	 */
	public BasicAI(Creature c) {
		super(c);
	}

	@Override
	public void doPrioritizedAction() {
		Tile t = c.getFloor().getTileAt(c.getX(), c.getY());
		
		if (t.getTurnSeen() == Session.player.turnCount) { // In LOS
			
		}
		
	}

}
