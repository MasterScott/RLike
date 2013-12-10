package roguelike.actors;

import java.awt.Image;

import roguelike.etc.Session;
import roguelike.ui.graphics.Graphic.GraphicFile;

/**
 * Actor representing a background tile - a floor, wall, door, etc.
 * 
 * @author Dan
 * 
 */
public class Tile extends Actor {

	private long turnSeen;
	private int distance;

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
		this.turnSeen = -1;
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
		this.turnSeen = -1;
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
		this.turnSeen = -1;
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
		this.turnSeen = -1;
	}

	/**
	 * Sets turn this tile was last seen.
	 */
	public void setTurnSeen() {
		turnSeen = Session.player.turnCount;
	}

	/**
	 * Returns turn this tile was last seen by the player.
	 * 
	 * @return
	 */
	public long getTurnSeen() {
		return turnSeen;
	}

	/**
	 * Sets distance from the player when tile was last seen.
	 * 
	 * @param d
	 *            Distance from the player.
	 */
	public void setDistance(int d) {
		distance = d;
	}

	/**
	 * Returns distance from the player when tile was last seen.
	 * 
	 * @return Distance from the player.
	 */
	public int getDistance() {
		return distance;
	}
}
