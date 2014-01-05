package roguelike.actors.AI;

import java.awt.Point;

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
	 */
	public BasicAI() {
		super();
	}

	@Override
	public void doPrioritizedAction() {
		Tile t = c.getFloor().getTileAt(c.getX(), c.getY());

		if (t.getTurnSeen() >= Session.turnCount - 2) { // In LOS or was just in LOS
			Point closest = getClosestTile(c.getX(), c.getY());

			int x = c.getX();
			int y = c.getY();
			if (x + closest.x == Session.player.getX() && y + closest.y == Session.player.getY()) {
				c.meleeAttack(Session.player);
			} else if (!c.getFloor().checkCollision(x + closest.x, y + closest.y)) {
				moveToward(closest.x, closest.y);
			}

		}

	}


}
