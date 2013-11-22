package roguelike.actors;

import java.awt.Color;

/**
 * Actor representing a background tile - a floor, wall, door, etc.
 * 
 * @author Dan
 * 
 */
public class Tile extends Actor {

	private Color obscuredColor;

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

		this.traversable = false;
		this.obscuredColor = new Color((int) (color.getRed() / 2),
				(int) (color.getGreen() / 2), (int) (color.getBlue() / 2));
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
	 * @param traversable
	 *            Whether or not this tile can be walked over.
	 */
	public Tile(char icon, Color color, int x, int y, boolean traversable) {
		super(icon, color, x, y);

		this.traversable = traversable;
		this.obscuredColor = new Color((int) (color.getRed() / 2),
				(int) (color.getGreen() / 2), (int) (color.getBlue() / 2));
	}

	/**
	 * Returns color when tile is not visible.
	 * 
	 * @return Color when tile is not visible.
	 */
	public Color getObscuredColor() {
		return obscuredColor;
	}

	/**
	 * Sets color when tile is not visible.
	 * 
	 * @param color
	 *            Color to set.
	 */
	public void setObscuredColor(Color color) {
		obscuredColor = color;
	}
}
