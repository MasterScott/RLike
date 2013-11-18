package roguelike.actors;

import java.awt.Color;

/**
 * Actor representing a background tile - a floor, wall, door, etc.
 * 
 * @author Dan
 * 
 */
public class Tile extends Actor {

	/**
	 * Creates a tile that is not traversable by default.
	 * 
	 * @param icon
	 *            Icon used to represent this tile.
	 * @param color
	 *            Color of icon used to represent this tile.
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 */
	public Tile(char icon, Color color, int x, int y) {
		super(icon, color, x, y);

		traversable = false;
	}

	/**
	 * Creates a tile with the specified traversability.
	 * 
	 * @param icon
	 *            Icon used to represent this tile.
	 * @param color
	 *            Color of icon used to represent this tile.
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 */
	public Tile(char icon, Color color, int x, int y, boolean traversable) {
		super(icon, color, x, y);

		this.traversable = traversable;
	}

}
