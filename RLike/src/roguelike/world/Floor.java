package roguelike.world;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import roguelike.actors.Actor;
import roguelike.actors.Creature;
import roguelike.actors.Feature;
import roguelike.actors.Feature.FeatureType;
import roguelike.actors.Player;
import roguelike.actors.Tile;
import roguelike.ui.graphics.Graphic;
import roguelike.ui.graphics.Graphic.GraphicFile;

public abstract class Floor {

	/**
	 * Width of floor.
	 */
	public final int XMAX = 42;

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
	 * All tiles accessible to the player. Only populated upon calling
	 * getAccessableArea().
	 */
	private ArrayList<Tile> accessibleTiles;

	/**
	 * All floors that can be accessed from this floor.
	 */
	public ArrayList<Floor> connectedFloors;
	
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
		// TODO Need to account for more than one actor on the same tile.
		for (Actor actor : actors) {
			if (actor.getX() == x && actor.getY() == y) {
				return actor;
			}
		}
		return null;
	}

	/**
	 * Returns creature at the specified coordinate. If there is no creature
	 * present, returns null.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @return Creature at the specified coordinate.
	 */
	public Creature getCreatureAt(int x, int y) {
		for (Actor actor : actors) {
			if (actor.getX() == x && actor.getY() == y && actor instanceof Creature) {
				return (Creature) actor;
			}
		}
		return null;
	}

	/**
	 * Returns tile at the specified coordinate. If there is no tile present,
	 * returns null.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @return Tile at the specified coordinate.
	 */
	public Tile getTileAt(int x, int y) {
		for (Actor actor : actors) {
			if (actor.getX() == x && actor.getY() == y && (actor instanceof Tile)) {
				return (Tile) actor;
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
						|| (actorGrid[x][y].getClass() == Tile.class && actorGrid[x][y].isTraversable()))
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
	 *
	 * @param tileset Tileset this tile's icon is located on.
	 * @param row Row of tileset icon is located on.
	 * @param col Column of tileset icon is located on.
	 */
	protected void encloseLevel(GraphicFile tileset, int row, int col) { 
		Image img = Graphic.getImage(tileset.fileName, col, row);
		
		// Create walls along top and bottom.
		for (int x = 0; x < XMAX; x++) {
			if (getActorAt(x, 0) == null) {
				Tile t = new Tile('#', Color.WHITE, x, 0);
				t.setImage(img);
				actors.add(t);
			}
			if (getActorAt(x, YMAX) == null) {
				Tile t = new Tile('#', Color.WHITE, x, YMAX - 1);
				t.setImage(img);
				actors.add(t);
			}
		}

		// Create walls along left and right.
		for (int y = 0; y < YMAX; y++) {
			if (getActorAt(0, y) == null) {
				Tile t = new Tile('#', Color.WHITE, 0, y);
				t.setImage(img);
				actors.add(t);
			}
			if (getActorAt(XMAX, y) == null) {
				Tile t = new Tile('#', Color.WHITE, XMAX - 1, y);
				t.setImage(img);
				actors.add(t);
			}
		}
	}

	/**
	 * Create a staircase of the specified type at a random location.
	 * 
	 * @param featureType
	 *            Either UPSTAIRS or DOWNSTAIRS from the Feature.FeatureType
	 *            enumeration.
	 */
	protected void createStairs(FeatureType featureType) {
		// TODO Make sure this occurs at a spot the player can reach.
		Point p = getRandomOpenTile();

		if (featureType == FeatureType.DOWNSTAIRS) {
			actors.add(new Feature('>', Color.WHITE, p.x, p.y, true, FeatureType.DOWNSTAIRS));
		} else if (featureType == FeatureType.UPSTAIRS) {
			actors.add(new Feature('<', Color.WHITE, p.x, p.y, true, FeatureType.UPSTAIRS));
		}

	}

	/**
	 * Create a staircase of the specified type at a specified location.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @param featureType
	 *            Either UPSTAIRS or DOWNSTAIRS from the Feature.FeatureType
	 *            enumeration.
	 */
	protected void createStairs(int x, int y, FeatureType featureType) {
		if (featureType == FeatureType.DOWNSTAIRS) {
			actors.add(new Feature('>', Color.WHITE, x, y, true, FeatureType.DOWNSTAIRS));
		} else if (featureType == FeatureType.UPSTAIRS) {
			actors.add(new Feature('<', Color.WHITE, x, y, true, FeatureType.UPSTAIRS));
		}
	}

	/**
	 * Create stairs that are reachable by the specified player.
	 * 
	 * @param player
	 *            Player the stairs should be reachable by.
	 * @param featureType
	 *            Either UPSTAIRS or DOWNSTAIRS from the Feature.FeatureType
	 *            enumeration.
	 */
	public void createAccessibleStairs(Player player, FeatureType featureType) {
		/*
		 * Have to clear list in order for the recursive getAccessibleArea() to
		 * work.
		 */
		accessibleTiles = new ArrayList<Tile>();

		int x = player.getX();
		int y = player.getY();

		getAccessibleArea(x, y);

		Random r = new Random();
		Tile t = accessibleTiles.get(r.nextInt(accessibleTiles.size()));
		x = t.getX();
		y = t.getY();
		actors.remove(t);

		if (featureType == FeatureType.DOWNSTAIRS) {
			actors.add(new Feature('>', Color.WHITE, x, y, true, FeatureType.DOWNSTAIRS));
			System.out.println("Downstairs added at x: " + x + " y: " + y);
		} else if (featureType == FeatureType.UPSTAIRS) {
			actors.add(new Feature('<', Color.WHITE, x, y, true, FeatureType.UPSTAIRS));
			System.out.println("Upstairs added at x: " + x + " y: " + y);
		} else {
			System.out.println("Failure to add.");
		}

	}

	/**
	 * Adds all tiles accessible from the specified location to the
	 * accessibleTiles ArrayList via a recursive algorithm.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 */
	private void getAccessibleArea(int x, int y) {
		Tile t = getTileAt(x, y);

		if (t == null || accessibleTiles.contains(t))
			return;

		if (t.isTraversable()) {
			accessibleTiles.add(t);
			getAccessibleArea(x + 1, y);
			getAccessibleArea(x - 1, y);
			getAccessibleArea(x, y + 1);
			getAccessibleArea(x, y - 1);
		}
	}


	protected void fillLevelWithTiles(GraphicFile tileset, int row, int col) {
		Image img = Graphic.getImage(tileset.fileName, col, row);
		
		for (int x = 0; x < XMAX; x++) {
			for (int y = 0; y < YMAX; y++) {
				if (getActorAt(x, y) == null) {
					Tile t = new Tile('.', Color.WHITE, x, y, true);
					t.setImage(img);
					actors.add(t);
				}
			}
		}
	}
}
