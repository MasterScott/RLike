package roguelike.actors;

import java.awt.Color;
import java.awt.Image;

import roguelike.ui.graphics.Graphic;
import roguelike.ui.graphics.Graphic.GraphicFile;
import roguelike.world.Floor;

/**
 * Base class for object being displayed on-screen.
 * 
 * @author Dan
 * 
 */
public abstract class Actor {

	char icon;
	Color color;
	int x, y;
	boolean traversable;
	boolean previouslySeen;
	Floor floor;
	Image image;

	public Actor(char icon, Color color, int x, int y) {
		this.icon = icon;
		this.color = color;
		this.x = x;
		this.y = y;
		this.previouslySeen = false;
	}

	/**
	 * Returns icon used to represent this actor.
	 * 
	 * @return Icon used to represent this actor.
	 */
	public char getIcon() {
		return icon;
	}

	/**
	 * Sets icon used to represent this actor.
	 * 
	 * @param icon
	 *            Icon to represent this actor.
	 */
	public void setIcon(char icon) {
		this.icon = icon;
	}

	/**
	 * Returns color of this actor's icon.
	 * 
	 * @return Color of this actor's icon.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets color of this actor's icon.
	 * 
	 * @param color
	 *            Color of this actor's icon.
	 */
	public void setColor(Color color) {
		this.color = color;
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
		this.image = Graphic.getImage(gf.fileName, col, row);
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

}
