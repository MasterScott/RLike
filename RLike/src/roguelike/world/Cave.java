package roguelike.world;

import java.awt.Color;
import java.util.ArrayList;

import roguelike.actors.Actor;
import roguelike.actors.Tile;

/**
 * Cave-based floor.
 * 
 * @author Dan
 * 
 */
public class Cave extends Floor {

	double tolerance;

	/**
	 * Creates a cave with default enclosed area.
	 */
	public Cave() {
		actors = new ArrayList<Actor>();
		tolerance = 0.51;
	}

	/**
	 * * Creates a cave with the specified tolerance for determining which areas
	 * are wall and which are open. For best results, use a tolerance of around
	 * 0.50.
	 * 
	 * @param tolerance
	 *            Double between 0.0 - 1.0
	 * @throws IllegalArgumentException
	 *             If tolerance is outside the expected range.
	 */
	public Cave(int tolerance) throws IllegalArgumentException {
		if (tolerance < 0.0 || tolerance > 1.0)
			throw new IllegalArgumentException();
		actors = new ArrayList<Actor>();
		this.tolerance = tolerance;
	}

	@Override
	public void generateFloor() {

		// Generate random grid
		for (int x = 0; x < XMAX; x++) {
			for (int y = 0; y < YMAX; y++) {
				if (Math.random() > tolerance) {
					actors.add(new Tile('#', Color.WHITE, x, y, false));
				}
			}
		}

		/*
		 * Utilize celluar automata rule B678/S345678 to generate a natural cave
		 * looking layout.
		 */
		int count = 1;
		while (count != 0) {
			count = 0;

			for (int x = 0; x < XMAX; x++) {
				for (int y = 0; y < YMAX; y++) {
					int n = numberOfNeighbors(x, y);
					Actor a = getActorAt(x, y);

					if (n >= 6 && a == null) { // If birth conditions are met
						actors.add(new Tile('#', Color.WHITE, x, y, false));
						count++;
					} else if (n < 3 && a != null) { // If survival not met
						actors.remove(a);
						count++;
					}
				}
			}
		}

		encloseLevel();
	}

	/**
	 * Get number of adjacent cells containing a tile.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @return Number of adjacent cells containing a tile.
	 */
	private int numberOfNeighbors(int x, int y) {

		final int[] XARR = { x - 1, x, x + 1, x + 1, x + 1, x, x - 1, x - 1 };
		final int[] YARR = { y - 1, y - 1, y - 1, y, y + 1, y + 1, y + 1, y };

		int count = 0;
		for (int i = 0; i < XARR.length; i++) {
			if (XARR[i] >= 0 && XARR[i] <= XMAX && YARR[i] >= 0
					&& YARR[i] <= YMAX) {
				if (getActorAt(XARR[i], YARR[i]) != null)
					count++;
			}
		}

		return count;
	}
}
