package roguelike.world;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import roguelike.actors.Actor;
import roguelike.actors.Tile;

public abstract class Floor {

	/**
	 * Width of floor.
	 */
	public final int XMAX = 64;

	/**
	 * Height of floor.
	 */
	public final int YMAX = 27;

	/**
	 * All actors on this floor.
	 */
	public ArrayList<Actor> actors;

	/**
	 * All open coordinates on this floor.
	 */
	protected ArrayList<Point> openings;

	/**
	 * Checks to see if there is an actor at the specified location that is not
	 * traversable.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @return True if there is a collision; false otherwise.
	 */
	public boolean checkCollision(int x, int y) {
		for (Actor actor : actors) {
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
	 * Returns actor at the specified coordinate. If there is no actor present,
	 * returns null.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @return Actor at the specified coordinate.
	 */
	public Actor getActorAt(int x, int y) {
		for (Actor actor : actors) {
			if (actor.getX() == x && actor.getY() == y) {
				return actor;
			}
		}
		return null;
	}

	/**
	 * Returns a grid used to draw actors to the screen.
	 * 
	 * @return Grid containing all actors.
	 */
	public Actor[][] getCurrentGrid() {
		Actor[][] actorGrid = new Actor[XMAX][YMAX];
		for (Actor actor : actors) {
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

	/**
	 * Returns a random point containing no non-traversable actors.
	 * 
	 * @return Random eligible point.
	 */
	public Point getRandomOpenTile() {
		openings = new ArrayList<Point>();
		Actor[][] actorGrid = getCurrentGrid();

		for (int x = 0; x < actorGrid.length; x++) {
			for (int y = 0; y < actorGrid[0].length; y++) {
				if (actorGrid[x][y] == null
						|| (actorGrid[x][y].getClass() == Tile.class && actorGrid[x][y]
								.isTraversable()))
					openings.add(new Point(x, y));
			}
		}

		Random r = new Random();
		Point p = openings.get(r.nextInt(openings.size()));
		return p;
	}

	/**
	 * Encloses the current level with walls around the edges to prevent actors
	 * trying to escape.
	 */
	protected void encloseLevel() {
		// Create walls along top and bottom.
		for (int x = 0; x < XMAX; x++) {
			if (getActorAt(x, 0) == null) {
				actors.add(new Tile('#', Color.WHITE, x, 0));
			}
			if (getActorAt(x, YMAX) == null) {
				actors.add(new Tile('#', Color.WHITE, x, YMAX - 1));
			}
		}

		// Create walls along left and right.
		for (int y = 0; y < YMAX; y++) {
			if (getActorAt(0, y) == null) {
				actors.add(new Tile('#', Color.WHITE, 0, y));
			}
			if (getActorAt(XMAX, y) == null) {
				actors.add(new Tile('#', Color.WHITE, XMAX - 1, y));
			}
		}
	}

	/**
	 * Creates a 'floor' for the level with the specified tile's attributes.
	 * 
	 * @param tile
	 *            Tile to take attributes from.
	 */
	protected void fillLevelWithTiles(Tile tile) {
		for (int x = 0; x < XMAX; x++) {
			for (int y = 0; y < YMAX; y++) {
				if (getActorAt(x, y) == null) {
					actors.add(new Tile(tile.getIcon(), tile.getColor(), tile
							.getObscuredColor(), x, y, true));
				}
			}
		}
	}
}
