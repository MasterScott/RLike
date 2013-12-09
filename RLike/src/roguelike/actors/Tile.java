package roguelike.actors;

import java.awt.Image;

import roguelike.ui.graphics.Graphic.GraphicFile;

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
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 */
	public Tile(int x, int y) {
		super(x, y);
		this.traversable = false;
	}

	/**
	 * Creates a tile with the specified traversability.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @param traversable
	 *            Whether or not this tile can be walked over.
	 */
	public Tile(int x, int y, boolean traversable) {
		super(x, y);

		this.traversable = traversable;
	}

	/**
	 * Creates a new Tile at the given coordinates with the specified image.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @param traversable
	 *            Whether or not this tile can be walked over.
	 * @param graphicFile
	 *            Tileset to use.
	 * @param row
	 *            Row of icon to use.
	 * @param col
	 *            Column of icon to use.
	 */
	public Tile(int x, int y, boolean traversable, GraphicFile graphicFile, int row, int col) {
		super(x, y);
		setImage(graphicFile, row, col);
		this.traversable = traversable;
	}

	/**
	 * Creates a new Tile at the given coordinates with the specified image.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @param traversable
	 *            Whether or not this tile can be walked over.
	 * @param Image
	 *            Image to represent this tile.
	 */
	public Tile(int x, int y, boolean traversable, Image image) {
		super(x, y);
		setImage(image);
		this.traversable = traversable;
	}
}
