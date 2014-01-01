package roguelike.world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import roguelike.actors.Actor;
import roguelike.actors.Tile;
import roguelike.etc.RLUtilities;
import roguelike.ui.graphics.Graphic.GraphicFile;

public class BaseDungeon extends Floor {

	private ArrayList<Room> rooms;
	private Room cursorRoom;
	private final double TOLERANCE = 0.65;
	private Random rand;

	public BaseDungeon() {
		actors = new ArrayList<Actor>();
		rooms = new ArrayList<Room>();

		// ADD BOUNDARY ROOMS
		rand = new Random();
	}

	/**
	 * Creates a new room beginning at the top left coordinates (specified by
	 * the x and y variables) and spanning the given width and height. Checks
	 * for overlap and returns false if an intersection is found.
	 * 
	 * @param x
	 *            Leftmost x-coordinate.
	 * @param y
	 *            Topmost y-coordinate.
	 * @param width
	 *            Width of room.
	 * @param height
	 *            Height of room.
	 * @param isPassageway
	 *            Whether or not this room is a single-tile wide passageway.
	 * @return True if room creation was successful; false otherwise.
	 */
	private boolean createRoom(int x, int y, int width, int height, boolean isPassageway) {
		Room r = new Room(x, y, width, height, isPassageway);

		// Don't try to create if an intersection is found.
		if (isIntersecting(r))
			return false;

		/*
		 * The outer edges are walls of the rooms/passageways, so don't clear
		 * tiles for those.
		 */
		for (int i = x + 1; i < x + width - 1; i++) {
			for (int j = y + 1; j < y + height - 1; j++) {
				Tile t = getTileAt(i, j);
				actors.remove(t);
				t = new Tile(i, j, true, GraphicFile.DUNGEON, 6, 3);
				actors.add(t);
			}
		}

		rooms.add(r);
		// Debug
		System.out.println(r.toString());
		return true;
	}

	@Override
	public void generateFloor() {
		super.generateFloor();
		// TODO Change to false after done debugging.
		fillLevelWithTiles(GraphicFile.DUNGEON, 0, 0, true);

		createRoom(RLUtilities.getRandom(15, 25), RLUtilities.getRandom(8, 14), RLUtilities.getRandom(5, 8),
				RLUtilities.getRandom(5, 8), false);

		boolean isPassageway = false;
		int roomCount = 0;
		while (roomCount < 15) {
			Point p = getRandomWall();

			boolean success;
			if (Math.random() < TOLERANCE) { // Try and place room.
				success = appendRoom(p);
			} else { // Try and place passageway.
				success = appendPassageway(p);
				isPassageway = true;
			}

			/*
			 * If placement was successful, either place a door (if
			 * non-consecutive hallways) or a floor tile (if consecutive
			 * hallways) and increment room count.
			 */
			if (success) {
				Tile t = getTileAt(p.x, p.y);
				actors.remove(t);

				if (cursorRoom.isPassageway && isPassageway) {
					t = new Tile(p.x, p.y, true, GraphicFile.DUNGEON, 6, 3);
				} else {
					t = new Tile(p.x, p.y, true, GraphicFile.DUNGEON, 0, 3);
				}

				actors.add(t);
				roomCount++;
			}

			isPassageway = false;
		}

		encloseLevel(GraphicFile.DUNGEON, 6, 3);
	}

