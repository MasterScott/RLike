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
	private final double TOLERANCE = 0.75;
	private Random rand;

	public BaseDungeon() {
		actors = new ArrayList<Actor>();
		rooms = new ArrayList<Room>();
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
	 * @return True if room creation was successful; false otherwise.
	 */
	private boolean createRoom(int x, int y, int width, int height) {
		Room r = new Room(x, y, width, height);

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

		return true;
	}

	@Override
	public void generateFloor() {
		super.generateFloor();
		// TODO Change to false after done debugging.
		fillLevelWithTiles(GraphicFile.DUNGEON, 0, 0, true);

		createRoom(20, 10, 6, 6);

		Point p = getRandomWall();

		appendPassageway(p);

		Tile t = getTileAt(p.x, p.y);
		actors.remove(t);
		t = new Tile(p.x, p.y, true, GraphicFile.DUNGEON, 0, 3);
		actors.add(t);

		encloseLevel(GraphicFile.DUNGEON, 6, 3);
	}

	/**
	 * Appends a random length passageway to the specified point leading away
	 * from the cursor room.
	 * 
	 * @param p Point on cursor room's wall.
	 */
	private void appendPassageway(Point p) {
		int length = RLUtilities.getRandom(5, 9);
		if (p.x == cursorRoom.minX) { // West
			createRoom(cursorRoom.minX - length + 1, p.y - 1, length, 3);
		} else if (p.x == cursorRoom.maxX) { // East
			createRoom(cursorRoom.maxX, p.y - 1, length, 3);
		} else if (p.y == cursorRoom.minY) { // North
			createRoom(p.x - 1, cursorRoom.minY - length + 1, 3, length);
		} else if (p.y == cursorRoom.maxY) { // South
			createRoom(p.x - 1, cursorRoom.maxY, 3, length);
		}
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
			if (r1.maxX > r2.minX && r1.minX < r2.maxX && r1.maxY > r2.minY && r1.minY < r2.maxY) {
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

		public Room(int x, int y, int width, int height) {
			this.minX = x;
			this.maxX = x + width - 1;
			this.minY = y;
			this.maxY = y + height - 1;
		}

		public String toString() {
			return ("x1: " + minX + " y1: " + minY + " x2: " + maxX + " y2: " + maxY);
		}
	}
}
