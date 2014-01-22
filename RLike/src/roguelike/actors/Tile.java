package roguelike.actors;

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
	 * @param index
	 * 			  Index of icon to use.
	 */
	public Tile(int x, int y, boolean traversable, GraphicFile graphicFile, int index) {
		super(x, y);
		this.gf = graphicFile;
		this.index = index;
		this.traversable = traversable;
		this.turnSeen = -1;
		this.distance = BIG_DIST;
	}

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
		
		setImage(gf, index);
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
