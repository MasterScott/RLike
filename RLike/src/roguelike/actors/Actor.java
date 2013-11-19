package roguelike.actors;

import java.awt.Color;

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

	public String toString() {
		return "Character: " + icon + " Color: " + color.toString() + " x: "
				+ x + " y: " + y;
	}
}
