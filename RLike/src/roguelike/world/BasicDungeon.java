package roguelike.world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import roguelike.actors.Actor;
import roguelike.actors.Tile;
import roguelike.etc.RLUtilities;
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

	/*
	 * Different rules on the splitting position can result in homogeneous
	 * sub-dungeons (position between 0.45 and 0.55) or heterogeneous ones
	 * (position between 0.1 and 0.9). We can also choose to use a deeper
	 * recursion level on some parts of the dungeon so that we get smaller rooms
	 * there.
	 */
	private void binarySpacePartition(Room parent, int x1, int x2, int y1, int y2) {
		Random r = new Random();
		int alignment = r.nextInt(2);
		Room room = new Room(parent, 0, 0, 0, 0);

		int divLineX = 0;
		int divLineY = 0;
		
		divLineX = RLUtilities.generateRandom(x1, x2, 0.45, 0.55);
		divLineY = RLUtilities.generateRandom(y1, y2, 0.45, 0.55);

		if (alignment == 0) { // Horizontal

			if (divLineY - y1 >= roomSizeMin && y2 - divLineY >= roomSizeMin) { // Horizontal
				binarySpacePartition(room, x1, x2, y1, divLineY);
				binarySpacePartition(room, x1, x2, divLineY , y2);
			} else if (divLineX - x1 >= roomSizeMin && x2 - divLineX >= roomSizeMin) { // Vertical
				binarySpacePartition(room, x1, divLineX, y1, y2);
				binarySpacePartition(room, divLineX, x2, y1, y2);
			} else {
				room.x = x1;
				room.y = y1;
				room.width = x2 - x1;
				room.height = y2 - y1;
				rooms.add(room);
			}
		} else { // Vertical

			if (divLineX - x1 >= roomSizeMin && x2 - divLineX >= roomSizeMin) {
				binarySpacePartition(room, x1, divLineX, y1, y2);
				binarySpacePartition(room, divLineX, x2, y1, y2);
			} else if (divLineY - y1 >= roomSizeMin && y2 - divLineY >= roomSizeMin) {
				binarySpacePartition(room, x1, x2, y1, divLineY);
				binarySpacePartition(room, x1, x2, divLineY, y2);
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

		// Floor
		for (int i = x; i < x + width; i++) {
			for (int j = y; j < y + height; j++) {
				Tile t = new Tile(i, j, true);
				t.setImage(GraphicFile.DUNGEON, 6, 3);
				actors.add(t);
			}
		}
		
		// Top and Bottom walls
		for (int i = x; i < x + width; i++) {
			getTileAt(i, y).setImage(GraphicFile.DUNGEON, 3, 0);
			getTileAt(i, y + height - 1).setImage(GraphicFile.DUNGEON, 3, 0);
		}
		
		// Left and right walls
		for (int j = y; j < y + height; j++) {
			getTileAt(x, j).setImage(GraphicFile.DUNGEON, 3, 0);
			getTileAt(x + width - 1, j).setImage(GraphicFile.DUNGEON, 3, 0);
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
						actors.add(new Tile(p1.x, i, true));
					} else {
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
						actors.add(new Tile(i, p1.y, true));
					} else {
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
