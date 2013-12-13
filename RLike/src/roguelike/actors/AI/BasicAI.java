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

		if (t.getTurnSeen() == Session.turnCount) { // In LOS
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

	/**
	 * Gets tile (in a one-tile radius) closest to the creature being pursued.
	 * 
	 * @param x
	 *            x-coordinate of pursuer.
	 * @param y
	 *            y-coordinate of pursuer.
	 * @return Point closest to creature being pursued.
	 */
	private Point getClosestTile(int x, int y) {
		int[] dx = { 1, 0, -1, -1, -1, 0, 1, 1 };
		int[] dy = { 1, 1, 1, 0, -1, -1, -1, 0 };

		Tile initial = c.getFloor().getTileAt(c.getX(), c.getY());
		Point result = new Point(0, 0);

		for (int i = 0; i < dx.length; i++) {
			Tile tile = c.getFloor().getTileAt(x + dx[i], y + dy[i]);
			if (tile.getDistance() < initial.getDistance() && tile.isTraversable()) {
				initial = tile;
				result = new Point(dx[i], dy[i]);
			} 
		}

		return result;
	}

}
