package roguelike.actors;

import java.awt.Image;
import java.awt.Point;
import java.util.HashMap;

import roguelike.ui.graphics.Graphic;
import roguelike.ui.graphics.Graphic.GraphicFile;
import roguelike.ui.los.LOS;
import roguelike.world.Floor;

/**
 * Base class for object being displayed on-screen.
 * 
 * @author Dan
 * 
 */
public abstract class Actor {

	/**
	 * Static map that contains all previously loaded images. Instead of
	 * recreating an image that was already loaded into memory, actors can check
	 * the map to see if said image was already loaded in, and if so, pull from
	 * this map.
	 */
	static HashMap<String, Image> images = new HashMap<String, Image>();
	
	int x, y;
	boolean traversable;
	boolean previouslySeen;
	Floor floor;
	Image image;

	public Actor(int x, int y) {
		this.x = x;
		this.y = y;
		this.previouslySeen = false;
	}

	/**
	 * Returns x-coordinate of this actor.
	 * 
	 * @return x-coordinate of this actor.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets x-coordinate of this actor.
	 * 
	 * @param x
	 *            x-coordinate of this actor.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns y-coordinate of this actor.
	 * 
	 * @return y-coordinate of this actor.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets y-coordinate of this actor.
	 * 
	 * @param y
	 *            y-coordinate of this actor.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Returns a point representing x and y-coordinates of this actor.
	 * 
	 * @return Point representing x and y-coordinates of this actor.
	 */
	public Point getCoords() {
		return new Point(this.x, this.y);
	}

	/**
	 * Sets x and y-coordinates of this actor.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 */
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns whether or not this actor is able to be 'walked' over.
	 * 
	 * @return True if traversable; false otherwise.
	 */
	public boolean isTraversable() {
		return traversable;
	}

	/**
	 * Sets whether or not this actor is able to be 'walked' over.
	 * 
	 * @param traversable
	 *            True if traversable; false otherwise.
	 */
	public void setTraversable(boolean traversable) {
		this.traversable = traversable;
	}

	/**
	 * Returns floor this actor is located on.
	 * 
	 * @return Floor this actor is located on.
	 */
	public Floor getFloor() {
		return floor;
	}

	/**
	 * Sets floor this actor is located on.
	 * 
	 * @param floor
	 *            Floor to set.
	 */
	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	/**
	 * Returns image used to represent this actor.
	 * 
	 * @return Image used to represent this actor.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Sets image to the icon at the row and column in the specified tileset.
	 * 
	 * @param gf
	 *            Tileset to use.
	 * @param row
	 *            Row of icon to use.
	 * @param col
	 *            Column of icon to use.
	 */
	public void setImage(GraphicFile gf, int row, int col) {
		this.image = Graphic.getImage(gf, row, col);
	}

	/**
	 * Sets image used to represent this actor to the image specified.
	 * 
	 * @param image
	 *            Image to represent this actor.
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Returns whether or not this actor has been seen by the player.
	 * 
	 * @return Whether or not this actor has been seen by the player.
	 */
	public boolean getPreviouslySeen() {
		return previouslySeen;
	}

	/**
	 * Sets whether or not this actor has been previously seen by the player.
	 * 
	 * @param previouslySeen
	 *            True if previously seen; false otherwise.
	 */
	public void setPreviouslySeen(boolean previouslySeen) {
		this.previouslySeen = previouslySeen;
	}

	/**
	 * Returns line of sight object for the actor.
	 * 
	 * @return Actor's line of sight object.
	 */
	public LOS getLOS() {
		return new LOS(this);
	}

}
