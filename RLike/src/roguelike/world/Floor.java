package roguelike.world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import roguelike.actors.Actor;
import roguelike.actors.Creature;
import roguelike.actors.Feature;
import roguelike.actors.Feature.FeatureType;
import roguelike.actors.util.CreatureTemplate;
import roguelike.actors.Player;
import roguelike.actors.Tile;
import roguelike.etc.Session;
import roguelike.etc.random.ObjectLists;
import roguelike.etc.random.RandomGenerator;
import roguelike.etc.random.WeightedRandom;
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
	 * The level of this floor (in terms of depth).
	 */
	protected int depth;

	/**
	 * Coordinates of the stairs leading downward on this floor.
	 */
	protected Feature downstairs;

	/**
	 * Coordinates of the stairs leading upward on this floor.
	 */
	protected Feature upstairs;

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
			if (actor.getX() == x && actor.getY() == y && !actor.isTraversable()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Create initial layout for floor.
	 */
	public void generateFloor() {
		if (Session.floors.isEmpty()) {
			this.depth = 1;
		} else {
			this.depth = Session.floors.size() + 1;
		}

		Session.floors.add(this);
	}

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
	 * Returns a random point accessible by the origin parameter.
	 * 
	 * @param origin
	 *            Starting point to determine accessibility.
	 * @return Random accessible point.
	 */
	public Point getRandomAccessibleTile(Point origin) {
		// Need to clear list.
		accessibleTiles = new ArrayList<Tile>();

		getAccessibleArea(origin.x, origin.y);

		Random r = new Random();
		Tile t = accessibleTiles.get(r.nextInt(accessibleTiles.size()));
		int x = t.getX();
		int y = t.getY();

		return new Point(x, y);
	}

	/**
	 * Encloses the current level with walls around the edges to prevent actors
	 * trying to escape.
	 * 
	 * @param tileset
	 *            Tileset this tile's icon is located on.
	 * @param row
	 *            Row of tileset icon is located on.
	 * @param col
	 *            Column of tileset icon is located on.
	 */
	protected void encloseLevel(GraphicFile tileset, int row, int col) {

		// Create walls along top and bottom.
		for (int x = 0; x < XMAX; x++) {
			if (getActorAt(x, 0) == null) {
				Tile t = new Tile(x, 0, false, tileset, row, col);
				actors.add(t);
			}
			if (getActorAt(x, YMAX) == null) {
				Tile t = new Tile(x, YMAX - 1, false, tileset, row, col);
				actors.add(t);
			}
		}

		// Create walls along left and right.
		for (int y = 0; y < YMAX; y++) {
			if (getActorAt(0, y) == null) {
				Tile t = new Tile(0, y, false, tileset, row, col);
				actors.add(t);
			}
			if (getActorAt(XMAX, y) == null) {
				Tile t = new Tile(XMAX - 1, y, false, tileset, row, col);
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
	 * @param tileset
	 *            Tileset this tile's icon is located on.
	 * @param row
	 *            Row of tileset icon is located on.
	 * @param col
	 *            Column of tileset icon is located on.
	 */
	public void createStairs(FeatureType featureType, GraphicFile tileset, int row, int col) {
		Point p = getRandomOpenTile();

		Tile t = getTileAt(p.x, p.y);
		actors.remove(t);

		Feature f = new Feature(p.x, p.y, true, tileset, row, col, featureType);
		
		if (f.getFeatureType() == FeatureType.DOWNSTAIRS) {
			downstairs = f;
		} else if (f.getFeatureType() == FeatureType.UPSTAIRS) {
			upstairs = f;
		}
		
		actors.add(f);
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
	 * @param tileset
	 *            Tileset this tile's icon is located on.
	 * @param row
	 *            Row of tileset icon is located on.
	 * @param col
	 *            Column of tileset icon is located on.
	 */
	public void createStairs(int x, int y, FeatureType featureType, GraphicFile tileset, int row, int col) {
		Feature f = new Feature(x, y, true, tileset, row, col, featureType);

		Tile t = getTileAt(x, y);
		actors.remove(t);

		if (f.getFeatureType() == FeatureType.DOWNSTAIRS) {
			downstairs = f;
		} else if (f.getFeatureType() == FeatureType.UPSTAIRS) {
			upstairs = f;
		}
		
		actors.add(f);
		System.out.println("Stairs at: " + x + " " + y);
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
	public void createAccessibleStairs(Player player, FeatureType featureType, GraphicFile tileset, int row, int col) {
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

		Feature f = new Feature(x, y, true, tileset, row, col, FeatureType.UPSTAIRS);
		f.setImage(tileset, row, col);

		if (f.getFeatureType() == FeatureType.DOWNSTAIRS) {
			downstairs = f;
		} else if (f.getFeatureType() == FeatureType.UPSTAIRS) {
			upstairs = f;
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
			// 7 ms with just NSWE, 15 ms with diagonals + NSWE.
			getAccessibleArea(x + 1, y);
			getAccessibleArea(x - 1, y);
			getAccessibleArea(x, y + 1);
			getAccessibleArea(x, y - 1);
			getAccessibleArea(x + 1, y + 1);
			getAccessibleArea(x + 1, y - 1);
			getAccessibleArea(x - 1, y + 1);
			getAccessibleArea(x - 1, y - 1);
		}
	}

	/**
	 * Files the entire floor with the specified tile.
	 * 
	 * @param tileset
	 *            Tileset this tile's icon is located on.
	 * @param row
	 *            Row of tileset icon is located on.
	 * @param col
	 *            Column of tileset icon is located on.
	 * @param traversable
	 *            Whether or not these tiles should be traversable by default.
	 */
	protected void fillLevelWithTiles(GraphicFile tileset, int row, int col, boolean traversable) {

		for (int x = 0; x < XMAX; x++) {
			for (int y = 0; y < YMAX; y++) {
				if (getActorAt(x, y) == null) {
					Tile t = new Tile(x, y, traversable, tileset, row, col);
					actors.add(t);
				}
			}
		}
	}

	/**
	 * Returns the stairs leading to the next floor.
	 * 
	 * @return Stairs leading to the next floor.
	 */
	public Feature getDownstairs() {
		return downstairs;
	}

	/**
	 * Returns the stairs leading to the previous floor.
	 * 
	 * @return Stairs leading to the previous floor.
	 */
	public Feature getUpstairs() {
		return upstairs;
	}

	/**
	 * Returns the depth of this floor.
	 * 
	 * @return Depth of this floor.
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Sets the depth of this floor.
	 * 
	 * @param depth
	 *            Depth of this floor.
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * Populates this floor with creatures of the appropriate level.
	 */
	public void populateWithCreatures() {
		if (depth < 1)
			throw new NullPointerException("Depth was never specified for this floor.");

		RandomGenerator random = new RandomGenerator();
		int num = (int) (Math.random() * 8); // TODO Better range for # of
												// creatures.
		for (int i = 0; i < 8; i++) {
			Point ps = getRandomOpenTile();
			WeightedRandom choice = random.generate(ObjectLists.Creatures.values(), this.depth);

			Creature c = Creature.constructCreature(CreatureTemplate.valueOf(choice.getName()));
			c.setCoords(ps.x, ps.y);
			c.setFloor(this);
			actors.add(c);
		}
	}

	/**
	 * Returns the tile used for the floor tile of this level.
	 * 
	 * @param x
	 *            X-coordinate of this tile.
	 * @param y
	 *            Y-coordinate of this tile.
	 * 
	 * @return Tile used for the floor tile of this level.
	 */
	public abstract Tile getFloorTile(int x, int y);

}