	/**
	 * Appends a room of random dimensions to the specified point leading away
	 * from the cursor room.
	 * 
	 * @param p
	 *            Point on cursor room's wall.
	 * @return True if room creation was successful; false otherwise.
	 */
	private boolean appendRoom(Point p) {
		int width = RLUtilities.getRandom(5, 11);
		int height = RLUtilities.getRandom(5, 9);

		if (p.x == cursorRoom.minX) { // West
			return createRoom(cursorRoom.minX - width + 1, RLUtilities.getRandom(p.y - height + 2, p.y - 1), width,
					height, false);
		} else if (p.x == cursorRoom.maxX) { // East
			return createRoom(cursorRoom.maxX, RLUtilities.getRandom(p.y - height + 2, p.y - 1), width, height, false);
		} else if (p.y == cursorRoom.minY) { // North
			return createRoom(RLUtilities.getRandom(p.x - width + 2, p.x - 1), cursorRoom.minY - height + 1, width,
					height, false);
		} else if (p.y == cursorRoom.maxY) { // South
			return createRoom(RLUtilities.getRandom(p.x - width + 2, p.x - 1), cursorRoom.maxY, width, height, false);
		}

		return false;
	}

	/**
	 * Appends a random length passageway to the specified point leading away
	 * from the cursor room.
	 * 
	 * @param p
	 *            Point on cursor room's wall.
	 * @return True if room creation was successful; false otherwise.
	 */
	private boolean appendPassageway(Point p) {
		int length = RLUtilities.getRandom(5, 9);
		if (p.x == cursorRoom.minX) { // West
			return createRoom(cursorRoom.minX - length + 1, p.y - 1, length, 3, true);
		} else if (p.x == cursorRoom.maxX) { // East
			return createRoom(cursorRoom.maxX, p.y - 1, length, 3, true);
		} else if (p.y == cursorRoom.minY) { // North
			return createRoom(p.x - 1, cursorRoom.minY - length + 1, 3, length, true);
		} else if (p.y == cursorRoom.maxY) { // South
			return createRoom(p.x - 1, cursorRoom.maxY, 3, length, true);
		}

		return false;
	}

	/**
	 * Returns a random point on a wall of all eligible rooms and sets the
	 * cursor room to the room the wall is in.
	 * 
	 * @return Random point on a wall.
	 */
	private Point getRandomWall() {

		Room r = rooms.get(rand.nextInt(rooms.size()));
		cursorRoom = r;

		Point p = null;
		int choice = rand.nextInt(4);
		switch (choice) {
		case 0: // North wall
			p = new Point(RLUtilities.getRandom(r.minX + 1, r.maxX - 1), r.minY);
			break;
		case 1: // South wall
			p = new Point(RLUtilities.getRandom(r.minX + 1, r.maxX - 1), r.maxY);
			break;
		case 2: // East wall
			p = new Point(r.maxX, RLUtilities.getRandom(r.minY + 1, r.maxY - 1));
			break;
		case 3: // West wall
			p = new Point(r.minX, RLUtilities.getRandom(r.minY + 1, r.maxY - 1));
			break;
		}

		return p;
	}

	/**
	 * Method to determine whether a given room intersects with any other rooms.
	 * 
	 * @param r1
	 *            Room to check intersections for.
	 * @return True if an intersection is found; false otherwise.
	 */
	private boolean isIntersecting(Room r1) {
		if (rooms.isEmpty())
			return false;

		for (Room r2 : rooms) {
			// Check for intersection with other rooms.
			if (r1.maxX > r2.minX && r1.minX < r2.maxX && r1.maxY > r2.minY && r1.minY < r2.maxY) {
				return true;
			}

			// Check for room stretching out of bounds.
			if (r1.minX < 0 || r1.maxX > XMAX || r1.minY < 0 || r1.maxY > YMAX) {
				return true;
			}

		}

		return false;
	}

	/**
	 * Data structure to represent rooms.
	 * 
	 * @author Dan
	 * 
	 */
	private class Room {

		int minY, maxY, maxX, minX;
		boolean isPassageway;

		public Room(int x, int y, int width, int height, boolean isPassageway) {
			this.minX = x;
			this.maxX = x + width - 1;
			this.minY = y;
			this.maxY = y + height - 1;
			this.isPassageway = isPassageway;
		}

		public String toString() {
			return ("x1: " + minX + " y1: " + minY + " x2: " + maxX + " y2: " + maxY);
		}
	}
}
