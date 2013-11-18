package roguelike.world;

import java.util.ArrayList;

import roguelike.actors.Actor;

public abstract class Floor {

	/**
	 * Width of floor.
	 */
	protected final int XMAX = 64;
	
	/**
	 * Height of floor.
	 */
	protected final int YMAX = 27;
	
	/**
	 * All actors on this floor.
	 */
	public ArrayList<Actor> actors;
	
	/**
	 * Checks to see if there is an actor at the specified location that is not traversable.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @return True if there is a collision; false otherwise.
	 */
	public boolean checkCollision(int x, int y) {
		for (Actor actor: actors) {
			if (actor.getX() == x && actor.getY() == y) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Create initial layout for floor.
	 */
	public abstract void generateFloor();
	
	/**
	 * Returns actor at the specified coordinate. If there is no actor present, returns null.
	 * @param x x-coordinate.
	 * @param y y-coordinate.
	 * @return Actor at the specified coordinate.
	 */
	public Actor getActorAt(int x, int y) {
		for (Actor actor: actors) {
			if (actor.getX() == x && actor.getY() == y) {
				return actor;
			}
		}
		return null;
	}
	
	/**
	 * Returns a grid used to draw actors to the screen.
	 * @return Grid containing all actors.
	 */
	public Actor[][] getCurrentGrid() {
		Actor[][] actorGrid = new Actor[XMAX][YMAX];
		for (Actor actor: actors) {
			int x = actor.getX();
			int y = actor.getY();
			if (actorGrid[x][y] == null) {
				actorGrid[x][y] = actor;
			} else {
				if (actorGrid[x][y].isTraversable()) {
					actorGrid[x][y] = actor;
				}
			}
		}
		
		return actorGrid;
	}
}
