package roguelike.world;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import roguelike.actors.Actor;
import roguelike.actors.Tile;
import roguelike.ui.graphics.Graphic.GraphicFile;

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
		numRoomsMax = 9;
		numRoomsMin = 6;
		actors = new ArrayList<Actor>();
		rooms = new ArrayList<Room>();
	}

	@Override
	public void generateFloor() {
		Random random = new Random();

		int nRooms = random.nextInt(numRoomsMax - numRoomsMin + 1) + numRoomsMin;

		Room dungeon = new Room(null, -1, -1, 0, 0);
		binarySpacePartition(dungeon, 0, XMAX - 1, 0, YMAX - 1);

		for (Room r : rooms) {
			generateRoom(r.x, r.y, r.width, r.height);
		}
	}

	private void binarySpacePartition(Room parent, int x1, int x2, int y1, int y2) {
		Random r = new Random();
		int alignment = r.nextInt(2);
		Room room = new Room(parent, 0, 0, 0, 0);
		
		int divLineX = 0;
		int divLineY = 0;
		int xRange = (x2 - roomSizeMin) - (x1 + roomSizeMin) + 1;
		int yRange = (y2 - roomSizeMin) - (y1 + roomSizeMin) + 1;

		if (xRange > 0)
			divLineX = r.nextInt(xRange) + x1 + roomSizeMin;
		if (yRange > 0)
			divLineY = r.nextInt(yRange) + y1 + roomSizeMin;

		// Min + (int)(Math.random() * ((Max - Min) + 1))
		if (alignment == 0) { // Horizontal
			
			System.out.println("x1: " + x1 + " x2: " + x2 + " y1: " + y1 + " y2: " + y2 + " divLineX: " + divLineX
					+ " divLineY: " + divLineY);

			// TODO check if there is enough space to make a room
			if (divLineY - y1 >= roomSizeMin && y2 - divLineY >= roomSizeMin) { // Horizontal
				binarySpacePartition(room, x1, x2, y1, divLineY);
				binarySpacePartition(room, x1, x2, divLineY + 1, y2);
			} else if (divLineX - x1 >= roomSizeMin && x2 - divLineX >= roomSizeMin) {
				binarySpacePartition(room, x1, divLineX, y1, y2);
				binarySpacePartition(room, divLineX + 1, x2, y1, y2);
			} else {
				room.x = x1;
				room.y = y1;
				room.width = x2 - x1;
				room.height = y2 - y1;
				rooms.add(room);
			}
		} else { // Vertical
			
			System.out.println("x1: " + x1 + " x2: " + x2 + " y1: " + y1 + " y2: " + y2 + " divLineX: " + divLineX
					+ " divLineY: " + divLineY);


			if (divLineX - x1 >= roomSizeMin && x2 - divLineX >= roomSizeMin) {
				binarySpacePartition(room, x1, divLineX, y1, y2);
				binarySpacePartition(room, divLineX + 1, x2, y1, y2);
			} else if (divLineY - y1 >= roomSizeMin && y2 - divLineY >= roomSizeMin) {
				binarySpacePartition(room, x1, x2, y1, divLineY);
				binarySpacePartition(room, x1, x2, divLineY + 1, y2);
			} else {
				room.x = x1;
				room.y = y1;
				room.width = x2 - x1;
				room.height = y2 - y1;
				rooms.add(room);
			}
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
		if (x + width - 1 >= XMAX || y + height - 1 >= YMAX || x < 0 || y < 0)
			return;

		for (int i = x; i < x + width - 1; i++) {
			for (int j = y; j < y + height - 1; j++) {
				Tile t = new Tile('.', Color.GRAY, i, j, true);
				t.setImage(GraphicFile.DUNGEON, 6, 3);
				actors.add(t);
			}
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
	private void carveStraightPassageway(Point p1, Point p2) throws IllegalArgumentException {
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
			throw new IllegalArgumentException("Passageway must be a straight line.");
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
		Room parent, left, right;

		private Room(Room parent, int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;

		}

	}

}
