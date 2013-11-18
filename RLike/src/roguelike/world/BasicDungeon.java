package roguelike.world;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import roguelike.actors.Actor;
import roguelike.actors.Tile;

/**
 * Basic dungeon layout with a specified number of rooms and passageways
 * connecting said rooms.
 * 
 * @author Dan
 * 
 */
public class BasicDungeon extends Floor {

	int roomSizeMax, roomSizeMin, numRoomsMax, numRoomsMin, numRooms;

	ArrayList<Room> rooms;

	/**
	 * Creates a new instance of a BasicDungeon level.
	 */
	public BasicDungeon() {
		numRooms = 0;
		roomSizeMax = 8;
		roomSizeMin = 5;
		numRoomsMax = 8;
		numRoomsMin = 6;
		actors = new ArrayList<Actor>();
		rooms = new ArrayList<Room>();

		generateFloor();
	}

	@Override
	protected void generateFloor() {
		Random random = new Random();

		int nRooms = random.nextInt(numRoomsMax - numRoomsMin + 1)
				+ numRoomsMin;

		// Generate all rooms.
		while (numRooms < nRooms) {
			generateRoom(
					random.nextInt(XMAX),
					random.nextInt(YMAX),
					random.nextInt(roomSizeMax - roomSizeMin + 1) + roomSizeMin,
					random.nextInt(roomSizeMax - roomSizeMin + 1) + roomSizeMin);
		}

		// Add doors to all rooms.
		for (Room room : rooms) {
			Point p = room.getRandomWall();
			Actor a = getActorAt(p.x, p.y);
			a.setIcon('/');
			a.setTraversable(true);
		}

	}

	/**
	 * Attempts to generate a room at the given coordinates and increments room
	 * count if successful.
	 * 
	 * @param x
	 *            x-coordinate of top-left corner.
	 * @param y
	 *            y-coordinate of top-left corner.
	 * @param width
	 *            Width of room.
	 * @param height
	 *            Height of room.
	 */
	private void generateRoom(int x, int y, int width, int height) {
		boolean collision = false;

		/*
		 * Check if any tile within the specified rectangle causes a collision.
		 */
		for (int i = x; i < x + width; ++i) {
			for (int j = y; j < y + height; ++j) {
				if (checkCollision(i, j) || i >= XMAX || j >= YMAX) {
					collision = true;
					break;
				}
			}
			if (collision)
				break;
		}

		/*
		 * If there is no collision, create a new room within this rectangle.
		 */
		if (!collision) {
			// Add floors and walls
			for (int i = x; i < x + width; ++i) {
				for (int j = y; j < y + height; ++j) {
					if (i == x || i == x + width - 1 || j == y
							|| j == y + height - 1) {
						actors.add(new Tile('#', Color.WHITE, i, j, false));
					} else {
						actors.add(new Tile('.', Color.GRAY, i, j, true));
					}
				}
			}

			rooms.add(new Room(x, y, width, height));
			numRooms++;
		}

	}

	private void generatePassageway(Room r1, Room r2) {
		r1.resetEligibility(); // Clear any previously set eligibilities

		if ((r1.x + r1.width) < (r2.x + r2.width)) { // r1 east wall eligible
			r1.east = true;
		}

		if (r1.x > r2.x) { // r1 west wall eligible
			r1.west = true;
		}

		if ((r1.y + r1.height) > (r2.y + r2.height)) { // r1 south wall eligible
			r1.south = true;
		}

		if (r1.y < r2.y) { // r1 north wall eligible
			r1.north = true;
		}
	}

	/**
	 * Carves either a horizontal or vertical passageway between two points.
	 * 
	 * @param p1
	 *            First point
	 * @param p2
	 *            Second point
	 * @throws IllegalArgumentException
	 *             If this would create a non-horizontal or vertical line.
	 */
	private void carveStraightPassageway(Point p1, Point p2)
			throws IllegalArgumentException {
		if ((p1.x == p2.x || p1.y == p2.y)) {
			if (p1.x == p2.x) { // If this is a vertical line
				int y1, y2;

				if (p1.y < p2.y) { // Set highest point on grid as y1
					y1 = p1.y;
					y2 = p2.y;
				} else {
					y1 = p2.y;
					y2 = p1.y;
				}

				// Change all points on this line to floor.
				for (int i = y1; i <= y2; ++i) {
					Actor actor = getActorAt(p1.x, i);
					if (actor == null) {
						actors.add(new Tile('.', Color.GRAY, p1.x, i, true));
					} else {
						actor.setIcon('.');
						actor.setColor(Color.GRAY);
						actor.setTraversable(true);
					}
				}

			} else { // This is a horizontal line
				int x1, x2;

				if (p1.x < p2.x) { // Set leftmost point on grid as y1
					x1 = p1.x;
					x2 = p2.x;
				} else {
					x1 = p2.x;
					x2 = p1.x;
				}

				// Change all points on this line to floor.
				for (int i = x1; i <= x2; ++i) {
					Actor actor = getActorAt(i, p1.y);
					if (actor == null) {
						actors.add(new Tile('.', Color.GRAY, i, p1.y, true));
					} else {
						actor.setIcon('.');
						actor.setColor(Color.GRAY);
						actor.setTraversable(true);
					}
				}
			}
		} else {
			throw new IllegalArgumentException(
					"Passageway must be a straight line.");
		}
	}

	/**
	 * Data structure to manage the rooms that have been created.
	 * 
	 * @author Dan
	 * 
	 */
	private class Room {

		int x, y, width, height;

		boolean north, east, south, west;

		private Room(int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;

			resetEligibility();
		}

		/**
		 * Returns a random point on a wall which can be used to create a
		 * passageway to another room.
		 * 
		 * @return Random point on a wall.
		 */
		Point getRandomWall() {
			Random random = new Random();
			int x, y;

			int r = random.nextInt(4);
			switch (r) {
			case 0:
				x = this.x;
				y = this.y + random.nextInt(height - 2) + 1;
				break;
			case 1:
				x = this.x + random.nextInt(width - 2) + 1;
				y = this.y;
				break;
			case 2:
				x = this.x + width - 1;
				y = this.y + random.nextInt(height - 2) + 1;
				break;
			case 3:
				x = this.x + random.nextInt(width - 2) + 1;
				y = this.y + height - 1;
				break;
			default:
				x = 0;
				y = 0;
				break;
			}

			return new Point(x, y);
		}

		void resetEligibility() {
			north = false;
			east = false;
			south = false;
			west = false;
		}
	}

}
