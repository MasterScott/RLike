package roguelike.actors.AI;

import java.awt.Point;

import roguelike.actors.Creature;
import roguelike.actors.Tile;

/**
 * Artificial intelligence for creatures.
 * 
 * @author Dan
 * 
 */
public abstract class AI {

	protected Creature c;

	/**
	 * Creates a new artificial intelligence for the given creature.
	 */
	public AI() {

	}

	/**
	 * Performs this creature's highest prioritized action.
	 */
	public abstract void doPrioritizedAction();

	/**
	 * Returns a random chance of one out of the given number. i.e. if the
	 * number is twenty, the method would return true one out of every twenty
	 * times.
	 * 
	 * @param num
	 *            Number to base chance on.
	 * @return True if one; otherwise false.
	 */
	boolean chanceOfOneIn(int num) {
		int i = (int) Math.random() * num;
		return i == 0;
	}

	/**
	 * Moves in the specified direction.
	 * 
	 * @param dx
	 *            x-offset.
	 * @param dy
	 *            y-offset.
	 */
	public void moveToward(int dx, int dy) {
		int x = c.getX();
		int y = c.getY();

		c.setCoords(x + dx, y + dy);
	}

	/**
	 * Sets host for this AI.
	 * 
	 * @param c
	 *            Creature to utilize AI.
	 */
	public void setHost(Creature c) {
		this.c = c;
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
	protected Point getClosestTile(int x, int y) {
		int[] dx = { 1, 0, -1, -1, -1, 0, 1, 1 };
		int[] dy = { 1, 1, 1, 0, -1, -1, -1, 0 };

		Tile initial = c.getFloor().getTileAt(c.getX(), c.getY());
		Point result = new Point(0, 0);

		for (int i = 0; i < dx.length; i++) {
			Tile tile = c.getFloor().getTileAt(x + dx[i], y + dy[i]);
			if ((tile.getDistance() < initial.getDistance() || tile.getTurnSeen() > initial.getTurnSeen() )&& tile.isTraversable() ) {
				initial = tile;
				result = new Point(dx[i], dy[i]);
			} 
		}

		return result;
	}

}
