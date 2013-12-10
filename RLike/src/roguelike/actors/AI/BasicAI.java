package roguelike.actors.AI;

import java.awt.Point;

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
			Point closest = getClosestTile(c.getX(), c.getY());

			int x = c.getX();
			int y = c.getY();
			if (!c.getFloor().checkCollision(x + closest.x, y + closest.y)) {
				c.setCoords(x + closest.x, y + closest.y);
				System.out.println("Creature coords: x: " + c.getX() + " y: " + c.getY());
			}
				
		}

	}

	private Point getClosestTile(int x, int y) {
		int[] dx = { 1, 0, -1, -1, -1, 0, 1, 1 };
		int[] dy = { 1, 1, 1, 0, -1, -1, -1, 0 };

		Tile initial = c.getFloor().getTileAt(c.getX(), c.getY());
		Point result = new Point(0, 0);

		for (int i = 0; i < dx.length; i++) {
			Tile tile = c.getFloor().getTileAt(x + dx[i], y + dy[i]);
			if (tile.getDistance() < initial.getDistance()) {
				initial = tile;
				result = new Point(dx[i], dy[i]);
			}
		}

		return result;
	}

}
