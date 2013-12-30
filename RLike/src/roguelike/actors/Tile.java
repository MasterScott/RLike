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
	private double distance;
	private final double BIG_DIST = 10000;

	/**
	 * Creates a tile that is not traversable by default.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 */
	@Deprecated
	public Tile(int x, int y) {
		super(x, y);
		this.traversable = false;
		this.turnSeen = -1;
		this.distance = BIG_DIST;
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
	@Deprecated
	public Tile(int x, int y, boolean traversable) {
		super(x, y);

		this.traversable = traversable;
		this.turnSeen = -1;
		this.distance = BIG_DIST;
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
		this.gf = graphicFile;
		this.row = row;
		this.col = col;
		this.traversable = traversable;
		this.turnSeen = -1;
		this.distance = BIG_DIST;
	}

//	/**
//	 * Creates a new Tile at the given coordinates with the specified image.
//	 * 
//	 * @param x
//	 *            x-coordinate.
//	 * @param y
//	 *            y-coordinate.
//	 * @param traversable
//	 *            Whether or not this tile can be walked over.
//	 * @param Image
//	 *            Image to represent this tile.
//	 */
//	public Tile(int x, int y, boolean traversable, Image image) {
//		super(x, y);
//		setImage(image);
//		this.traversable = traversable;
//		this.turnSeen = -1;
//		this.distance = BIG_DIST;
//	}

	/**
	 * Sets turn this tile was last seen.
	 */
	public void setTurnSeen() {
		turnSeen = Session.turnCount;
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
	 * Loads this tile's image into memory.
	 */
	public void loadImage() {
		if (gf == null) throw new NullPointerException("Image not specified.");
		
		setImage(gf, row, col);
	}
	
	/**
	 * Sets distance from the player when tile was last seen.
	 * 
	 * @param d
	 *            Distance from the player.
	 */
	public void setDistance(double d) {
		distance = d;
	}

	/**
	 * Returns distance from the player when tile was last seen.
	 * 
	 * @return Distance from the player.
	 */
	public double getDistance() {
		return distance;
	}
}
